document.addEventListener('DOMContentLoaded', function() {
  document.getElementById('myForm').addEventListener('submit', function(event) {
    event.preventDefault();

    // Gather form data
    var formData = new FormData(this);

    // Check if all required fields are filled
    if (!formData.get('fullname') || !formData.get('email') || !formData.get('password')) {
      alert('Please fill in all required fields');
    } else {
      alert('User registered successfully');
      this.reset();  // Reset form fields after submission
      document.getElementById('thank-you-content').style.display = 'block';
    }
  });

  // Update file name when file is selected
  const fileInput = document.getElementById('propertyPhoto');
  fileInput.addEventListener('change', function() {
    updateFileName(this);
  });
});
