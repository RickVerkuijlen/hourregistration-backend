package repositories;

import context.Interfaces.IClientContext;
import objects.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import repositories.Interfaces.IClientRepository;

import java.util.List;

@Component
public class ClientRepository implements IClientRepository {

    private IClientContext clientContext;

    @Autowired
    public ClientRepository(IClientContext clientContext) {
        this.clientContext = clientContext;
    }

    @Override
    public Client getById(int id) {
        return clientContext.getById(id);
    }

    @Override
    public List<Client> getAllClients() {
        return clientContext.getAll();
    }

    @Override
    public Boolean updateClient(Client client) {
        return clientContext.update(client);
    }

    @Override
    public int createClient(Client entity) {
        return clientContext.create(entity);
    }
}
