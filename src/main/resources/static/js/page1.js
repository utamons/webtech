let playInterval;
function submitCoords(obj) {
    const input = document.getElementById('num');
    const form = document.getElementById('mainForm');
    input.value = obj.cellIndex+','+obj.parentNode.rowIndex;
    form.submit();
}

function start() {
    const autoPlay = document.getElementById('autoPlay');
    const form = document.getElementById('sendPlay');
    autoPlay.value = true;
    console.log('start')
    form.submit();
}

function autoPlay(){
    const form = document.getElementById('nextForm');
    let play=document.getElementById('autoPlay');
    if(play.value==='true'){
        console.log(play.value)
        setTimeout(()=>form.submit(), 500)
    }
}

function stopPlay(){
    const autoPlay = document.getElementById('autoPlay');
    const form = document.getElementById('sendPlay');
    autoPlay.value = false;
    console.log('stop')
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

