
$(document).ready(function () {

    $(' .addUserBtn, .table .addUserBtn').on('click',function(event) {
        event.preventDefault();
        var href = $(this).attr('href');
        var text= $(this).text();

        if(text==='Edit') {
            $.get(href, function (user,status) {
                $('.myForm #id').val(user.id);
                $('.myForm #name').val(user.name);
                $('.myForm #username').val(user.username);
                $('.myForm #password').val(user.password);

            });
            $('.myForm #exampleModal').modal();
        }else{
            $('.myForm #id').val('');
            $('.myForm #name').val('');
            $('.myForm #username').val('');
            $('.myForm #password').val('');
            $('.myForm #exampleModal').modal();

        }

    });

    $('.table .deleteUserBtn').on('click',function(event){
        event.preventDefault();
        var href = $(this).attr('href');

        $('#myModal #delRef').attr('href',href);
        $('#myModal').modal();
    });






    $(' .table .seeAppBtn ').on('click',function(event) {
        event.preventDefault();
        var href = $(this).attr('href');


            $.get(href, function (user, status) {
                $('.myForm #id').val(user.id);
                $('.myForm #name').val(user.name);
                $('.myForm #username').val(user.username);
                $('.myForm #password').val(user.password);

            });
            $('.myForm #exampleModal').modal();
        });

});