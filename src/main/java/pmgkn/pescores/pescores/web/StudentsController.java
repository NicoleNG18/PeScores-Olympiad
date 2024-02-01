package pmgkn.pescores.pescores.web;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pmgkn.pescores.pescores.domain.dto.binding.StudentAddBindingDto;
import pmgkn.pescores.pescores.service.ClassesService;
import pmgkn.pescores.pescores.service.StudentsService;

import java.security.Principal;
import java.util.UUID;

@Controller
@RequestMapping("/students")
public class StudentsController {

    private final ClassesService classesService;
    private final StudentsService studentsService;

    public StudentsController(ClassesService classesService,
                              StudentsService studentsService) {
        this.classesService = classesService;
        this.studentsService = studentsService;
    }

    @ModelAttribute("studentAddDto")
    public StudentAddBindingDto initStudentAddDto() {
        return new StudentAddBindingDto();
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

        UUID classId = this.studentsService.saveStudent(principal.getName(), studentAddBindingDto);

        return "redirect:/classes/" + classId;
    }


}
