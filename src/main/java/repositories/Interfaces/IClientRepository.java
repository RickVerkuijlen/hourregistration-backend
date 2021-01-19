package repositories.Interfaces;

import objects.Client;

import java.util.List;

public interface IClientRepository {
    Client getById(int id);
    List<Client> getAllClients();
    Boolean updateClient(Client entity);
    int createClient(Client entity);
}
