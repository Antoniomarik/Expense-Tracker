$(function(){

    $.validator.addMethod('customEmail',function(value,element){
        return this.optional(element) || /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/.test(value);
    })

    const $regform = $('#regform');

    if ($regform.length){

        $regform.validate({

                rules:{
                    name:{
                        required: true,
                        minlength: 3
                    },
                    email:{
                        required:true,
                        customEmail:true
                    },
                    password:{
                        required:true,
                        minlength: 6,
                        maxlength: 15
                    },
                    confirmpassword:{
                        required:true,
                        equalTo:'#password'
                    }
                },
                messages:{
                    name:{
                        required:"Please enter valid name for user",
                        minlength:"Cmon, you can do better! Enter longer Name!"
                    },
                    email:{
                        required:"Please enter valid email for user",
                        customEmail:"Please enter valid email"
                    },
                    password:{
                        required:"Please enter valid password for user",
                        minlength:"Minimum 6 characters",
                        maxlength:"Password should be less than 15 characters"
                    },
                    confirmpassword:{
                        required:"PLease enter password",
                        equalTo:"Passwords not matching!"
                    }
                }
        })
    }
})