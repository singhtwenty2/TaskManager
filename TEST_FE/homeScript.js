// This file can be left empty for now, or you can add JavaScript functionality as needed.
const apiUrl = 'http://localhost:8050/tasks'
const jwtToken = localStorage.getItem('jwtToken')

document.getElementById('fetch').addEventListener('click', function () {

    fetch(apiUrl, {
        headers: {
            'Authorization': 'Bearer ' + jwtToken
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            console.log(data);
            // Update UI with task data
            for (let i = 0; i < data.length; i++) {
                document.getElementById('title').textContent = data[i].title;
                document.getElementById('description').textContent = data[i].description;
                document.getElementById('dueDate').textContent = data[i].dueDate;
                document.getElementById('priority').textContent = data[i].priority;
                document.getElementById('status').textContent = data[i].isDone ? 'Done' : 'Not Done';
            }
        })
        .catch(error => {
            console.error('There was a problem with the fetch operation:', error);
        });
});

