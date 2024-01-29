package pmgkn.pescores.pescores.web;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pmgkn.pescores.pescores.domain.dto.binding.ClassAddBindingDto;
import pmgkn.pescores.pescores.service.ClassesService;

@Controller
@RequestMapping("/classes")
public class ClassesController {

    private final ClassesService classesService;

    public ClassesController(ClassesService classesService) {
        this.classesService = classesService;
    }

    @ModelAttribute("productDto")
    public ClassAddBindingDto initClassAddDto() {
        return new ClassAddBindingDto();
    }

    @GetMapping
    public String getClasses(){
        return "classes";
    }

    @GetMapping("/add")
    public String getAddClass(){
        return "add-class";
    }

    @PostMapping("/add")
    public String postAddClass(@Valid ClassAddBindingDto classAddBindingDto,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes){


        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("classAddDto", classAddBindingDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.classAddDto"
                    , bindingResult);

            return "redirect:/classes/add";

        }

        this.classesService.saveClass();

       return "classes";
    }

}
