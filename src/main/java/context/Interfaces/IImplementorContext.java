package context.Interfaces;

import objects.ImplementorDTO;

import java.util.List;

public interface IImplementorContext extends IContext<ImplementorDTO> {
    List<ImplementorDTO> getAllImplementors();
    ImplementorDTO getById(int id);
}
