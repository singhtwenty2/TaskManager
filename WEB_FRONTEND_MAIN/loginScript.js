document.addEventListener('DOMContentLoaded', function () {
    document.getElementById('login-form').addEventListener('submit', function (event) {
        event.preventDefault(); // prevent default form submission

        // Get form data
        var formData = new FormData(this);

        // Convert formData to JSON object
        var jsonObject = {};
        formData.forEach(function (value, key) {
            jsonObject[key] = value;
        }); 

        // Convert JSON object to string
        var jsonData = JSON.stringify(jsonObject);

        // Send form data to the server using AJAX
        fetch('http://127.0.0.1:8050/signin', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: jsonData
        })
        .then(response => {
            if (response.ok) {
                // Parse the response to get the JWT token
                return response.json();
            } else {
                throw new Error('Login failed');
            }
        })
        .then(data => {
            // Save JWT token in local storage
            localStorage.setItem('jwtToken', data.token);

            // Redirect to the HomeScreen
            window.location.href = 'home.html';
        })
        .catch(error => {
            console.error('Error:', error);
            // Display error message
            alert('Login failed. Please check your credentials and try again.');
        });
    });
});
