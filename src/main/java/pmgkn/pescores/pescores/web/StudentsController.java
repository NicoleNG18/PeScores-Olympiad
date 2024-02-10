package pmgkn.pescores.pescores.web;

import jakarta.validation.Valid;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pmgkn.pescores.pescores.domain.dto.binding.StudentAddBindingDto;
import pmgkn.pescores.pescores.domain.dto.binding.StudentUpdateDto;
import pmgkn.pescores.pescores.domain.entity.ClassEntity;
import pmgkn.pescores.pescores.domain.entity.UserEntity;
import pmgkn.pescores.pescores.service.ClassesService;
import pmgkn.pescores.pescores.service.StudentsService;
import pmgkn.pescores.pescores.service.UserService;

import java.security.Principal;
import java.util.UUID;

@Controller
@RequestMapping("/students")
public class StudentsController {

    private final ClassesService classesService;
    private final StudentsService studentsService;

    private final UserService userService;

    public StudentsController(ClassesService classesService,
                              StudentsService studentsService,
                              UserService userService) {
        this.classesService = classesService;
        this.studentsService = studentsService;
        this.userService = userService;
    }

    @ModelAttribute("studentAddDto")
    public StudentAddBindingDto initStudentAddDto() {
        return new StudentAddBindingDto();
    }

    @ModelAttribute("studentUpdate")
    public StudentUpdateDto initStudentUpdateDto() {
        return new StudentUpdateDto();
    }

    @GetMapping("/add")
    public String getAddStudent(Model model,
                                Principal principal) {

        model.addAttribute("classes", this.classesService.getAllClassesByUser(principal.getName()));

        return "student-add";
    }

    @PostMapping("/add")
    public String postAddClass(@Valid StudentAddBindingDto studentAddBindingDto,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes,
                               Principal principal) {


        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("studentAddDto", studentAddBindingDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.studentAddDto"
                    , bindingResult);

            return "redirect:/students/add";

        }

        if (this.studentsService.checkIfClassNumRepeats(studentAddBindingDto.getStudentClass(), principal.getName(), studentAddBindingDto.getStudentNumber())) {

            redirectAttributes.addFlashAttribute("isUnique", false);

            return "redirect:/students/add";

        }

        UUID classId = this.studentsService.saveStudent(principal.getName(), studentAddBindingDto);
        UserEntity currentUser = this.userService.getUserByEmail(principal.getName());

        return "redirect:/classes/" + currentUser.getId() + "/" + classId;
    }

    @PostMapping("/update/{id}")
    public String editStudent(@PathVariable("id") UUID id,
                              @Valid StudentUpdateDto studentUpdate,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes,
                              Principal principal) {

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("studentUpdate", studentUpdate);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.studentUpdate"
                    , bindingResult);

            return "redirect:/students/update/{id}";

        }

        UUID classId = this.studentsService.editStudent(studentUpdate, id);
        UserEntity currentUser = this.userService.getUserByEmail(principal.getName());


        return "redirect:/classes/" + currentUser.getId() + "/" + classId;
    }

    @PostMapping("/delete/{idTeacher}/{id}")
    public String deleteStudent(@PathVariable("idTeacher") UUID idTeacher,
                                @PathVariable("id") UUID id,
                                Principal principal) {

        if (!this.userService.getUserById(idTeacher).equals(this.userService.getUserByEmail(principal.getName()))) {
            throw new ObjectNotFoundException("the info is not yours", ClassEntity.class);
        }

        this.studentsService.deleteStudent(id);

        return "redirect:/classes";
    }



}
