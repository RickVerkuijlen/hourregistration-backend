package controllers;

import objects.ImplementorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repositories.Interfaces.IImplementorRepository;

@RestController
@RequestMapping("/implementors")
@CrossOrigin(origins = "http://localhost:3000")
public class ImplementorController {

    @Autowired
    private IImplementorRepository implementorRepository;

    @Autowired
    public ImplementorController(IImplementorRepository implementorRepository) {
        this.implementorRepository = implementorRepository;
    }

    @GetMapping("/{id}")
    public @ResponseBody HttpEntity<ImplementorDTO> getById(@PathVariable String id) {
        ImplementorDTO implementorDTO = implementorRepository.getById(Integer.parseInt(id));
        return new ResponseEntity<>(implementorDTO, HttpStatus.OK);
    }

}
