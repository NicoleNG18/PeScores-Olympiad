package pmgkn.pescores.pescores.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pmgkn.pescores.pescores.domain.dto.TaskDto;
import pmgkn.pescores.pescores.service.TaskService;

import java.security.Principal;
import java.time.LocalDate;

@RestController
public class TasksRestController {


    private final TaskService taskService;


    public TasksRestController(TaskService taskService) {
        this.taskService = taskService;
    }


    @PostMapping("/api/tasks/save")
    public ResponseEntity<Object> saveTask (@RequestParam(value = "descr") String descr, @RequestParam(value = "dueDate") LocalDate dueDate, Principal principal) {

        String taskDescription=descr.substring(1);

        this.taskService.saveTask(new TaskDto(taskDescription,dueDate), principal.getName());

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

//      @PostMapping(path = "/temp/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//        public ResponseEntity<PhotoVM> upload (@Valid PhotoBM photoBM) {
//
//            final PhotoVM photoVM = this.tempPhotoService.save(photoBM);
//
//            return ResponseEntity
//                .status(HttpStatus.CREATED)
//                .body(photoVM);
//    }
}
