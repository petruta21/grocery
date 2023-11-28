function deleteProduct(userId, listId,id) {
    const baseUrl = window.location.origin;
    const url = `${baseUrl}/grocery_list/list/${userId}/${listId}/${id}`;
    fetch(url, { method: 'DELETE' })

        .then(response => {
            if (response.ok) {
//                goToAnotherPage();
            location.reload();
            } else {

            }
        })
        .catch(error => {

        });
    }