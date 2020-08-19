package context.Interfaces;

import objects.ClientDTO;

import java.util.List;

public interface IClientContext extends IContext<ClientDTO> {
    ClientDTO getById(int id);
    List<ClientDTO> getAll();
}
