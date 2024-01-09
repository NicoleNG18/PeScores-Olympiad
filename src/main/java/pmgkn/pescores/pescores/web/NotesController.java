package pmgkn.pescores.pescores.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pmgkn.pescores.pescores.domain.dto.TaskDto;
import pmgkn.pescores.pescores.service.TaskService;

import java.security.Principal;

@RestController
public class NotesController {


    private final TaskService taskService;


    public NotesController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/api/notes/save")
    public ResponseEntity<Object> inactivateUser (@RequestParam(value = "descr",required = true) String descr,Principal principal) {

        String taskDescription= descr.substring(1);

        this.taskService.addTask(new TaskDto(taskDescription), principal.getName());

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
