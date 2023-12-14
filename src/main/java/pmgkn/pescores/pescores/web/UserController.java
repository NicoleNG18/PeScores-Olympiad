package pmgkn.pescores.pescores.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pmgkn.pescores.pescores.domain.dto.UserRegistrationDTO;
import pmgkn.pescores.pescores.repositories.UserRepository;
import pmgkn.pescores.pescores.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserRepository userRepository;
    private UserService userService;

    @Autowired
    public UserController(UserRepository userRepository, UserService userService){
        this.userRepository=userRepository;
        this.userService=userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRegistrationDTO userRegistrationDTO) {

        if (userRepository.findByEmail(userRegistrationDTO.email()).isEmpty()) {
            return new ResponseEntity<>("User with that email is already registered", HttpStatus.BAD_REQUEST);
        }

        userService.registerUser(userRegistrationDTO);

        return new ResponseEntity<>("User registered success!", HttpStatus.OK);
    }
}
