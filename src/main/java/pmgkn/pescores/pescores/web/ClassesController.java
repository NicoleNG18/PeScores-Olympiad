package pmgkn.pescores.pescores.web;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pmgkn.pescores.pescores.domain.dto.binding.ClassAddBindingDto;
import pmgkn.pescores.pescores.domain.dto.binding.ClassEditBindingDto;
import pmgkn.pescores.pescores.service.ClassesService;

import java.security.Principal;
import java.util.UUID;

@Controller
@RequestMapping("/classes")
public class ClassesController {

    private final ClassesService classesService;

    public ClassesController(ClassesService classesService) {
        this.classesService = classesService;
    }

    @ModelAttribute("classAddDto")
    public ClassAddBindingDto initClassAddDto() {
        return new ClassAddBindingDto();
    }

    @ModelAttribute("classEdit")
    public ClassEditBindingDto initClassEdit() {
        return new ClassEditBindingDto();
    }

    @GetMapping
    public String getClasses(Model model,
                             Principal principal){

        model.addAttribute("classes", this.classesService.getAllClassesByUser(principal.getName()));

        return "classes";
    }

    @GetMapping("/add")
    public String getAddClass(){
        return "add-class";
    }

    @PostMapping("/add")
    public String postAddClass(@Valid ClassAddBindingDto classAddBindingDto,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes,
                                 Principal principal){


        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("classAddDto", classAddBindingDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.classAddDto"
                    , bindingResult);

            return "redirect:/classes/add";

        }

        UUID classId = this.classesService.saveClass(classAddBindingDto, principal.getName());

        return "redirect:/classes/"+ classId;
    }

    @GetMapping("/{id}")
    public String getCurrentClass(@PathVariable("id") UUID id,
                                  Model model,
                                  Principal principal) {

        model.addAttribute("classes", this.classesService.getAllClassesByUser(principal.getName()));
        model.addAttribute("currentClass", this.classesService.getClassById(id));
        model.addAttribute("students",this.classesService.getClassById(id).getStudents());
        model.addAttribute("id", id);

        return "current-class";
    }

    @GetMapping("/edit/{id}")
    public String getEditClass(@PathVariable("id") UUID id,
                               Model model){

        model.addAttribute("class",this.classesService.getClassById(id));

        return "classes-edit";
    }

    @PatchMapping("/edited/{id}")
    public String editClass(@PathVariable("id") UUID id,
                            @Valid ClassAddBindingDto classEdit,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("classEdit", classEdit);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.classEdit"
                    , bindingResult);

            return "redirect:/classes/edit/{id}";

        }

        this.classesService.editClass(id, classEdit);

        return "redirect:/classes/" + id;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteClass(@PathVariable("id") UUID id) {

        this.classesService.deleteClass(id);

        return "redirect:/classes";
    }


}
