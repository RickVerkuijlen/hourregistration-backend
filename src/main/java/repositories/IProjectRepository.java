package repositories;

import objects.Project;

public interface IProjectRepository {
    Project getProjectByCode(String code);
}
