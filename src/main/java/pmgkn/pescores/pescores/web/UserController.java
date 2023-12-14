package pmgkn.pescores.pescores.web;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pmgkn.pescores.pescores.domain.dto.UserRegistrationDTO;
import pmgkn.pescores.pescores.repositories.UserRepository;
import pmgkn.pescores.pescores.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserRepository userRepository;
    private UserService userService;

    @Autowired
    public UserController(UserRepository userRepository, UserService userService){
        this.userRepository=userRepository;
        this.userService=userService;
    }

    @GetMapping("/register")
    public String getRegister(){
        return "register";
    }
    @PostMapping("/register")
    public String postRegister(@Valid UserRegistrationDTO registerDto,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()){

            redirectAttributes.addFlashAttribute("registerDto",registerDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerDto"
                    ,bindingResult);

            return "redirect:/users/register";

        }

        this.userService.registerUser(registerDto);

        return "redirect:/login";
    }

}
