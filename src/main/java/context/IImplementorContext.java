package context;

import objects.Implementor;

public interface IImplementorContext extends IContext<Implementor> {
    Implementor getById(int id);
}
