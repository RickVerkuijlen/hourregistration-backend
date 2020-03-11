package repositories;

import context.IProjectContext;
import objects.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import repositories.Interfaces.IProjectRepository;

import java.util.List;

@Component
public class ProjectRepository implements IProjectRepository {

    private IProjectContext projectContext;

    @Autowired
    public ProjectRepository(IProjectContext projectContext) {
        this.projectContext = projectContext;
    }

    public List<Project> allProjects() {
        return projectContext.getAllProjects();
    }

    @Override
    public Project getProjectByCode(String code) {
        return projectContext.getAllProjects().stream().filter(o -> o.getCode().equals(code)).findFirst().orElseThrow(NullPointerException::new);
    }
}
