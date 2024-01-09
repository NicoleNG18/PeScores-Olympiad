package pmgkn.pescores.pescores.web;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pmgkn.pescores.pescores.domain.dto.TaskDto;
import pmgkn.pescores.pescores.service.TaskService;

import java.security.Principal;
import java.util.UUID;

@Controller
public class HomeController {

    private final TaskService taskService;

    public HomeController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/")
    public String getHome(){
        return "home";
    }

    @GetMapping("/contact")
    public String getContact(){
        return "contact";
    }

    @GetMapping("/notes")
    public String getNotes(Model model,Principal principal){

        model.addAttribute("tasks",this.taskService.getAllTasksByUser(principal.getName()));

        return "notes";
    }

}
