package pmgkn.pescores.pescores.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NormsController {

    @GetMapping("/denseBall")
    public String getDenseBall(){
        return "dense-ball";
    }
}
