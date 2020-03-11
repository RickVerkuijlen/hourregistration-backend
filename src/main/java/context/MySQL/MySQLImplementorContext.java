package context.MySQL;

import context.IImplementorContext;
import objects.Implementor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MySQLImplementorContext implements IImplementorContext {
    @Override
    public Implementor getById(int id) {
        return new Implementor();
    }

    @Override
    public boolean delete(UUID entity) {
        return false;
    }

    @Override
    public boolean update(Implementor entity) {
        return false;
    }

    @Override
    public boolean create(Implementor entity) {
        return false;
    }
}
