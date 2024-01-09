package pmgkn.pescores.pescores.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pmgkn.pescores.pescores.service.TaskService;

import java.security.Principal;

@Controller
public class ToDoController {

    private final TaskService taskService;

    public ToDoController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/notes")
    public String getNotes(Model model,
                           Principal principal){

        model.addAttribute("tasks",this.taskService.getAllTasksByUser(principal.getName()));

        return "notes";
    }
}
