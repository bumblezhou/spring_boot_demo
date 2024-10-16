function getRowData(editId) {
    let rowId = editId.toString().replace("edit_link_", "tr_").replace("add_link_", "tr_");
    // Get the row element by its id
    let row = document.getElementById(rowId);
    
    // Get all the <td> elements within that row
    let cells = row.getElementsByTagName('td');
    
    // Extract the data from each cell
    let id = cells[0].innerText;
    let name = cells[1].innerText;
    let address = cells[2].innerText;
    let code = cells[3].innerText;
    let members = cells[4].innerText;
    let isRunning = cells[5].innerText == "true" ? true : false;

    // Output the data (you can also return it or use it in your logic)
    console.log("ID: " + id + ", Name: " + name + ", Addr: " + address + ", Code: " + code + ", Members: " + members + ", IsRunning: " + isRunning);

    return {
        id: id,
        name: name,
        address: address,
        code: code,
        members: members,
        isRunning: isRunning,
    };
}

let currentPageIndex = 0;
const pageSize = 10;
let currentState = 0; //0: browser, 1: search

// Function to display pagination controls
function display_pagination(data) {
    const pagination = document.getElementById('pagination');
    pagination.innerHTML = '';

    // Previous button
    if (!data.first) {
        const prev = document.createElement('li');
        prev.classList.add('page-item');
        prev.innerHTML = '<a class="page-link" href="#">Previous</a>';
        prev.onclick = () => {
            if (currentPageIndex > 0) {
                currentPageIndex--;
                if (currentState == 0) {
                    load_departments(currentPageIndex);
                } else {
                    search_department(currentPageIndex);
                }
            }
        };
        pagination.appendChild(prev);
    }

    // Page numbers
    for (let i = 0; i < data.totalPages; i++) {
        const page = document.createElement('li');
        page.classList.add('page-item');
        if (i === currentPageIndex) {
            page.classList.add('active');
        }
        page.innerHTML = `<a class="page-link" href="#">${i + 1}</a>`;
        page.onclick = (function (pageIndex) {
            return function () {
                currentPageIndex = pageIndex;
                if (currentState == 0) {
                    load_departments(currentPageIndex);
                } else {
                    search_department(currentPageIndex);
                }
            };
        })(i);
        pagination.appendChild(page);
    }

    // Next button
    if (!data.last) {
        const next = document.createElement('li');
        next.classList.add('page-item');
        next.innerHTML = '<a class="page-link" href="#">Next</a>';
        next.onclick = () => {
            if (currentPageIndex < data.totalPages - 1) {
                currentPageIndex++;
                if (currentState == 0) {
                    load_departments(currentPageIndex);
                } else {
                    search_department(currentPageIndex);
                }
            }
        };
        pagination.appendChild(next);
    }
}

function search_department(currentPageIndex){
    let search_key_name = document.getElementById('txt_search_name').value;
    let search_key_addr = document.getElementById('txt_search_addr').value;
    let search_key_code = document.getElementById('txt_search_code').value;
    let search_min_members = document.getElementById('txt_search_min_members').value;
    let search_max_members = document.getElementById('txt_search_max_members').value;
    if(
        (search_key_name == null || search_key_name == "") &&
        (search_key_addr == null || search_key_addr == "") &&
        (search_key_code == null || search_key_code == "") &&
        (search_min_members == null || search_min_members == "") &&
        (search_max_members == null || search_max_members == "")
    ) {
        currentState = 0;
        currentPageIndex = 0;
        load_departments(currentPageIndex);
        return;
    }
    currentState = 1;
    // Create a new XMLHttpRequest object
    const xhr = new XMLHttpRequest();

    // Specify the type of request (GET) and the URL
    xhr.open('GET', 'http://localhost:8080/api/departments/findDepartments?page=' + currentPageIndex + '&size=' + pageSize + '&name=' + search_key_name + '&address=' + search_key_addr + '&code=' + search_key_code + '&membersMin' + search_min_members + '&membersMax' + search_max_members, true);

    // Set up a callback function to handle the response
    xhr.onload = function() {
        if (xhr.status === 200) {
            // Parse the JSON response
            const data = JSON.parse(xhr.responseText);
            // Get the table body element
            const tbody = document.getElementById('table_departments').getElementsByTagName('tbody')[0];
            // Clear the table before inserting new data
            tbody.innerHTML = '';
            // Iterate over the array of departments and create table rows
            data.content.forEach(department => {
                const row = tbody.insertRow(); // Insert a new row
                row.id = 'tr_' + department.id;
                const idCell = row.insertCell(0); // Insert a cell for the Id
                idCell.textContent = department.id;   // Set the text content of the cell
                const nameCell = row.insertCell(1); // Insert a cell for the Name
                nameCell.textContent = department.name; // Set the text content of the cell
                const addrCell = row.insertCell(2); // Insert a cell for the Address
                addrCell.textContent = department.address; // Set the text content of the cell
                const codeCell = row.insertCell(3); // Insert a cell for the Code
                codeCell.textContent = department.code; // Set the text content of the cell
                const membersCell = row.insertCell(4); // Insert a cell for the Members
                membersCell.textContent = department.members; // Set the text content of the cell
                const isRunningCell = row.insertCell(5); // Insert a cell for the IsRunning
                isRunningCell.textContent = department.isRunning; // Set the text content of the cell
                const operateCell = row.insertCell(6); // Insert a cell for Operating

                // // Create the anchor (a) element
                // const add_anchor = document.createElement('a');
                // // Set the href attribute (it can be a URL or '#' for no navigation)
                // add_anchor.href = '#';
                // // Set the text content of the anchor
                // add_anchor.textContent = 'Add';
                // // Add an ID (optional, if you want to reference it later)
                // add_anchor.id = 'add_link_' + department.id;
                // // Bind the click event handler
                // add_anchor.addEventListener('click', function(event) {
                //     event.preventDefault();  // Prevent default action (navigation)
                //     // Select the modal element
                //     document.getElementById('edit_id').value = 0;
                //     let current_department = getRowData(this.id);
                //     // document.getElementById('edit_id').value = current_department.id;
                //     document.getElementById('edit_name').value = current_department.name;
                //     document.getElementById('edit_address').value = current_department.address;
                //     document.getElementById('edit_code').value = current_department.code;
                //     document.getElementById('edit_members').value = current_department.members;
                //     document.getElementById('edit_isrunning').checked = current_department.isRunning;
                //     document.getElementById('btn_save_department').setAttribute('onclick', 'add_department_req(' + current_department.id + '); return false;');
                //     document.getElementById('myModalLabel').textContent = 'Add Department';
                //     edit_modal.show();
                // });
                // // Append the anchor tag to the div with ID "link-container"
                // operateCell.appendChild(add_anchor);

                // const span1 = document.createElement('span');
                // span1.textContent = ' | ';
                // operateCell.appendChild(span1);

                // Create the anchor (button) element
                const edit_button = document.createElement('button');
                // Set the href attribute (it can be a URL or '#' for no navigation)
                // edit_button.href = '#';
                edit_button.className = 'btn btn-sm btn-warning';
                // Set the text content of the anchor
                edit_button.textContent = 'Edit';
                // Add an ID (optional, if you want to reference it later)
                edit_button.id = 'edit_link_' + department.id;
                // Bind the click event handler
                edit_button.addEventListener('click', function(event) {
                    event.preventDefault();  // Prevent default action (navigation)
                    // Select the modal element
                    let current_department = getRowData(this.id);
                    document.getElementById('edit_id').value = current_department.id;
                    document.getElementById('edit_name').value = current_department.name;
                    document.getElementById('edit_address').value = current_department.address;
                    document.getElementById('edit_code').value = current_department.code;
                    document.getElementById('edit_members').value = current_department.members;
                    document.getElementById('edit_isrunning').checked = current_department.isRunning;
                    document.getElementById('btn_save_department').setAttribute('onclick', 'update_department_req(' + current_department.id + '); return false;');
                    document.getElementById('myModalLabel').textContent = 'Update Department';
                    edit_modal.show();
                });
                // Append the anchor tag to the div with ID "link-container"
                operateCell.appendChild(edit_button);

                const span2 = document.createElement('span');
                span2.textContent = ' ';
                operateCell.appendChild(span2);
                
                // Create the anchor (a) element
                const delete_button = document.createElement('a');
                // Set the href attribute (it can be a URL or '#' for no navigation)
                // delete_button.href = '#';
                delete_button.className = 'btn btn-sm btn-danger';
                // Set the text content of the anchor
                delete_button.textContent = 'Delete';
                // Add an ID (optional, if you want to reference it later)
                delete_button.id = 'delete_link_' + department.id;
                // Bind the click event handler
                delete_button.addEventListener('click', function(event) {
                    event.preventDefault();  // Prevent default action (navigation)
                    delete_department_req(department.id);
                });
                // Append the anchor tag to the div with ID "link-container"
                operateCell.appendChild(delete_button);
            });

            display_pagination(data);
        } else {
            console.error('Failed to load departments:', xhr.statusText);
        }
    };

    // Handle any errors that occur during the request
    xhr.onerror = function() {
        console.error('Request error...');
    };

    // Send the request
    xhr.send();
}

/*
    currentPageIndex: The current page number, start with 0
*/
function load_departments(currentPageIndex) {
    // Create a new XMLHttpRequest object
    const xhr = new XMLHttpRequest();

    // Specify the type of request (GET) and the URL
    xhr.open('GET', 'http://localhost:8080/api/departments/fetchDepartmentsByPage?page=' + currentPageIndex + '&size=' + pageSize, true);

    // Set up a callback function to handle the response
    xhr.onload = function() {
        if (xhr.status === 200) {
            // Parse the JSON response
            const data = JSON.parse(xhr.responseText);
            // Get the table body element
            const tbody = document.getElementById('table_departments').getElementsByTagName('tbody')[0];
            // Clear the table before inserting new data
            tbody.innerHTML = '';
            // Iterate over the array of departments and create table rows
            data.content.forEach(department => {
                const row = tbody.insertRow(); // Insert a new row
                row.id = 'tr_' + department.id;
                const idCell = row.insertCell(0); // Insert a cell for the Id
                idCell.textContent = department.id;   // Set the text content of the cell
                const nameCell = row.insertCell(1); // Insert a cell for the Name
                nameCell.textContent = department.name; // Set the text content of the cell
                const addrCell = row.insertCell(2); // Insert a cell for the Address
                addrCell.textContent = department.address; // Set the text content of the cell
                const codeCell = row.insertCell(3); // Insert a cell for the Code
                codeCell.textContent = department.code; // Set the text content of the cell
                const membersCell = row.insertCell(4); // Insert a cell for the Members
                membersCell.textContent = department.members; // Set the text content of the cell
                const isRunningCell = row.insertCell(5); // Insert a cell for the IsRunning
                isRunningCell.textContent = department.isRunning; // Set the text content of the cell
                const operateCell = row.insertCell(6); // Insert a cell for Operating

                // // Create the anchor (a) element
                // const add_anchor = document.createElement('a');
                // // Set the href attribute (it can be a URL or '#' for no navigation)
                // add_anchor.href = '#';
                // // Set the text content of the anchor
                // add_anchor.textContent = 'Add';
                // // Add an ID (optional, if you want to reference it later)
                // add_anchor.id = 'add_link_' + department.id;
                // // Bind the click event handler
                // add_anchor.addEventListener('click', function(event) {
                //     event.preventDefault();  // Prevent default action (navigation)
                //     // Select the modal element
                //     document.getElementById('edit_id').value = 0;
                //     let current_department = getRowData(this.id);
                //     // document.getElementById('edit_id').value = current_department.id;
                //     document.getElementById('edit_name').value = current_department.name;
                //     document.getElementById('edit_address').value = current_department.address;
                //     document.getElementById('edit_code').value = current_department.code;
                //     document.getElementById('edit_members').value = current_department.members;
                //     document.getElementById('edit_isrunning').checked = current_department.isRunning;
                //     document.getElementById('btn_save_department').setAttribute('onclick', 'add_department_req(' + current_department.id + '); return false;');
                //     document.getElementById('myModalLabel').textContent = 'Add Department';
                //     edit_modal.show();
                // });
                // // Append the anchor tag to the div with ID "link-container"
                // operateCell.appendChild(add_anchor);

                // const span1 = document.createElement('span');
                // span1.textContent = ' | ';
                // operateCell.appendChild(span1);

                // <button class="btn btn-sm btn-warning" @click="openModal(product)">Edit</button>
                // &nbsp;
                // <button class="btn btn-sm btn-danger" @click="deleteProduct(product.id)">Delete</button>

                // Create the anchor (button) element
                const edit_button = document.createElement('button');
                // Set the href attribute (it can be a URL or '#' for no navigation)
                // edit_button.href = '#';
                edit_button.className = 'btn btn-sm btn-warning';
                // Set the text content of the anchor
                edit_button.textContent = 'Edit';
                // Add an ID (optional, if you want to reference it later)
                edit_button.id = 'edit_link_' + department.id;
                // Bind the click event handler
                edit_button.addEventListener('click', function(event) {
                    event.preventDefault();  // Prevent default action (navigation)
                    // Select the modal element
                    let current_department = getRowData(this.id);
                    document.getElementById('edit_id').value = current_department.id;
                    document.getElementById('edit_name').value = current_department.name;
                    document.getElementById('edit_address').value = current_department.address;
                    document.getElementById('edit_code').value = current_department.code;
                    document.getElementById('edit_members').value = current_department.members;
                    document.getElementById('edit_isrunning').checked = current_department.isRunning;
                    document.getElementById('btn_save_department').setAttribute('onclick', 'update_department_req(' + current_department.id + '); return false;');
                    document.getElementById('myModalLabel').textContent = 'Update Department';
                    edit_modal.show();
                });
                // Append the anchor tag to the div with ID "link-container"
                operateCell.appendChild(edit_button);

                const span2 = document.createElement('span');
                span2.textContent = ' ';
                operateCell.appendChild(span2);
                
                // Create the anchor (button) element
                const delete_button = document.createElement('button');
                // Set the href attribute (it can be a URL or '#' for no navigation)
                // delete_button.href = '#';
                delete_button.className = 'btn btn-sm btn-danger';
                // Set the text content of the anchor
                delete_button.textContent = 'Delete';
                // Add an ID (optional, if you want to reference it later)
                delete_button.id = 'delete_link_' + department.id;
                // Bind the click event handler
                delete_button.addEventListener('click', function(event) {
                    event.preventDefault();  // Prevent default action (navigation)
                    delete_department_req(department.id);
                });
                // Append the anchor tag to the div with ID "link-container"
                operateCell.appendChild(delete_button);
            });

            display_pagination(data);
        } else {
            console.error('Failed to load departments:', xhr.statusText);
        }
    };

    // Handle any errors that occur during the request
    xhr.onerror = function() {
        console.error('Request error...');
    };

    // Send the request
    xhr.send();
}

function add_department() {
    document.getElementById('edit_id').value = 0;
    document.getElementById('edit_name').value = '财务部';
    document.getElementById('edit_address').value = '1-3';
    document.getElementById('edit_code').value = '001';
    document.getElementById('edit_members').value = 3;
    document.getElementById('edit_isrunning').checked = true;
    document.getElementById('btn_save_department').setAttribute('onclick', 'add_department_req(); return false;');
    document.getElementById('myModalLabel').textContent = 'Add Department';
    edit_modal.show();
}

function add_department_req() {
    const url = 'http://localhost:8080/api/departments';
    // Data to be sent in the request body
    const data = {
        name: document.getElementById('edit_name').value,
        address: document.getElementById('edit_address').value,
        code: document.getElementById('edit_code').value,
        members: document.getElementById('edit_members').value,
        isRunning: document.getElementById('edit_isrunning').checked,
    };
    // Making the POST request using fetch API
    fetch(url, {
        method: 'POST', // or 'PUT'
        headers: {
            'Content-Type': 'application/json', // Sending JSON data
        },
        body: JSON.stringify(data), // Convert JavaScript object to JSON
    })
    .then(response => response.json()) // Parse the JSON response
    .then(data => {
        console.log('Success:', data);
        load_departments(currentPageIndex);
        edit_modal.hide();
    })
    .catch((error) => {
        console.error('Error:', error);
    });
}

function update_department_req(id) {
    const url = 'http://localhost:8080/api/departments/' + id;
    // Data to be sent in the request body
    const data = {
        id: document.getElementById('edit_id').value,
        name: document.getElementById('edit_name').value,
        address: document.getElementById('edit_address').value,
        code: document.getElementById('edit_code').value,
        members: document.getElementById('edit_members').value,
        isRunning: document.getElementById('edit_isrunning').checked,
    };
    // Making the PUT request using fetch API
    fetch(url, {
        method: 'PUT', // or 'POST'
        headers: {
            'Content-Type': 'application/json', // Sending JSON data
        },
        body: JSON.stringify(data), // Convert JavaScript object to JSON
    })
    .then(response => response.json()) // Parse the JSON response
    .then(data => {
        console.log('Success:', data);
        load_departments(currentPageIndex);
        edit_modal.hide();
    })
    .catch((error) => {
        console.error('Error:', error);
    });
}

function delete_department_req(id) {
    if(!id) {
        return;
    }
    const url = 'http://localhost:8080/api/departments/' + id;
    // Making the DELETE request using fetch API
    fetch(url, {
        method: 'DELETE', // or 'PUT'
        headers: {
            'Content-Type': 'application/json', // Sending JSON data
        }
    })
    .then(response => response.json()) // Parse the JSON response
    .then(data => {
        console.log('Success:', data);
        load_departments(currentPageIndex);
    })
    .catch((error) => {
        console.error('Error:', error);
    });
}