function addCopyListName(userId, listId) {

 const newName = document.getElementById('newNameList').value;
 const baseUrl = window.location.origin;
 const url = `${baseUrl}/grocery_list/list/${userId}/${listId}/copy?name=${newName}`;
 const url1 = `${baseUrl}/lists/`;

 fetch(url, { method: 'POST'})
    .then(response => {
          if (response.ok) {
             window.location.href = url1;
          }
    })
    .catch(error => {
          console.error("Error:", error);
    });
 }


