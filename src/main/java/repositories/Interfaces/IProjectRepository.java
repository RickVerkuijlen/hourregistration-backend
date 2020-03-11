package repositories.Interfaces;

import objects.ProjectDTO;

import java.util.List;

public interface IProjectRepository {
    List<ProjectDTO> allProjects();
    ProjectDTO getProjectByCode(String code);
    Boolean createProject(ProjectDTO projectDTO);
}
