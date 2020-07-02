package repositories.Interfaces;

import objects.ImplementorDTO;

import java.util.List;

public interface IImplementorRepository {
    ImplementorDTO getById(int id);

    List<ImplementorDTO> getAllImplementors();
}
