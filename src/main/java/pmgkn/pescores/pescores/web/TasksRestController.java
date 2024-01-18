package pmgkn.pescores.pescores.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pmgkn.pescores.pescores.domain.dto.binding.TaskBindingDto;
import pmgkn.pescores.pescores.service.TaskService;
import java.security.Principal;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;

@RestController
public class TasksRestController {


    private final TaskService taskService;


    public TasksRestController(TaskService taskService) {
        this.taskService = taskService;
    }

//    @PostMapping("/api/tasks/save")
//    public ResponseEntity<Object> saveTask (@RequestParam(value = "descr") String descr,
//                                            @RequestParam(value = "dueDate") LocalDate dueDate,
//                                            Principal principal,
//                                            Model model) {
//
//        if(descr.equals(",") || dueDate.isBefore(ChronoLocalDate.from(LocalDate.now()))){
//            model.addAttribute("check",false);
//            model.addAttribute("text","must not be empty");
//
//            return ResponseEntity.r
//        }
//
//        String taskDescription=descr.substring(1);
//
//        this.taskService.saveTask(new TaskBindingDto(taskDescription,dueDate), principal.getName());
//
//        return ResponseEntity
//                .status(HttpStatus.NO_CONTENT)
//                .build();
//    }

}
