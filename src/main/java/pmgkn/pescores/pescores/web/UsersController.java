package pmgkn.pescores.pescores.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pmgkn.pescores.pescores.service.UserService;

import java.util.UUID;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @PatchMapping("/roles/admin/add/{schoolId}/{id}")
    public String makeAdmin(@PathVariable("schoolId") UUID schoolId,@PathVariable("id") UUID id) {

        this.userService.makeAdmin(id);

        return "redirect:/schools/"+schoolId;
    }

    @PatchMapping("/roles/admin/remove/{schoolId}/{id}")
    public String removeAdmin(@PathVariable("schoolId") UUID schoolId,@PathVariable("id") UUID id) {

        this.userService.removeAdmin(id);

        return "redirect:/schools/"+schoolId;
    }

}
