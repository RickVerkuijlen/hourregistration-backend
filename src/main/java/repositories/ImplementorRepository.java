package repositories;

import context.IImplementorContext;
import objects.Implementor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import repositories.Interfaces.IImplementorRepository;

@Component
public class ImplementorRepository implements IImplementorRepository {

    private IImplementorContext implementorContext;

    @Autowired
    public ImplementorRepository(IImplementorContext implementorContext) {
        this.implementorContext = implementorContext;
    }
    @Override
    public Implementor getById(int id) {
        return implementorContext.getById(id);
    }
}
