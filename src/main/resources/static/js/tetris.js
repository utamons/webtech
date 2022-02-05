// noinspection DuplicatedCode

let playInterval;
let x = 0;

function start() {
    loadArray(false);
    playInterval = setInterval(() => loadArray(true), 300)
}

function stopPlay() {
    clearInterval(playInterval)
}

function load() {
    loadArray(false);
}

function turnLeft(){
    fetch('http://localhost:8080/api/turnleft').then()

}

function turnRight(){
    fetch('http://localhost:8080/api/turnright').then()
}

function moveLeft(){
    fetch('http://localhost:8080/api/moveleft').then()
}
function moveRight(){
    fetch('http://localhost:8080/api/moveright').then()
}

function keyPress(event){
    if(event.key==='ArrowUp'){
        turnLeft();
    }
    if(event.key==='ArrowDown'){
        turnRight();
    }

    if(event.key==='ArrowLeft'){
        moveLeft();
    }
    if(event.key==='ArrowRight'){
        moveRight();
    }
}

function loadArray(play) {
    fetch('http://localhost:8080/api/tetrisarr?move=' + play)
        .then(response => response.json())
        .then(arr => {
            renderArray(arr)
        });
}

function renderArray(arr) {
    const table = document.getElementById('playGround')
    table.innerHTML = ''
    for (let x = 0; x < arr.length; ++y) {
        const tr = document.createElement('tr');
        for (let x = 0; x < arr[0].length; ++x) {
            const td = document.createElement('td')
            td.setAttribute('onclick', 'submitCoords(this);');
            td.setAttribute('style', arr[y][x].style);
            td.innerText = arr[y][x].value;
            tr.appendChild(td);
        }
        table.appendChild(tr);
    }
}