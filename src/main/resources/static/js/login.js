$(function(){

    const $login = $("#login");

    if($login.length){
        $login.validate({
            rules:{
                email:{
                    required:true
                },
                password:{
                    required:true
                }
            },
            messages:{
                email:{
                    required:"Please enter valid email"
                },
                password:{
                    required:"Please enter password"
                }
            }
        })
    }
})