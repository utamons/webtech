let playInterval;
function submitCoords(obj) {
    postCoord(obj.parentNode.rowIndex,obj.cellIndex);
}

function start() {
    console.log('start')
}

function stopPlay(){
    clearInterval(playInterval)
}

function load(){
    loadArray(false);
}

function loadArray(play) {
    fetch('http://localhost:8080/api/arr?move='+play)
        .then(response => response.json())
        .then(arr => renderArray(arr));
}

function renderArray(arr) {
    console.log(arr)
    const table = document.getElementById('playGround')
    table.innerHTML = ''
    for (let y=0; y<arr.length; ++y) {
        const tr = document.createElement('tr');
        for (let x=0; x<arr[0].length; ++x) {
            const td = document.createElement('td')
            td.setAttribute('onclick','submitCoords(this);');
            td.setAttribute('style',arr[y][x].style);
            td.innerText = arr[y][x].value;
            tr.appendChild(td);
        }
        table.appendChild(tr);
    }

}

function postCoord(x,y) {
    const coord = {
        x: x,
        y: y
    }
    const options = {
        method: 'POST',
        body: JSON.stringify(coord),
        headers: {
            'Content-Type': 'application/json'
        }
    }
    fetch('http://localhost:8080/api/coord', options)
        .then(res => res.json())
        .then(() => loadArray(false));
}

