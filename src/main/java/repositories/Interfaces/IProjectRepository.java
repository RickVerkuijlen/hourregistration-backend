package repositories.Interfaces;

import objects.Project;

import java.util.List;

public interface IProjectRepository {
    List<Project> allProjects();
    Project getProjectByCode(String code);
    Boolean createProject(Project project);
    Boolean updateProject(Project project);
    Boolean deleteProject(Project project);
}
