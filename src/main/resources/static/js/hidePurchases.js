function hidePurchases(labelId) {

        let label = document.getElementById(labelId);
        if (label.style.textDecoration === 'line-through') {
            label.style.display = 'none';
        } else {
            label.style.display = 'block';
        }
}
