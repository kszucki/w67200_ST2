$(document).ready(function (){
    // Call the function to set up the event handler for the delete button
    setUpDeleteBtnHandler();

});

// Define a function to set up the event handler for the delete button
function setUpDeleteBtnHandler() {
    $('.table .delBtn').on('click', function (event) {
        event.preventDefault();
        const href = $(this).attr('href');
        $('#myModal #delRef').attr('href', href);
        $('#myModal').modal();
    });
}