package repositories;

import context.Interfaces.IProjectContext;
import controllers.ClientController;
import controllers.ImplementorController;
import controllers.ProjectController;
import objects.ProjectDTO;
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

    @Override
    public List<ProjectDTO> allProjects() {
        List<ProjectDTO> projectDTOS = projectContext.getAllProjects();
        for (ProjectDTO projectDTO :
                projectDTOS) {

            Link selfLink = linkTo(methodOn(ProjectController.class).getProjectById(projectDTO.getCode())).withSelfRel();
            projectDTO.add(selfLink);

            Link implementorLink = linkTo(methodOn(ImplementorController.class).getById(Integer.toString(projectDTO.getImplementorId()))).withRel("implementor");
            projectDTO.add(implementorLink);

            Link clientLink = linkTo(methodOn(ClientController.class).getClientById(Integer.toString(projectDTO.getClientId()))).withRel("client");
            projectDTO.add(clientLink);
        }
        return projectDTOS;
    }

    @Override
    public ProjectDTO getProjectByCode(String code) {
        return allProjects().stream().filter(o -> o.getCode().equals(code)).findFirst().orElseThrow(NullPointerException::new);
    }

    @Override
    public Boolean createProject(ProjectDTO projectDTO) {
        return projectContext.create(projectDTO) == 1;
    }

    @Override
    public Boolean updateProject(ProjectDTO projectDTO) {
        return projectContext.update(projectDTO);
    }
}
