package controllers;

import objects.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repositories.ProjectRepository;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.util.ArrayList;
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

    @GetMapping("/get/all")
    public @ResponseBody HttpEntity<List<Project>> getAllProjects() {
        List<Project> allProjects = projectRepository.allProjects();
        allProjects.add(new Project());
        for (Project project :
                allProjects) {
            String implementorId = Integer.toString(project.getImplementor().getId());

            Link selfLink = linkTo(ProjectController.class).slash(project.getCode()).withSelfRel();
            project.add(selfLink);
            Link implementorLink = linkTo(methodOn(ImplementorController.class).getById(implementorId)).withRel("implementor");
            project.add(implementorLink);
        }

        if(allProjects.isEmpty()) {
            return new ResponseEntity<>(allProjects, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(allProjects, HttpStatus.OK);
        }

    }
}
