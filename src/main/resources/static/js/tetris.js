// noinspection DuplicatedCode

let playInterval;

function start() {
    loadArray(true, true);
    if (!playInterval)
        playInterval = setInterval(() => loadArray(true, true), 600)
}

function stopPlay() {
    clearInterval(playInterval)
}

function load() {
    loadArray(false,false);
}

function turnLeft(){
    fetch('http://localhost:8080/api/turnleft').then(()=>{loadArray(true, false)})
}

function turnRight(){
    fetch('http://localhost:8080/api/turnright').then(()=>{loadArray(true, false)})
}

function moveLeft(){
    fetch('http://localhost:8080/api/moveleft').then(()=>{loadArray(true, false)})
}
function moveRight(){
    fetch('http://localhost:8080/api/moveright').then(()=>{loadArray(true, false)})
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

function loadArray(play,down) {
    fetch('http://localhost:8080/api/tetrisarr?move=' + play + '&down=' + down)
        .then(response => response.json())
        .then(arr => {
            renderArray(arr)
        });
}

function renderArray(arr) {
    const table = document.getElementById('playGround')
    table.innerHTML = ''
    for (let  y = 0; y < arr[0].length; ++y) {
        const tr = document.createElement('tr');
        for (let x = 0; x < arr.length; ++x) {
            const td = document.createElement('td')
            td.setAttribute('onclick', 'submitCoords(this);');
            td.setAttribute('style', arr[x][y].style);
            td.innerText = arr[x][y].value;
            tr.appendChild(td);
        }
        table.appendChild(tr);
    }
}
