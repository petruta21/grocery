function deleteList(id) {
    const baseUrl = window.location.origin;
    const url = `${baseUrl}/lists/${id}`;
    fetch(url, { method: 'DELETE' })
        .then(response => {
            if (response.ok) {
                location.reload();
            }
        })
        .catch(error => {
        });
    }
