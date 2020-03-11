package context.Interfaces;

import objects.ProjectDTO;

import java.util.List;

public interface IProjectContext extends IContext<ProjectDTO> {
    List<ProjectDTO> getAllProjects();
}
