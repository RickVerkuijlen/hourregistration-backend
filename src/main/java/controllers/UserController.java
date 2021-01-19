package controllers;

import objects.User;
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
    HttpEntity<List<User>> getAllUsers() {
        List<User> allUsers = userRepository.allUsers();
        if(!allUsers.isEmpty()) {
            return new ResponseEntity<>(allUsers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public @ResponseBody HttpEntity<User> getById(@PathVariable String id) {
        User user = userRepository.getById(Integer.parseInt(id));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
