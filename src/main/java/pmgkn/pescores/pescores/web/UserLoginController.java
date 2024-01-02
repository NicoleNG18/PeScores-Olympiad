package pmgkn.pescores.pescores.web;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pmgkn.pescores.pescores.domain.dto.UserLoginDto;

@Controller
@RequestMapping("/users")
public class UserLoginController {

    @GetMapping("/login")
    public String login(Model model) {
        if (!model.containsAttribute("userLoginDto")) {
            model.addAttribute("userLoginDto", new UserLoginDto());
            model.addAttribute("bad_credentials", false);
        }
        return "login";
    }

    @PostMapping("/login-error")
    public String errorLogin(UserLoginDto userLoginDto,
                             RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("userLoginDto", userLoginDto);
        redirectAttributes.addFlashAttribute("bad_credentials", true);
        return "redirect:/users/login";
    }

}