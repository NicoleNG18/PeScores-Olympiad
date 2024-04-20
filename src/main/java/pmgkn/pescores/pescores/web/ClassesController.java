package pmgkn.pescores.pescores.web;

import jakarta.validation.Valid;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pmgkn.pescores.pescores.domain.dto.binding.ClassAddBindingDto;
import pmgkn.pescores.pescores.domain.dto.binding.ClassEditBindingDto;
import pmgkn.pescores.pescores.domain.dto.binding.StudentUpdateBindingDto;
import pmgkn.pescores.pescores.domain.dto.view.ClassViewDto;
import pmgkn.pescores.pescores.domain.entity.ClassEntity;
import pmgkn.pescores.pescores.domain.entity.UserEntity;
import pmgkn.pescores.pescores.service.ClassesService;
import pmgkn.pescores.pescores.service.UserService;

import java.security.Principal;
import java.util.UUID;

@Controller
@RequestMapping("/classes")
public class ClassesController {

    private final ClassesService classesService;
    private final UserService userService;

    public ClassesController(ClassesService classesService,
                             UserService userService) {
        this.classesService = classesService;
        this.userService = userService;
    }

    @ModelAttribute("classAddDto")
    public ClassAddBindingDto initClassAddDto() {
        return new ClassAddBindingDto();
    }

    @ModelAttribute("classEdit")
    public ClassEditBindingDto initClassEditDto() {
        return new ClassEditBindingDto();
    }

    @GetMapping
    public String getClasses(Model model,
                             Principal principal) {

        model.addAttribute("classes", this.classesService.getAllClassesByUser(principal.getName()));
        model.addAttribute("allClasses", this.classesService.getAllClassesInSchool(principal.getName()));

        return "classes";
    }

    @GetMapping("/add")
    public String getAddClass(Model model,
                              Principal principal) {

        model.addAttribute("teachers", this.userService.getTeachers(principal.getName()));

        return "add-class";
    }

    @PostMapping("/add")
    public String postAddClass(@Valid ClassAddBindingDto classAddBindingDto,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes,
                               Principal principal) {


        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("classAddDto", classAddBindingDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.classAddDto"
                    , bindingResult);

            return "redirect:/classes/add";
        }

        if (this.classesService.checkIfClassNameRepeats(principal.getName(), classAddBindingDto.getClassName())) {

            redirectAttributes.addFlashAttribute("isUnique", false);

            return "redirect:/classes/add";
        }

        this.classesService.saveClass(classAddBindingDto, classAddBindingDto.getTeacher());

        return "redirect:/classes";
    }


    @GetMapping("/{id}")
    public String getCurrentClass(@PathVariable("id") UUID id,
                                  Model model,
                                  Principal principal) {

        if (!this.classesService.getClassById(id).getTeacher().getSchool().getSchoolName().equals(this.userService.getUserByEmail(principal.getName()).getSchool().getSchoolName())) {
            throw new ObjectNotFoundException("the info is not yours", ClassEntity.class);
        }

        model.addAttribute("classes", this.classesService.getAllClassesByUser(principal.getName()));
        model.addAttribute("currentClass", this.classesService.getClassById(id));
        model.addAttribute("students", this.classesService.getStudentsSorted(id));
        model.addAttribute("studentUpdate", new StudentUpdateBindingDto());
        model.addAttribute("id", id);

        return "current-class";
    }

    @GetMapping("/edit/{id}")
    public String getEditClass(@PathVariable("id") UUID id,
                               Model model,
                               Principal principal) {

        model.addAttribute("class", this.classesService.getClassById(id));
        model.addAttribute("teachers", this.userService.getTeachers(principal.getName()));

        return "classes-edit";
    }

    @PatchMapping("/edited/{id}")
    public String editClass(
            @PathVariable("id") UUID id,
            @Valid ClassEditBindingDto classEdit,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("classEdit", classEdit);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.classEdit"
                    , bindingResult);

            return "redirect:/classes/edit/{id}";
        }

        this.classesService.editClass(id, classEdit);

        return "redirect:/classes";
    }

    @PostMapping("/delete/{id}")
    public String deleteClass(@PathVariable("id") UUID id,
                              Principal principal) {

        ClassViewDto classById = this.classesService.getClassById(id);
        UserEntity loggedUser = this.userService.getUserByEmail(principal.getName());

        if (!classById.getTeacher().getSchool().equals(loggedUser.getSchool())) {
            throw new ObjectNotFoundException("the info is not yours", ClassEntity.class);
        }

        this.classesService.deleteClass(id);

        return "redirect:/classes";
    }
}
