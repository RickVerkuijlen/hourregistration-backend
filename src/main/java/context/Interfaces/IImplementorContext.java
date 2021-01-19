package context.Interfaces;

import objects.Implementor;

import java.util.List;

public interface IImplementorContext extends IContext<Implementor> {
    List<Implementor> getAllImplementors();
    Implementor getById(int id);
}
