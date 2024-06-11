document.getElementById('uploadFotoBtn').addEventListener('click', function() {
    const input = document.getElementById('fotoPerfil');
    const file = input.files[0];

    if (file) {
        const reader = new FileReader();
        reader.onload = function(e) {
            const photoUpload = document.querySelector('.photo-upload');
            const preview = photoUpload.querySelector('.photo-preview');
            preview.style.backgroundImage = `url('${e.target.result}')`;
        }
        reader.readAsDataURL(file);
    }
});

document.querySelectorAll('.btn-remove-photo').forEach(function(button) {
    button.addEventListener('click', function() {
        const photoUpload = button.closest('.photo-upload');
        const preview = photoUpload.querySelector('.photo-preview');
        preview.style.backgroundImage = '';
        const input = photoUpload.querySelector('input[type="file"]');
        input.value = '';
    });
});
