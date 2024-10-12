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
    let isrunning = cells[5].innerText == "true" ? true : false;

    // Output the data (you can also return it or use it in your logic)
    console.log("ID: " + id + ", Name: " + name + ", Addr: " + address + ", Code: " + code + ", Members: " + members + ", IsRunning: " + isrunning);

    return {
        departmentId: id,
        departmentName: name,
        departmentAddress: address,
        departmentCode: code,
        departmentMembers: members,
        departmentIsRunning: isrunning,
    };
}

function load_departments() {
    // Create a new XMLHttpRequest object
    const xhr = new XMLHttpRequest();

    // Specify the type of request (GET) and the URL
    xhr.open('GET', 'http://localhost:8080/api/departments', true);

    // Set up a callback function to handle the response
    xhr.onload = function() {
        if (xhr.status === 200) {
            // Parse the JSON response
            const departments = JSON.parse(xhr.responseText);
            // Get the table body element
            const tbody = document.getElementById('table_departments').getElementsByTagName('tbody')[0];
            // Clear the table before inserting new data
            tbody.innerHTML = '';
            // Iterate over the array of departments and create table rows
            departments.forEach(department => {
                const row = tbody.insertRow(); // Insert a new row
                row.id = 'tr_' + department.departmentId;
                const idCell = row.insertCell(0); // Insert a cell for the Id
                idCell.textContent = department.departmentId;   // Set the text content of the cell
                const nameCell = row.insertCell(1); // Insert a cell for the Name
                nameCell.textContent = department.departmentName; // Set the text content of the cell
                const addrCell = row.insertCell(2); // Insert a cell for the Address
                addrCell.textContent = department.departmentAddress; // Set the text content of the cell
                const codeCell = row.insertCell(3); // Insert a cell for the Code
                codeCell.textContent = department.departmentCode; // Set the text content of the cell
                const membersCell = row.insertCell(4); // Insert a cell for the Members
                membersCell.textContent = department.departmentMembers; // Set the text content of the cell
                const isRunningCell = row.insertCell(5); // Insert a cell for the IsRunning
                isRunningCell.textContent = department.departmentIsRunning; // Set the text content of the cell
                const operateCell = row.insertCell(6); // Insert a cell for Operating

                // Create the anchor (a) element
                const add_anchor = document.createElement('a');
                // Set the href attribute (it can be a URL or '#' for no navigation)
                add_anchor.href = '#';
                // Set the text content of the anchor
                add_anchor.textContent = 'Add';
                // Add an ID (optional, if you want to reference it later)
                add_anchor.id = 'add_link_' + department.departmentId;
                // Bind the click event handler
                add_anchor.addEventListener('click', function(event) {
                    event.preventDefault();  // Prevent default action (navigation)
                    // Select the modal element
                    document.getElementById('edit_id').value = 0;
                    let current_department = getRowData(this.id);
                    // document.getElementById('edit_id').value = current_department.departmentId;
                    document.getElementById('edit_name').value = current_department.departmentName;
                    document.getElementById('edit_address').value = current_department.departmentAddress;
                    document.getElementById('edit_code').value = current_department.departmentCode;
                    document.getElementById('edit_members').value = current_department.departmentMembers;
                    document.getElementById('edit_isrunning').checked = current_department.departmentIsRunning;
                    document.getElementById('btn_save_department').setAttribute('onclick', 'add_department_req(' + current_department.departmentId + '); return false;');
                    document.getElementById('myModalLabel').textContent = 'Add Department';
                    edit_modal.show();
                });
                // Append the anchor tag to the div with ID "link-container"
                operateCell.appendChild(add_anchor);

                const span1 = document.createElement('span');
                span1.textContent = ' | ';
                operateCell.appendChild(span1);

                // Create the anchor (a) element
                const edit_anchor = document.createElement('a');
                // Set the href attribute (it can be a URL or '#' for no navigation)
                edit_anchor.href = '#';
                // Set the text content of the anchor
                edit_anchor.textContent = 'Edit';
                // Add an ID (optional, if you want to reference it later)
                edit_anchor.id = 'edit_link_' + department.departmentId;
                // Bind the click event handler
                edit_anchor.addEventListener('click', function(event) {
                    event.preventDefault();  // Prevent default action (navigation)
                    // Select the modal element
                    let current_department = getRowData(this.id);
                    document.getElementById('edit_id').value = current_department.departmentId;
                    document.getElementById('edit_name').value = current_department.departmentName;
                    document.getElementById('edit_address').value = current_department.departmentAddress;
                    document.getElementById('edit_code').value = current_department.departmentCode;
                    document.getElementById('edit_members').value = current_department.departmentMembers;
                    document.getElementById('edit_isrunning').checked = current_department.departmentIsRunning;
                    document.getElementById('btn_save_department').setAttribute('onclick', 'update_department_req(' + current_department.departmentId + '); return false;');
                    document.getElementById('myModalLabel').textContent = 'Update Department';
                    edit_modal.show();
                });
                // Append the anchor tag to the div with ID "link-container"
                operateCell.appendChild(edit_anchor);

                const span2 = document.createElement('span');
                span2.textContent = ' | ';
                operateCell.appendChild(span2);
                
                // Create the anchor (a) element
                const delete_anchor = document.createElement('a');
                // Set the href attribute (it can be a URL or '#' for no navigation)
                delete_anchor.href = '#';
                // Set the text content of the anchor
                delete_anchor.textContent = 'Delete';
                // Add an ID (optional, if you want to reference it later)
                delete_anchor.id = 'delete_link_' + department.departmentId;
                // Bind the click event handler
                delete_anchor.addEventListener('click', function(event) {
                    event.preventDefault();  // Prevent default action (navigation)
                    delete_department_req(department.departmentId);
                });
                // Append the anchor tag to the div with ID "link-container"
                operateCell.appendChild(delete_anchor);
            });
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
        departmentName: document.getElementById('edit_name').value,
        departmentAddress: document.getElementById('edit_address').value,
        departmentCode: document.getElementById('edit_code').value,
        departmentMembers: document.getElementById('edit_members').value,
        departmentIsRunning: document.getElementById('edit_isrunning').checked,
    };
    // Making the POST request using fetch API
    fetch(url, {
        method: 'POST', // or 'PUT'
        headers: {
            'Content-Type': 'application/json', // Sending JSON data
            // 'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: JSON.stringify(data), // Convert JavaScript object to JSON
    })
    .then(response => response.json()) // Parse the JSON response
    .then(data => {
        console.log('Success:', data);
        load_departments();
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
        departmentId: document.getElementById('edit_id').value,
        departmentName: document.getElementById('edit_name').value,
        departmentAddress: document.getElementById('edit_address').value,
        departmentCode: document.getElementById('edit_code').value,
        departmentMembers: document.getElementById('edit_members').value,
        departmentIsRunning: document.getElementById('edit_isrunning').checked,
    };
    // Making the PUT request using fetch API
    fetch(url, {
        method: 'PUT', // or 'POST'
        headers: {
            'Content-Type': 'application/json', // Sending JSON data
            // 'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: JSON.stringify(data), // Convert JavaScript object to JSON
    })
    .then(response => response.json()) // Parse the JSON response
    .then(data => {
        console.log('Success:', data);
        load_departments();
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
        load_departments();
    })
    .catch((error) => {
        console.error('Error:', error);
    });
}