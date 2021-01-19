package context.Interfaces;

import objects.Client;

import java.util.List;

public interface IClientContext extends IContext<Client> {
    Client getById(int id);
    List<Client> getAll();
}
