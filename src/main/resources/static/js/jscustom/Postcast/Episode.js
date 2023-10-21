const dropContainer = document.querySelector('.drop-container');
const dropMessage = document.querySelector('.drop-message');
const fileInput = document.getElementById('fileInput');
const audioPlayer = document.getElementById('audioPlayer');
const library = document.querySelector('.library');
const libraryFile = document.getElementById('library-file');

library.addEventListener('click', function(){
    libraryFile.click()
});

dropContainer.addEventListener('click', (e) => {
    fileInput.click()
})

dropContainer.addEventListener('dragleave', () => {
    dropContainer.classList.remove('dragover');
    dropMessage.textContent = 'Drag & Drop an audio file here';
});

dropContainer.addEventListener('dragover', (e) => {
    e.preventDefault();
    dropContainer.classList.add('dragover');
});

dropContainer.addEventListener('drop', (e) => {
    e.preventDefault();
    dropContainer.classList.remove('dragover');
    const file = e.dataTransfer.files[0];
    fileInput.files = e.dataTransfer.files;
    audioPlayer.src = URL.createObjectURL(file);
    audioPlayer.hidden = false;
    dropMessage.textContent = `Uploaded: ${file.name}`;
});

fileInput.addEventListener('change', () => {
    const file = fileInput.files[0];
    audioPlayer.src = URL.createObjectURL(file);
    audioPlayer.hidden = false;
    dropMessage.textContent = `Uploaded: ${file.name}`;
});

libraryFile.addEventListener('change', () =>{
    const file = libraryFile.files[0];
    audioPlayer.src = URL.createObjectURL(file);
    audioPlayer.hidden= false;
    dropMessage.textContent = `Uploaded: ${file.name}`;
})



