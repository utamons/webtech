function submitCoords(obj) {
    const input = document.getElementById('num');
    const form = document.getElementById('mainForm');
    input.value = obj.cellIndex+','+obj.parentNode.rowIndex;
    form.submit();
}
function validate(textField) {
    console.log(textField.value);
}
function tableMouseOver() {
    console.log('mouse over');
}
function keyPress(event) {
    console.log(event.key)
}