document.addEventListener('DOMContentLoaded', function () {
    document.getElementById('signup-form').addEventListener('submit', function (event) {
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
        fetch('http://127.0.0.1:8050/signup', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: jsonData
        })
        .then(response => {
            if (response.ok) {
                // Redirect to the LoginScreen
                window.location.href = 'login.html';
            } else {
                // Display error message
                alert('Account creation failed. Please try again.');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            // Display error message
            alert('An error occurred. Please try again later.');
        });
    });
});
