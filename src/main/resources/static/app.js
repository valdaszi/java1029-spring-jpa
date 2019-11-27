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
        new URLSearchParams({page: page, 'size': size}));
    const res = await response.json();
    showPage(res);
}

function showPage(page) {
    let content = document.getElementById('content');
    let table = `
        <table class="table">
            <tr>
                <th>PID</th>
                <th>First-name</th>
                <th>Last-name</th>
                <th>Actions</th>
            </tr>
    `;
    page.content.forEach(e => {
        table += `
            <tr>
                <td>${e.pid}</td>
                <td>${e.firstName}</td>
                <td>${e.lastName}</td>
                <td>
                    <button class="btn btn-danger"
                        onclick="remove(${e.id})">Delete</button>
                </td>
            </tr>
        `;
    })
    table += '</table>';
    content.innerHTML = table;
}

function regOnclick(name, action) {
    let elem = document.getElementById(name);
    elem.onclick = action;
}

function regActions() {
    regOnclick('next', onNextClick);
    regOnclick('prev', onPrevClick);
    regOnclick('save-changes', onSaveChangesClick);
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

function onSaveChangesClick() {
    const pid = document.getElementById('person-id').value;
    const fname = document.getElementById('first-name').value;
    const lname = document.getElementById('last-name').value;

    saveDriver({
        pid: pid,
        firstName: fname,
        lastName: lname
    });

    $('#new-driver').modal('hide');
}

async function saveDriver(driver) {
    if (!driver.id) {
        // create new
        const response = await fetch(
                '/api/driver',
                {
                     method: 'POST',
                     headers: {
                        'Content-Type': 'application/json'
                     },
                     body: JSON.stringify(driver)
                }
        );
    } else {
        // update
        alert('Dar nera update metodo!!!!');
    }
    const res = await response.json();

    document.getElementById('person-id').value = '';
    document.getElementById('first-name').value = '';
    document.getElementById('last-name').value = '';

    getPage(currentPage, size);
}

function remove(id) {
    //alert('Removing...' + id)
    deleteById(id);
}

async function deleteById(id) {
    const response = await fetch(
        '/api/driver/' + id,
        {
             method: 'DELETE'
        }
    );
    getPage(currentPage, size);
}

start();
