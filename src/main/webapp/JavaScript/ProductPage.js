document.addEventListener('DOMContentLoaded', function() {
    const imageElements = document.querySelectorAll('.image-container img');
    const imagePaths = [
        '../image/my_house.jpg',
        '../image/my_house2.jpg',
        '../image/my_house3.jpg'
        // Add more image paths as needed
    ];

    let currentIndex = 0;

    // Function to update image sources
    function updateImages() {
        imageElements.forEach((img, index) => {
            img.src = imagePaths[(currentIndex + index) % imagePaths.length];
        });
        currentIndex = (currentIndex + 1) % imagePaths.length;
    }

    // Automatically update images every 3 seconds
    setInterval(updateImages, 3000);
});