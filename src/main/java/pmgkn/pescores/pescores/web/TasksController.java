package pmgkn.pescores.pescores.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pmgkn.pescores.pescores.service.TaskService;

import java.security.Principal;

@Controller
public class TasksController {

    private final TaskService taskService;

    public TasksController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public String getNotes(Model model,
                           Principal principal){

        model.addAttribute("tasks",this.taskService.getAllTasksByUser(principal.getName()));

        return "tasks";
    }
}
