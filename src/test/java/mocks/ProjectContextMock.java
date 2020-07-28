package mocks;

import context.Interfaces.IProjectContext;
import objects.ProjectDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProjectContextMock implements IProjectContext {
    @Override
    public List<ProjectDTO> getAllProjects() {
        List<ProjectDTO> projectDTOS = new ArrayList<>();
        projectDTOS.add(new ProjectDTO());
        projectDTOS.add(new ProjectDTO());
        projectDTOS.add(new ProjectDTO());
        return projectDTOS;
    }

    @Override
    public boolean delete(UUID entity) {
        return false;
    }

    @Override
    public boolean update(ProjectDTO entity) {
        return false;
    }

    @Override
    public int create(ProjectDTO entity) {
        return 0;
    }
}
