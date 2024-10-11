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

                const deleteCell = row.insertCell(6); // Insert a cell for the Delete
                // Create the anchor (a) element
                const anchor = document.createElement('a');
                // Set the href attribute (it can be a URL or '#' for no navigation)
                anchor.href = '#';
                // Set the text content of the anchor
                anchor.textContent = 'Delete';
                // Add an ID (optional, if you want to reference it later)
                anchor.id = 'dynamic_link_' + department.departmentId;
                // Append the anchor tag to the div with ID "link-container"
                deleteCell.appendChild(anchor);
                // Bind the click event handler
                anchor.addEventListener('click', function(event) {
                    event.preventDefault();  // Prevent default action (navigation)
                    delete_department_req(department.departmentId);
                });
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

function add_department_req() {
    const url = 'http://localhost:8080/api/departments';
    // Data to be sent in the request body
    const data = {
        departmentName: document.getElementById('name').value,
        departmentAddress: document.getElementById('address').value,
        departmentCode: document.getElementById('code').value,
        departmentMembers: document.getElementById('members').value,
        departmentIsRunning: document.getElementById('isrunning').checked,
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