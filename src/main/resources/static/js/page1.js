let playInterval;
let x = 0;

function submitCoords(obj) {
    postCoord(obj.parentNode.rowIndex, obj.cellIndex);
}

function clearArr() {
    fetch('http://localhost:8080/api/clear')
        .then(() => loadArray(false));
    x = 0;
    document.getElementById('genNum').innerText = x;

}

function genCount() {
    document.getElementById('genNum').innerText = x;
    x++;
}

function start() {
    playInterval = setInterval(() => loadArray(true), 500)
    console.log('start')
}

function stopPlay() {
    console.log('stop'),
        clearInterval(playInterval)
}

function load() {
    loadArray(false);
}

function size() {
    let numInput = document.getElementById('size')
    let x = numInput.value.split(',')
    let is = isNaN(x)
    if (is == true) {
        alert("Input numbers in the field");
    } else {
        fetch('http://localhost:8080/api/size?num=' + numInput.value)
            .then(() => loadArray(true));
    }
}

function loadArray(play) {
    fetch('http://localhost:8080/api/arr?move=' + play)
        .then(response => response.json())
        .then(arr => {
            renderArray(arr)
            if (play)
                genCount()
        });
}

function renderArray(arr) {
    console.log(arr)
    const table = document.getElementById('playGround')
    table.innerHTML = ''
    for (let y = 0; y < arr.length; ++y) {
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

function postCoord(x, y) {
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

