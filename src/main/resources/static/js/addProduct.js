function addProduct(userId, listId) {
    const name = document.getElementById('productA').value;
    const amount = document.getElementById('productAmount').value;
    const amountSize = document.getElementById('productAmountSize').value;

    const baseUrl = window.location.origin;
    const url = `${baseUrl}/grocery_list/list/${userId}/${listId}?name=${name}&amount=${amount}&amountSize=${amountSize}`;
    fetch(url, { method: 'POST' })
         .then(response => {
               if (response.ok) {
                  location.reload();
               }
           })
 }
