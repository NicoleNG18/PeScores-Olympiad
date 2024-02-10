package pmgkn.pescores.pescores.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NormsController {

    @GetMapping("/denseBall")
    public String getDenseBall() {
        return "dense-ball";
    }

    @GetMapping("/jump")
    public String getJump() {
        return "jump";
    }

    @GetMapping("/tTest")
    public String getTtest() {
        return "t-test";
    }

    @GetMapping("/thirty")
    public String getThirtyMeters() {
        return "thirty-meters";
    }

    @GetMapping("/twoHundred")
    public String getTwoHundredMeters() {
        return "two-hundred-meters";
    }
}
