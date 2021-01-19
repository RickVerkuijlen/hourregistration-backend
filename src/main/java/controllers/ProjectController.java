package controllers;

import objects.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repositories.Interfaces.IProjectRepository;
import repositories.ProjectRepository;

import java.util.List;

@RestController
@RequestMapping("/projects")
@CrossOrigin(origins = "http://localhost:3000")
public class ProjectController {

    @Autowired
    private IProjectRepository projectRepository;

    @Autowired
    public ProjectController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @GetMapping("")
    public @ResponseBody
    HttpEntity<List<Project>> getAllProjects() {
        List<Project> allProjects = projectRepository.allProjects();
        if(!allProjects.isEmpty()) {
            return new ResponseEntity<>(allProjects, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{code}")
    public @ResponseBody
    HttpEntity<Project> getProjectById(@PathVariable String code) {
        Project project = projectRepository.getProjectByCode(code);

        if(project != null) {
            return new ResponseEntity<>(project, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public @ResponseBody
    HttpEntity<Boolean> createProject(@RequestBody Project newProject) {
        Boolean projectCreationSuccess = projectRepository.createProject(newProject);

        if(projectCreationSuccess) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    public @ResponseBody
    HttpEntity<Boolean> updateProject(@RequestBody Project entity) {
        Boolean projectUpdateSuccess = projectRepository.updateProject(entity);

        if(projectUpdateSuccess) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
