$(document).ready(function () {
    fetchEmployees();
});

function fetchEmployees() {
    $.ajax({
        url: "/employees/data",
        method: "GET",
        success: function (data) {
            const employeeTableBody = $("#employeeTableBody");
            employeeTableBody.empty();

            if (data.length === 0) {
                $("#employeeTable").hide();
                $("#noEmployeesMessage").show();
            } else {
                $("#employeeTable").show();
                $("#noEmployeesMessage").hide();

                data.forEach(employee => {
                    const row = $("<tr></tr>");
                    $("<td></td>").text(employee.employeeId).appendTo(row);
                    $("<td></td>").text(employee.firstName).appendTo(row);
                    $("<td></td>").text(employee.lastName).appendTo(row);
                    $("<td></td>").text(employee.emailId).appendTo(row);
                    $("<td></td>").text(employee.contactNumber).appendTo(row);
                    $("<td></td>").text(employee.department).appendTo(row);
                    $("<td></td>").text(employee.salary).appendTo(row);
                    $("<td></td>").text(employee.status).appendTo(row);
                    const actionTd = $("<td></td>");
                    const updateButton = $("<a></a>")
                        .addClass("btn btn-warning btn-sm")
                        .text("Update")
                        .attr("href", `/employees/edit/${employee.employeeId}`);

                    const deleteButton = $("<button></button>")
                        .addClass("btn btn-danger btn-sm")
                        .text("Delete")
                        .on("click", function () {
                            deleteEmployee(employee.employeeId);
                        });

                    actionTd.append(updateButton).append(" ").append(deleteButton);
                    row.append(actionTd);

                    employeeTableBody.append(row);
                });
            }
        },
        error: function () {
            $("#employeeTable").hide();
            $("#noEmployeesMessage").text("Error fetching employees").show();
        }
    });
}

function deleteEmployee(employeeId) {
    if (confirm("Are you sure you want to delete this employee?")) {
        $.ajax({
            url: `/employees/delete/${employeeId}`,
            method: "POST",
            success: function (response) {
                alert(response);
                fetchEmployees();
            },
            error: function () {
                alert("Error deleting employee.");
            }
        });
    }
}
