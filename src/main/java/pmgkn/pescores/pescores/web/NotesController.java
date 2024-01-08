package pmgkn.pescores.pescores.web;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pmgkn.pescores.pescores.domain.dto.TaskDto;
import pmgkn.pescores.pescores.service.TaskService;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
public class NotesController {


    private final TaskService taskService;


    public NotesController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/api/notes/save")
    public ResponseEntity<Object> inactivateUser (@RequestParam(value = "descr") String descr,Principal principal) {

        this.taskService.addTask(new TaskDto(descr), principal.getName());

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


//    @GetMapping("/notes")
//    public ResponseEntity<List<TaskDto>> displayTaskById(Principal principal){
//
//        List<TaskDto> task=this.taskService.getAllTasksByUser(principal.getName());
//
//        return ResponseEntity.ok(task);
//    }
////

//    @GetMapping("/notes/{id}")
//    public ResponseEntity<TaskDto> displayTaskById(@PathVariable("id") UUID id){
//
//        TaskDto task=this.taskService.getTaskById(id);
//
//        return ResponseEntity.ok(task);
//    }

//    @ModelAttribute("taskDto")
//    public TaskDto initOrderDto() {
//        return new TaskDto();
//    }

    @PostMapping("/notes/save")
    public String addTask(@RequestBody TaskDto taskDto,
//                          BindingResult bindingResult,
//                          RedirectAttributes redirectAttributes,
                          Principal principal){

//        if (taskDto.getDescription().equals("")) {
//
//            redirectAttributes.addFlashAttribute("taskDto", taskDto);
//            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.taskDto"
//                    , bindingResult);
//
//            return "redirect:/notes";
//
//        }

//        UUID uuid = this.taskService.addTask(taskDto, principal.getName());

        String test="Nikolee";

//        return "redirect:/notes/" + uuid;
        return "redirect:/notes/";

    }

}
