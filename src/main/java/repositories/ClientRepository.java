package repositories;

import context.Interfaces.IClientContext;
import objects.ClientDTO;
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
    public ClientDTO getById(int id) {
        return clientContext.getById(id);
    }

    @Override
    public List<ClientDTO> getAllClients() {
        return null;
    }
}
