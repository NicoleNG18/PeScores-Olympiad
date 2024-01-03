package pmgkn.pescores.pescores.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClassesController {

    @GetMapping("/classes")
    public String getClasses(){
        return "classes";
    }
}
