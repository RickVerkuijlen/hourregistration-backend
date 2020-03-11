package controllers;

import objects.ProjectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repositories.ProjectRepository;

import java.util.List;

@RestController
@RequestMapping("/project")
@CrossOrigin(origins = "http://localhost:3000")
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    public ProjectController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @GetMapping("/all")
    public @ResponseBody HttpEntity<List<ProjectDTO>> getAllProjects() {
        List<ProjectDTO> allProjects = projectRepository.allProjects();
        if(!allProjects.isEmpty()) {
            return new ResponseEntity<>(allProjects, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{code}")
    public @ResponseBody HttpEntity<ProjectDTO> getProjectById(@PathVariable String code) {
        ProjectDTO projectDTO = projectRepository.getProjectByCode(code);

        if(projectDTO != null) {
            return new ResponseEntity<>(projectDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public HttpEntity<Boolean> createProject(@RequestBody ProjectDTO newProject) {
        Boolean projectCreationSuccess = projectRepository.createProject(newProject);

        if(projectCreationSuccess) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
