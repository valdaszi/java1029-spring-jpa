var size = 3;
var currentPage = 1;

function start() {
    let e = document.getElementById('h');
    e.innerHTML = 'Drivers';
    //let page = getPage(1, size);
    // showPage(page);
    regActions();
    getPage(currentPage, size);
}

async function getPage(page, size) {
    const response = await fetch(
        '/api/driver?' +
        new URLSearchParams({page: page, size: size}));
    const res = await response.json();
    showPage(res);
}

function showPage(page) {
    let content = document.getElementById('content');
    let table = `
        <table class="table">
            <tr>
                <td>PID</td>
                <td>First-name</td>
                <td>Last-name</td>
            </tr>
    `;
    page.content.forEach(e => {
        table += `
            <tr>
                <td>${e.pid}</td>
                <td>${e.firstName}</td>
                <td>${e.lastName}</td>
            </tr>
        `;
    })
    table += '</table>';
    content.innerHTML = table;
}

function regActions() {
    let bNext = document.getElementById('next');
    bNext.onclick = onNextClick;

    let bPrev = document.getElementById('prev');
    bPrev.onclick = onPrevClick;
}

function onNextClick(event) {
    event.preventDefault();
    //alert('onNextClick')
    getPage(++currentPage, size);
}

function onPrevClick(event) {
    event.preventDefault();
    //alert('onPrevClick')
    if (currentPage > 1) {
        getPage(--currentPage, size);
    }
}

start();
