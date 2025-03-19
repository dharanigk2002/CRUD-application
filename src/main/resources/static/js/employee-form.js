$(document).ready(function () {
    $("#employeeForm").submit(function (event) {
        event.preventDefault();
        $(".text-danger").addClass("d-none");

        let isValid = true;

        const firstName = $("#firstName").val().trim();
        if (firstName === "") {
            $("#firstNameError").removeClass("d-none");
            isValid = false;
        }

        const lastName = $("#lastName").val().trim();
        if (lastName === "") {
            $("#lastNameError").removeClass("d-none");
            isValid = false;
        }

        const email = $("#emailId").val().trim();
        const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
        if (email === "" ) {
            $("#emailError").text("Email id is required").removeClass("d-none");
            isValid = false;
        }
        else if(!emailPattern.test(email)) {
            $("#emailError").removeClass("d-none");
            isValid = false;
        }

        const contact = $("#contactNumber").val().trim();
        const contactPattern = /^[0-9]{10}$/;
        if (contact === "" || !contactPattern.test(contact)) {
            $("#contactError").removeClass("d-none");
            isValid = false;
        }

        const department = $("#department").val().trim();
        if (department === "") {
            $("#departmentError").removeClass("d-none");
            isValid = false;
        }

        const salary = parseFloat($("#salary").val().trim());
        if (isNaN(salary) || salary <= 0) {
            $("#salaryError").removeClass("d-none");
            isValid = false;
        }

        if (!isValid) return;

        const formData = $(this).serialize();
        $.ajax({
            url: "/employees/save",
            type: "POST",
            data: formData,
            success: function (response) {
                alert(response);
                window.location.href = "/employees";
            },
            error: function (xhr) {
                const errorMessage = JSON.parse(xhr.responseText);
                $("#error-message").text(errorMessage.message).removeClass("d-none");
            }
        });
    });
});
