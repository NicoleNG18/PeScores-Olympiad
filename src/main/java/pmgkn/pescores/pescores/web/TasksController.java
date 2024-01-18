package pmgkn.pescores.pescores.web;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pmgkn.pescores.pescores.domain.dto.binding.TaskBindingDto;
import pmgkn.pescores.pescores.service.TaskService;

import java.security.Principal;
import java.util.UUID;

@Controller
public class TasksController {

    private final TaskService taskService;

    public TasksController(TaskService taskService) {
        this.taskService = taskService;
    }

    @ModelAttribute("taskDto")
    public TaskBindingDto initTaskDto() {
        return new TaskBindingDto();
    }


    @GetMapping("/tasks")
    public String getTasks(Model model,
                           Principal principal) {

        model.addAttribute("tasks", this.taskService.getAllInProgressTasksByUser(principal.getName()));
        model.addAttribute("tasksDone", this.taskService.getAllDoneTasksByUser(principal.getName()));

        return "tasks";
    }

    @PostMapping("/tasks/save")
    public String saveTask (@Valid TaskBindingDto taskDto,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes,
                            Principal principal) {

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("taskDto", taskDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.taskDto"
                    , bindingResult);

            return "redirect:/tasks";

        }

        this.taskService.saveTask(taskDto,principal.getName());

        return "redirect:/tasks";
    }

    @PatchMapping("/tasks/done/")
    public String doneTask(@RequestParam(value = "descr") UUID descr) {

        this.taskService.makeTaskDone(descr);

        return "redirect:/tasks";
    }

    @PatchMapping("/tasks/delete/")
    public String deleteTask(@RequestParam(value = "descr") UUID descr) {

        this.taskService.makeTaskDeleted(descr);

        return "redirect:/tasks";
    }

}
