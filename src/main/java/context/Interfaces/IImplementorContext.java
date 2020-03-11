package context.Interfaces;

import objects.ImplementorDTO;

public interface IImplementorContext extends IContext<ImplementorDTO> {
    ImplementorDTO getById(int id);
}
