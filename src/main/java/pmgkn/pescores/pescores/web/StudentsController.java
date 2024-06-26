package pmgkn.pescores.pescores.web;

import jakarta.validation.Valid;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pmgkn.pescores.pescores.domain.dto.binding.StudentAddBindingDto;
import pmgkn.pescores.pescores.domain.dto.binding.StudentUpdateBindingDto;
import pmgkn.pescores.pescores.domain.entity.ClassEntity;
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
    public StudentUpdateBindingDto initStudentUpdateDto() {
        return new StudentUpdateBindingDto();
    }

    @GetMapping("/add")
    public String getAddStudent(Model model,
                                Principal principal) {

        model.addAttribute("classes", this.classesService.getAllClassesInSchool(principal.getName()));

        return "student-add";
    }

    @PostMapping("/add")
    public String postAddStudent(@Valid StudentAddBindingDto studentAddBindingDto,
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

        return "redirect:/classes/" + classId;
    }

    @PostMapping("/update/{id}")
    public String editStudent(@PathVariable("id") UUID id,
                              @Valid StudentUpdateBindingDto studentUpdate,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("studentUpdate", studentUpdate);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.studentUpdate"
                    , bindingResult);

            return "redirect:/students/update/{id}";
        }

        UUID classId = this.studentsService.editStudent(studentUpdate, id);

        return "redirect:/classes/" + classId;
    }

    @PostMapping("/delete/{idTeacher}/{id}")
    public String deleteStudent(@PathVariable("idTeacher") UUID idTeacher,
                                @PathVariable("id") UUID id,
                                Principal principal) {

        if (!this.userService.getUserById(idTeacher).getSchool().getSchoolName().equals(this.userService.getUserByEmail(principal.getName()).getSchool().getSchoolName())) {
            throw new ObjectNotFoundException("the info is not yours", ClassEntity.class);
        }

        UUID classId = this.studentsService.getStudentClassId(id);

        this.studentsService.deleteStudent(id);

        return "redirect:/classes/" + classId;
    }
}
