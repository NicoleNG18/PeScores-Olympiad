package pmgkn.pescores.pescores.web;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pmgkn.pescores.pescores.domain.dto.binding.SchoolAddBindingDto;
import pmgkn.pescores.pescores.service.SchoolService;
import pmgkn.pescores.pescores.service.UserService;

@Controller
@RequestMapping("/schools")
public class SchoolController {
    private final SchoolService schoolService;
    private final UserService userService;

    public SchoolController(SchoolService schoolService,
                             UserService userService) {
        this.schoolService = schoolService;
        this.userService = userService;
    }

    @ModelAttribute("schoolAddDto")
    public SchoolAddBindingDto initClassAddDto() {
        return new SchoolAddBindingDto();
    }

    @GetMapping("/add")
    public String getAddSchool() {
        return "add-school";
    }

    @PostMapping("/add")
    public String postAddSchool(@Valid SchoolAddBindingDto schoolAddDto,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("schoolAddDto", schoolAddDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.schoolAddDto"
                    , bindingResult);

            return "redirect:/schools/add";
        }

        this.schoolService.saveSchool(schoolAddDto);

        return "redirect:/schools/all";
    }

    @GetMapping("/all")
    public String getAllSchools() {
        return "all-schools";
    }

}
