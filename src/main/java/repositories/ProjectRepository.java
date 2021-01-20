package repositories;

import context.Interfaces.IClientContext;
import context.Interfaces.IProjectContext;
import controllers.ClientController;
import controllers.ImplementorController;
import controllers.ProjectController;
import objects.Client;
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

    @Autowired
    private IProjectContext projectContext;

    @Autowired
    private IClientContext clientContext;

    @Override
    public List<Project> allProjects() {
        List<Project> projects = projectContext.getAllProjects();
        for (Project project :
                projects) {

            Link selfLink = linkTo(methodOn(ProjectController.class).getProjectById(project.getCode())).withSelfRel();
            project.add(selfLink);

            Link implementorLink = linkTo(methodOn(ImplementorController.class).getById(Integer.toString(project.getImplementorId()))).withRel("implementor");
            project.add(implementorLink);

            Link clientLink = linkTo(methodOn(ClientController.class).getClientById(Integer.toString(project.getClientId()))).withRel("client");
            project.add(clientLink);
        }
        return projects;
    }

    @Override
    public Project getProjectByCode(String code) {
        return allProjects().stream().filter(o -> o.getCode().equals(code)).findFirst().orElseThrow(NullPointerException::new);
    }

    @Override
    public Boolean createProject(Project project) {
        return projectContext.create(project) == 1;
    }

    @Override
    public Boolean updateProject(Project project) {
        return projectContext.update(project);
    }

    @Override
    public Boolean deleteProject(Project project) {
        Client client = clientContext.getById(project.getClientId());
        projectContext.delete(project);
        return clientContext.delete(client);
    }
}
