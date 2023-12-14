package pmgkn.pescores.pescores.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String getHome(){
        return "home";
    }

    @GetMapping("/contact")
    public String getContact(){
        return "contact";
    }

    @GetMapping("/register")
    public String getRegister(){
        return "register";
    }


    @GetMapping("/login")
    public String getLogin(){
        return "login";
    }

}
