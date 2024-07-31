$(document).ready(function() {

    setUpDeleteBtnHandler();

    $("#searchInput").keyup(function() {
        var query = $(this).val();
        $.get("/users/search", { query: query }, function(data) {
            $("tbody").empty();
            $.each(data, function(index, user) {
                $("tbody").append(
                    "<tr>" +
                    "<td>" + user.firstName + "</td>" +
                    "<td>" + user.lastName + "</td>" +
                    "<td>" + user.pesel + "</td>" +
                    "<td>" + user.address + "</td>" +
                    "<td>" + user.email + "</td>" +
                    "<td>" +
                    "<a href='/users/" + user.idUsers + "/policies' class='btn btn-success'>Polisy</a>" +
                    "<div class='btn-group' role='group' aria-label='Basic example'>" +
                    "<a href='/users/" + user.idUsers + "' class='btn btn-danger delBtn'>Usun</a>" +
                    "<a href='/users/edit/" + user.idUsers + "' class='btn btn-primary'>Edytuj</a>" +
                    "</div>" +
                    "</td>" +
                    "</tr>"
                );
            });

            setUpDeleteBtnHandler();
        });
    });
});
function setUpDeleteBtnHandler() {
    $('.table .delBtn').on('click', function (event) {
        event.preventDefault();
        const href = $(this).attr('href');
        $('#myModal #delRef').attr('href', href);
        $('#myModal').modal();
    });
}