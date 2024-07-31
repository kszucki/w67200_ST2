$(document).ready(function() {
    // Call the function to set up the event handler for the delete button
    setUpDeleteBtnHandler();

    $("#searchInput").keyup(function() {
        var query = $(this).val();
        $.get("/policies/search", { query: query }, function(data) {
            $("tbody").empty();
            $.each(data, function(index, policy) {
                var startDate = new Date(policy.startDate).toLocaleDateString('en-GB');
                var endDate = new Date(policy.endDate).toLocaleDateString('en-GB');
                var installmentDate = new Date(policy.installmentDate).toLocaleDateString('en-GB');
                $("tbody").append(
                    "<tr>" +
                    "<td>" + policy.user.firstName + " " + policy.user.lastName + "<br>" + policy.user.pesel + "</td>" +
                    "<td>" + policy.coOwnFirstName + "<br>" + policy.coOwnLastName + "</td>" +
                    "<td>" + policy.insuranceComp + "<br>" + policy.policyNumber + "<br>" + policy.discount + "</td>" +
                    "<td>" + startDate + "<br>" + endDate + "<br>" + installmentDate + "</td>" +
                    "<td>" + policy.vehicleType + "<br>" + policy.regNumber + "</td>" +
                    "<td>" +
                    "<div>" +
                    "<a href='/policies/edit/" + policy.idPolicy + "' class='btn btn-primary'>Edytuj</a>" +
                    "<a href='/policies/delete/" + policy.idPolicy + "' class='btn btn-danger delBtn'>Usun</a>" +
                    "</div>" +
                    "</td>" +
                    "</tr>"
                );
            });
            // Call the function to set up the event handler for the delete button
            setUpDeleteBtnHandler();
        });
    });
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
