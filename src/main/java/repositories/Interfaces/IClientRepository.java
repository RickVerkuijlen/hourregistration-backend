package repositories.Interfaces;

import objects.ClientDTO;

import java.util.List;

public interface IClientRepository {
    ClientDTO getById(int id);
    List<ClientDTO> getAllClients();
}
