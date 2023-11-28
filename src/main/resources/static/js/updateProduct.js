function updateProduct(userId, listId, id) {

    var amount = 0;
    var inputElements = document.getElementsByName("amount");

    for (var i = 0; i < inputElements.length; i++) {
        var currentElement = inputElements[i];
        if (currentElement.id == id) {
            var inputValue = currentElement.value;
            amount = inputValue;
            break;
        }
    }

    var amountSize = 0;
    var inputElements = document.getElementsByName("amountSize");

    for (var i = 0; i < inputElements.length; i++) {
        var currentElement = inputElements[i];
        if (currentElement.id == id) {
            var inputValue = currentElement.value;
            amountSize = inputValue;
            break;
        }
    }
    const baseUrl = window.location.origin;
    const url = `${baseUrl}/grocery_list/list/${userId}/${listId}/${id}?amount=${amount}&amountSize=${amountSize}`;
    fetch(url, { method: 'PUT' })
        .then(response => {
            if (response.ok) {
                location.reload();
            }
         })
}