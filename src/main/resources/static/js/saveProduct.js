function saveProduct() {
    const name = document.getElementById('name').value;
    const baseUrl = window.location.origin;
    const url = `${baseUrl}/lists/?name=${name}`;
    fetch(url, { method: 'POST' })

        .then(response => {
            if (response.ok) {
            location.reload();
            }
        })
        .catch(error => {
        });
}