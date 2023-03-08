package makelli.expensetracker.Controller;

import lombok.AllArgsConstructor;
import makelli.expensetracker.DTO.UserDTO;
import makelli.expensetracker.Entity.User;
import makelli.expensetracker.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller

public class AuthController {
    @Autowired
    UserService userService;


    @GetMapping({"/login","/"})
    public String showLoginPage(){

        return "login";
    }

    @GetMapping("register")
    public String showRegisterPage (Model model){
        model.addAttribute("user", new UserDTO());

        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") UserDTO userDTO,Model model){
      userService.saveUser(userDTO);
      model.addAttribute("successMsg",true);
        return "login";
    }

}
