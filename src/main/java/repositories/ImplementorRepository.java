package repositories;

import context.Interfaces.IImplementorContext;
import objects.ImplementorDTO;
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
    public ImplementorDTO getById(int id) {
        return implementorContext.getById(id);
    }
}
