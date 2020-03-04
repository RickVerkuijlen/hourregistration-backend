package context;

import objects.Project;

import java.util.List;

public interface IProjectContext extends IContext<Project> {
    List<Project> getAllProjects();
}
