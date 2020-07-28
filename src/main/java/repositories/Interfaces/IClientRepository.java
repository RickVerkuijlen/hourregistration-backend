package repositories.Interfaces;

import objects.ClientDTO;

import java.util.List;

public interface IClientRepository {
    ClientDTO getById(int id);
    List<ClientDTO> getAllClients();
    Boolean updateClient(ClientDTO entity);
    int createClient(ClientDTO entity);
}
