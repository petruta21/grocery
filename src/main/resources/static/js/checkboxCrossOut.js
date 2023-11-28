  function checkboxCrossOut(checkbox) {

   let label = checkbox.nextElementSibling;
    if (checkbox.checked) {
     label.style.textDecoration = "line-through";
    }
    else{
        label.style.textDecoration = "none";
    }
}
