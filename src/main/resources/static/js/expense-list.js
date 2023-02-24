$(function(){
    $("#startdate").datepicker({
    dateFormat: "dd/mm/yy",
    changeMonth: true,
    changeYear: true,
    maxDate: new Date()
    })

     $("#enddate").datepicker({
       dateFormat: "dd/mm/yy",
        changeMonth: true,
        changeYear: true,
        maxDate: new Date()
     })
})