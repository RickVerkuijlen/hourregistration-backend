package repositories.Interfaces;

import objects.Project;

public interface IProjectRepository {
    Project getProjectByCode(String code);
}
