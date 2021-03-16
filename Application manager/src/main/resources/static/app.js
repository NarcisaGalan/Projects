
$(document).ready(function () {

    $(' .addAppBtn, .table .addAppBtn').on('click',function(event) {
        event.preventDefault();
        var href = $(this).attr('href');
        var text= $(this).text();

        if(text==='Edit') {
            $.get(href, function (app,status) {
                $('.myForm #id').val(app.id);
                $('.myForm #name').val(app.name);
                $('.myForm #technologies').val(app.technologies);
                $('.myForm #version').val(app.version);

            });
            $('.myForm #exampleModal').modal();
        }else{
            $('.myForm #id').val('');
            $('.myForm #name').val('');
            $('.myForm #technologies').val('');
            $('.myForm #version').val('');
            $('.myForm #exampleModal').modal();

        }

    });

    $('.table .deleteAppBtn').on('click',function(event){
        event.preventDefault();
        var href = $(this).attr('href');

        $('#myModal #delRef').attr('href',href);
        $('#myModal').modal();
    });

});