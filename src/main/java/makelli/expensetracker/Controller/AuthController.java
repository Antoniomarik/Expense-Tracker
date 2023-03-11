package makelli.expensetracker.Controller;

import lombok.AllArgsConstructor;
import makelli.expensetracker.DTO.UserDTO;
import makelli.expensetracker.Entity.User;
import makelli.expensetracker.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.naming.Binding;
import javax.validation.Valid;

@Controller

public class AuthController {
    @Autowired
    UserService userService;


    @GetMapping({"/login","/"})
    public String showLoginPage(){
       Authentication auth = SecurityContextHolder.getContext().getAuthentication();

       if (auth == null|| auth instanceof AnonymousAuthenticationToken){
           return "login";
       }

        return "redirect:/expenses";
    }

    @GetMapping("register")
    public String showRegisterPage (Model model){
        model.addAttribute("user", new UserDTO());

        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") UserDTO userDTO,
                           BindingResult result,
                           Model model
                           ){
        if(result.hasErrors()){
            return "register";
        }
      userService.saveUser(userDTO);
      model.addAttribute("successMsg",true);
        return "response";
    }

}
