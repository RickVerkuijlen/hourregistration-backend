package repositories.Interfaces;

import objects.Implementor;

import java.util.List;

public interface IImplementorRepository {
    Implementor getById(int id);

    List<Implementor> getAllImplementors();
}
