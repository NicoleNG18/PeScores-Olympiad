package pmgkn.pescores.pescores.web;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pmgkn.pescores.pescores.domain.dto.binding.SchoolAddBindingDto;
import pmgkn.pescores.pescores.service.SchoolService;
import pmgkn.pescores.pescores.service.UserService;

import java.util.UUID;

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
    public String getAllSchools(Model model) {

        model.addAttribute("schools", this.schoolService.getAllSchools());

        return "all-schools";
    }

    @GetMapping("/{id}")
    public String getSchool(@PathVariable("id") UUID id,
                            Model model) {

        model.addAttribute("currentSchool", this.schoolService.getSchoolById(id));
        model.addAttribute("teachers",this.schoolService.getSchoolById(id).getTeachers());

        return "school";
    }


}
