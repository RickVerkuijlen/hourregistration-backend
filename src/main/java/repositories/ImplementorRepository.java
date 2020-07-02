package repositories;

import context.Interfaces.IImplementorContext;
import objects.ImplementorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import repositories.Interfaces.IImplementorRepository;

import java.util.List;

@Component
public class ImplementorRepository implements IImplementorRepository {

    private IImplementorContext implementorContext;

    @Autowired
    public ImplementorRepository(IImplementorContext implementorContext) {
        this.implementorContext = implementorContext;
    }

    public List<ImplementorDTO> getAllImplementors() {
        return implementorContext.getAllImplementors();
    }
    @Override
    public ImplementorDTO getById(int id) {
        return implementorContext.getById(id);
    }
}
