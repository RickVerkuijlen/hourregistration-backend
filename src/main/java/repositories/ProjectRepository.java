package repositories;

import context.IProjectContext;
import controllers.ImplementorController;
import controllers.ProjectController;
import objects.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;
import repositories.Interfaces.IProjectRepository;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class ProjectRepository implements IProjectRepository {

    private IProjectContext projectContext;

    @Autowired
    public ProjectRepository(IProjectContext projectContext) {
        this.projectContext = projectContext;
    }

    public List<Project> allProjects() {
        List<Project> projects = projectContext.getAllProjects();
        for (Project project :
                projects) {
            String implementorId = Integer.toString(project.getImplementor());

            Link selfLink = linkTo(methodOn(ProjectController.class).getProjectById(project.getCode())).withSelfRel();
            project.add(selfLink);

            Link implementorLink = linkTo(methodOn(ImplementorController.class).getById(implementorId)).withRel("implementor");
            project.add(implementorLink);
        }
        return projects;
    }

    @Override
    public Project getProjectByCode(String code) {
        return allProjects().stream().filter(o -> o.getCode().equals(code)).findFirst().orElseThrow(NullPointerException::new);
    }
}
