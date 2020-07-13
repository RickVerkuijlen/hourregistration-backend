package controllers;

import objects.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repositories.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public @ResponseBody
    HttpEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> allUsers = userRepository.allUsers();
        if(!allUsers.isEmpty()) {
            return new ResponseEntity<>(allUsers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public @ResponseBody HttpEntity<UserDTO> getById(@PathVariable String id) {
        UserDTO userDTO = userRepository.getById(Integer.parseInt(id));
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
}
