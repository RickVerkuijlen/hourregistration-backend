package context.Interfaces;

import objects.ClientDTO;

public interface IClientContext extends IContext<ClientDTO> {
    ClientDTO getById(int id);
}
