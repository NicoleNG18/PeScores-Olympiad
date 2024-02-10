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
    public ClassEditBindingDto initClassEdit() {
        return new ClassEditBindingDto();
    }

    @GetMapping
    public String getClasses(Model model,
                             Principal principal) {

        model.addAttribute("classes", this.classesService.getAllClassesByUser(principal.getName()));

        return "classes";
    }

    @GetMapping("/add")
    public String getAddClass() {
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

        UUID classId = this.classesService.saveClass(classAddBindingDto, principal.getName());
        UserEntity currentUser = this.userService.getUserByEmail(principal.getName());

        return "redirect:/classes/" + currentUser.getId() + "/" + classId;
    }

    @GetMapping("/{idTeacher}/{id}")
    public String getCurrentClass(@PathVariable("idTeacher") UUID idTeacher,
                                  @PathVariable("id") UUID id,
                                  Model model,
                                  Principal principal) {

        if (!this.userService.getUserById(idTeacher).equals(this.userService.getUserByEmail(principal.getName()))) {
            throw new ObjectNotFoundException("the info is not yours", ClassEntity.class);
        }


        model.addAttribute("classes", this.classesService.getAllClassesByUser(principal.getName()));
        model.addAttribute("currentClass", this.classesService.getClassById(id));
        model.addAttribute("students", this.classesService.getStudentsSorted(id));
        model.addAttribute("studentUpdate", new StudentUpdateBindingDto());
        model.addAttribute("id", id);

        return "current-class";
    }

    @GetMapping("/edit/{idTeacher}/{id}")
    public String getEditClass(@PathVariable("idTeacher") UUID idTeacher,
                               @PathVariable("id") UUID id,
                               Model model,
                               Principal principal) {

        if (!this.userService.getUserById(idTeacher).equals(this.userService.getUserByEmail(principal.getName()))) {
            throw new ObjectNotFoundException("the info is not yours", ClassEntity.class);
        }

        model.addAttribute("class", this.classesService.getClassById(id));

        return "classes-edit";
    }

    @PatchMapping("/edited/{id}")
    public String editClass(
                            @PathVariable("id") UUID id,
                            @Valid ClassAddBindingDto classEdit,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes,
                            Principal principal) {

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("classEdit", classEdit);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.classEdit"
                    , bindingResult);

            return "redirect:/classes/edit/{idTeacher}/{id}";

        }

        this.classesService.editClass(id, classEdit);

        UserEntity currentUser = this.userService.getUserByEmail(principal.getName());

        return "redirect:/classes/" + currentUser.getId() + "/" + id;
    }

    @PostMapping("/delete/{idTeacher}/{id}")
    public String deleteClass(@PathVariable("idTeacher") UUID idTeacher,
                              @PathVariable("id") UUID id,
                              Principal principal) {

        if (!this.userService.getUserById(idTeacher).equals(this.userService.getUserByEmail(principal.getName()))) {
            throw new ObjectNotFoundException("the info is not yours", ClassEntity.class);
        }

        this.classesService.deleteClass(id);

        return "redirect:/classes";
    }


}
