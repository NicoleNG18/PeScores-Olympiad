package pmgkn.pescores.pescores.web;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pmgkn.pescores.pescores.domain.dto.binding.UserRegistrationBindingDto;
import pmgkn.pescores.pescores.service.SchoolService;
import pmgkn.pescores.pescores.service.UserService;

@Controller
@RequestMapping("/users")
public class UserRegisterController {

    private final UserService userService;
    private final SchoolService schoolService;

    public UserRegisterController(UserService userService,
                                  SchoolService schoolService) {
        this.userService = userService;
        this.schoolService = schoolService;
    }

    @ModelAttribute("registerDto")
    public UserRegistrationBindingDto initRegisterDto() {
        return new UserRegistrationBindingDto();
    }

    @GetMapping("/register")
    public String getRegister(Model model) {

        model.addAttribute("schools",this.schoolService.getAllSchools());

        return "register";
    }

    @PostMapping("/register")
    public String postRegister(@Valid UserRegistrationBindingDto registerDto,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("registerDto", registerDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerDto"
                    , bindingResult);

            return "redirect:/users/register";
        }

        this.userService.registerUser(registerDto);

        return "redirect:/users/login";
    }
}
