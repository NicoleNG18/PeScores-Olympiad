package pmgkn.pescores.pescores.web;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pmgkn.pescores.pescores.domain.dto.UserRegistrationDTO;
import pmgkn.pescores.pescores.service.UserService;

@Controller
@RequestMapping("/users")
public class UserRegisterController {

    private final UserService userService;

    public UserRegisterController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("registerDto")
    public UserRegistrationDTO initRegisterDto() {
        return new UserRegistrationDTO();
    }

    @GetMapping("/register")
    public String getRegister(){
        return "auth-register";
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

        return "redirect:/users/login";
    }

}
