document.getElementById('role').addEventListener('change', function() {
    var selectedRole = this.value;
    var collegeInput = document.getElementById('collegeInput');
    var sectionInput = document.getElementById('sectionInput');

    // Hide collegeInput and sectionInput by default
    collegeInput.style.display = 'none';
    sectionInput.style.display = 'none';

    // Show collegeInput for admin role
    if (selectedRole === 'admin') {
        collegeInput.style.display = 'block';
    }

    // Show sectionInput for teacher role
    if (selectedRole === 'teacher') {
        sectionInput.style.display = 'block';
    }
});

document.getElementById('dataForm').addEventListener('submit', function(event) {
    event.preventDefault();
    var role = document.getElementById('role').value;
    var collegeId = document.getElementById('collegeId').value;
    var section = document.getElementById('section').value;

   
    var url = '/data?role=' + role;

    
    if (role === 'admin') {
        url += '&collegeId=' + collegeId;
    }
    if (role === 'teacher') {
        url += '&section=' + section;
    }

    
    fetch(url)
    .then(response => response.json())
    .then(data => {
        
        var resultElement = document.getElementById('result');
        resultElement.innerText = JSON.stringify(data);
    })
    .catch(error => console.error('Error:', error));
});
