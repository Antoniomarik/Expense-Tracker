$(function(){
    $('#createdAt').datepicker({
    dateFormat:"dd/mm/yy",
    changeMonth: true,
    changeYear: true,
    maxDate: new Date()
    });

    const $expenseForm = $("#expenseForm");

    if($expenseForm.length){
    $expenseForm.validate({

        rules:{
            name:{
                required:true,
                minlength: 3
            },
            amount: {
                required:true,
                },
            dateString:{
                required:true
                }
        },
        messages:{
                name:{
                    required: 'Please enter expanse name',
                    minlength: 'Expanse ame shpould be minimum 3'
                },
                amount:{
                    required:"this field is requred"
                    }
                dateString:{
                    required:"this field is required!"
                    }
            }
    })}
})