package pmgkn.pescores.pescores.web;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pmgkn.pescores.pescores.domain.dto.TaskDto;
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

        model.addAttribute("tasks",this.taskService.getAllInProgressTasksByUser(principal.getName()));
        model.addAttribute("tasksDone",this.taskService.getAllDoneTasksByUser(principal.getName()));

        return "tasks";
    }

    @PatchMapping("/tasks/done/")
    public String doneTask(@RequestParam(value = "descr") String descr, Principal principal){
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

        this.taskService.makeTaskDone(descr,principal.getName());


        return "redirect:/tasks";
    }

    @DeleteMapping("/tasks/delete/")
    public String deleteTask(@RequestParam(value = "descr") String descr, Principal principal){
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

        this.taskService.deleteTask(descr,principal.getName());


        return "redirect:/tasks";
    }

}
