var categoriesURL = "http://localhost:8080/rest/categories";

var ordersURL = "http://localhost:8080/rest/orders";

var itemsArray;

var currentCategoryElem;

var clickFlag;

var currentCategoryId;

var currentItemId;

var currentItem;

var currentOrderRows;

var totalSum;

var totalQuantity;

refresh();

function refresh() {
    findAllCategories();
    clickFlag = true;
    itemsArray = [];
    currentOrderRows = [];
    totalQuantity = 0;
    totalSum = 0;
}

$(document).ready(function () {
    $("#addToOrder").click(function () {
        if (Number($('#quantity').val()) == 0) {
            alert("The number must be greater than 0");
        }
        else {
            addUpdateRow();
        }
        $('#checkOrder').prop('value', "You choose " + totalQuantity + " items for " + totalSum + "$");
    });

    $("#checkOrder").click(function () {
        checkOrder();
    });

    $("#createCategory").click(function () {
        $('#editCategory').modal();
    });
});

function findAllCategories() {
    console.log('findAllCategories:');
    $.ajax({
        type: 'GET',
        url: categoriesURL,
        dataType: "json", // data type of response
        success: renderListCategories
    });
}

function renderListCategories(data) {
    // JAX-RS serializes an empty list as null, and a 'collection of one' as an object (not an 'array of one')
    var list = data == null ? [] : (data instanceof Array ? data : [data]);

    $.each(list, function (index, category) {
        $li = $('<li/>');
        $li.attr('data-identity', category.id);
        $li.attr('data-category', true);
        $li.append("<a href='index#''>" + category.name + "</a>");
        $('#categoriesList').append($li);
    });

}

function findItemsByCategory() {
    console.log('findItemsByCategory:');
    $.ajax({
        type: 'GET',
        url: categoriesURL + "/" + currentCategoryId + "/items",
        dataType: "json", // data type of response
        success: renderItemsByCategory
    });
}

$(document).on('click', "li", function () {
    if ($(this).data('category') == false) {
        clickFlag = false;
        currentItemId = $(this).data('identity');
        findItemById();
        $('#quantity').prop('disabled', false);
        $('#addToOrder').prop('disabled', false);
    }
    if ($(this).parent().get(0) == $('#categoriesList').get(0) && clickFlag) {
        currentCategoryId = $(this).data('identity');
        for (var i = 0; i < itemsArray.length; i++) {
            itemsArray[i].detach();
        }
        currentCategoryElem = $(this);
        findItemsByCategory();
    }
    else if ($(this).parent().get(0) == $('#categoriesList').get(0) && !clickFlag) {
        clickFlag = true;
    }
})

function renderItemsByCategory(data) {
    itemsArray = [];
    var list = data == null ? [] : (data instanceof Array ? data : [data]);
    $ul = $('<ul/>');
    $.each(list, function (index, item) {
        $li = $('<li/>');
        $li.attr('data-identity', item.id);
        $li.attr('data-category', false);
        $li.append("<a href='index#''>" + item.name + "</a>");
        itemsArray.push($li);
        $ul.append($li);
    });
    currentCategoryElem.append($ul);

}

function findItemById() {
    console.log('findItemsByCategory:');
    $.ajax({
        type: 'GET',
        url: categoriesURL + "/" + currentCategoryId + "/items/" + currentItemId,
        dataType: "json", // data type of response
        success: function (data) {
            console.log('findItemById success: ' + data.name);
            currentItem = data;
            $('#description').val(currentItem.name + ":\n\n " + currentItem.description);
            $('#price').val(currentItem.price);
        }
    });
}

function addUpdateRow() {
    var currentRow = undefined;
    for (var i = 0; i < currentOrderRows.length; i++) {
        if (currentOrderRows[i].item.id == currentItemId)
            currentRow = currentOrderRows[i];
    }
    if (currentRow == undefined) {
        currentRow = {
            item: currentItem,
            quantity: Number($('#quantity').val()),
            price: currentItem.price,
            sum: $('#quantity').val() * currentItem.price
        };
        currentOrderRows.push(currentRow);
    }
    else {
        currentRow.quantity = currentRow.quantity + Number($('#quantity').val());
        currentRow.sum = currentRow.quantity * currentRow.price;
    }
    totalQuantity = 0;
    totalSum = 0;
    for (var i = 0; i < currentOrderRows.length; i++) {
        totalQuantity = totalQuantity + currentOrderRows[i].quantity;
        totalSum = totalSum + currentOrderRows[i].sum;
    }
}


function checkOrder() {
    $('#orderTable tbody tr').remove();
    var currentRow;
    var $tbody = $('#orderTable').append('<tbody />').children('tbody');
    for (var i = 0; i < currentOrderRows.length; i++) {
        currentRow = currentOrderRows[i];
        $tbody.append('<tr class="item"/>').children('tr:last')
            .append("<td id='itemId' style='display: none'>" + currentRow.item.id + "</td>")
            .append("<td >" + currentRow.item.name + "</td>")
            .append("<td >" + currentRow.price + "</td>")
            .append("<td >" + currentRow.quantity + "</td>")
            .append("<td >" + currentRow.sum + "</td>");
    }
    $('#createOrder').modal();
}

function createOrder() {
    console.log('createOrder');
    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: ordersURL,
        dataType: "text",
        data: orderToJSON(null),
        success: function () {
            alert('Order created successfully');
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('createOrder error: ' + textStatus);
        }
    });
}

function orderToJSON(id) {
    return JSON.stringify({
        "id": id == null ? null : id,
        "name": $('#userName').val(),
        "email": $('#userEmail').val()
    });
}

function createCategory() {
    console.log('createCategory');
    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: categoriesURL,
        dataType: "text",
        data: categoryToJSON(null),
        success: function () {
            alert('Category created successfully');
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('createCategory error: ' + textStatus);
        }
    });
    refresh();
}

function categoryToJSON(id) {
    return JSON.stringify({
        "id": id == null ? null : id,
        "name": $('#categoryName').val(),
        "description": $('#categoryDescription').val(),
        "itemSet" : []
    });
}
