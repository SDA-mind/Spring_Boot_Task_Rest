$(document).ready(function() {
    $.getJSON('http://localhost:8080/admin/userlist', function(json) {
        var tr=[];
        for (var i = 0; i < json.length; i++) {
            let auth = "";
            for(let pop = 0; pop < json[i]["authorities"].length; pop++) {
                auth += json[i]["authorities"][pop].authority;
            }
            tr.push('<tr>');
            tr.push('<td>' + json[i].firstName + '</td>');
            tr.push('<td>' + json[i].lastName + '</td>');
            tr.push('<td>' + json[i].username + '</td>');
            tr.push('<td>' + json[i].password + '</td>');
            tr.push('<td>' + json[i].date + '</td>');
            tr.push('<td>' + json[i].lastName + '</td>');
            tr.push('<td>' + a + '</td>');
            tr.push('<td><button class=\'edit\'>Edit</button>&nbsp;&nbsp;<button class=\'delete\' id=' + json[i].id + '>Delete</button></td>');
            tr.push('</tr>');
        }
        $('table').append($(tr.join('')));
    });

    $(document).delegate('#addNew', 'click', function(event) {
        event.preventDefault();

        var name = $('#name').val();

        $.ajax({
            type: "POST",
            contentType: "application/json; charset=utf-8",
            url: "http://localhost:8080/company/save",
            data: JSON.stringify({'name': name}),
            cache: false,
            success: function(result) {
                $("#msg").html( "<span style='color: green'>Company added successfully</span>" );
                window.setTimeout(function(){location.reload()},1000)
            },
            error: function(err) {
                $("#msg").html( "<span style='color: red'>Name is required</span>" );
            }
        });
    });

    $(document).delegate('.delete', 'click', function() {
        if (confirm('Do you really want to delete record?')) {
            var id = $(this).attr('id');
            var parent = $(this).parent().parent();
            $.ajax({
                type: "DELETE",
                url: "http://localhost:8080/company/delete/" + id,
                cache: false,
                success: function() {
                    parent.fadeOut('slow', function() {
                        $(this).remove();
                    });
                    location.reload(true)
                },
                error: function() {
                    $('#err').html('<span style=\'color:red; font-weight: bold; font-size: 30px;\'>Error deleting record').fadeIn().fadeOut(4000, function() {
                        $(this).remove();
                    });
                }
            });
        }
    });

    $(document).delegate('.edit', 'click', function() {
        var parent = $(this).parent().parent();

        var id = parent.children("td:nth-child(1)");
        var name = parent.children("td:nth-child(2)");
        var buttons = parent.children("td:nth-child(3)");

        name.html("<input type='text' id='txtName' value='" + name.html() + "'/>");
        buttons.html("<button id='save'>Save</button>&nbsp;&nbsp;<button class='delete' id='" + id.html() + "'>Delete</button>");
    });

    $(document).delegate('#save', 'click', function() {
        var parent = $(this).parent().parent();

        var id = parent.children("td:nth-child(1)");
        var name = parent.children("td:nth-child(2)");
        var buttons = parent.children("td:nth-child(3)");

        $.ajax({
            type: "POST",
            contentType: "application/json; charset=utf-8",
            url: "http://localhost:8080/company/save",
            data: JSON.stringify({'id' : id.html(), 'name' : name.children("input[type=text]").val()}),
            cache: false,
            success: function() {
                name.html(name.children("input[type=text]").val());
                buttons.html("<button class='edit' id='" + id.html() + "'>Edit</button>&nbsp;&nbsp;<button class='delete' id='" + id.html() + "'>Delete</button>");
            },
            error: function() {
                $('#err').html('<span style=\'color:red; font-weight: bold; font-size: 30px;\'>Error updating record').fadeIn().fadeOut(4000, function() {
                    $(this).remove();
                });
            }
        });
    });

})