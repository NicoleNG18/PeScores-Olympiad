package pmgkn.pescores.pescores.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pmgkn.pescores.pescores.service.TaskService;

import java.security.Principal;
import java.util.UUID;

@Controller
public class TasksController {

    private final TaskService taskService;

    public TasksController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public String getTasks(Model model,
                           Principal principal){

        model.addAttribute("tasks",this.taskService.getAllInProgressTasksByUser(principal.getName()));
        model.addAttribute("tasksDone",this.taskService.getAllDoneTasksByUser(principal.getName()));

        return "tasks";
    }

    @PatchMapping("/tasks/done/")
    public String doneTask(@RequestParam(value = "descr") UUID descr){
//                              BindingResult bindingResult,
//                              RedirectAttributes redirectAttributes) {

//        if (bindingResult.hasErrors()) {
//
//            redirectAttributes.addFlashAttribute("taskDto", taskDto);
//            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.editedProductDto"
//                    , bindingResult);
//
//            return "redirect:/products/edit/{id}";
//
//        }

        this.taskService.makeTaskDone(descr);


        return "redirect:/tasks";
    }

    @PatchMapping("/tasks/delete/")
    public String deleteTask(@RequestParam(value = "descr") UUID descr){

        this.taskService.makeTaskDeleted(descr);

        return "redirect:/tasks";
    }

}
