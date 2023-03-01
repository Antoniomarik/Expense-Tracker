$(function(){
    $('#createdAt').datepicker({
    dateFormat:"dd/mm/yy",
    changeMonth: true,
    changeYear: true,
    maxDate: new Date()
    });

    const $expenseForm = $("#expenseform");

    if($expenseForm.length){
    $expenseForm.validate({
        rules:{
            name:{
                required:true,
                minlength: 3
            }
        },
        messages:{
            name:{
                required: 'Please enter expanse name',
                minlength: 'Expanse ame shpould be minimum 3'
                }

            }
    })}
})