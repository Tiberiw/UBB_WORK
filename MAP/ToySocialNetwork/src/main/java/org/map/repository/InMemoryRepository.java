package org.map.repository;

import org.map.domain.Entity;
import org.map.exception.RepositoryException;
import org.map.exception.ValidatorException;
import org.map.validator.Validator;

import java.util.HashMap;
import java.util.Map;

public class InMemoryRepository<ID, E extends Entity<ID> > implements Repository<ID,E>{

    private final Map<ID,E> users;
    private final Validator<E> validator;

    public InMemoryRepository(Validator<E> validator) {
        users = new HashMap<>();
        this.validator = validator;
    }


    @Override
    public E save(E entity) throws IllegalArgumentException, ValidatorException, RepositoryException{
        if (entity == null)
            throw new IllegalArgumentException("Invalid Entity");

        validator.validate(entity);

        if(users.get(entity.getID()) != null ) {
            throw new RepositoryException("Existent ID");
        }

        users.putIfAbsent(entity.getID(),entity);
        return  entity;
    }

    @Override
    public E findOne(ID id) throws RepositoryException{
        if(users.get(id) == null)
            throw new RepositoryException("Nonexistent ID");

        return users.get(id);
    }

    @Override
    public Iterable<E> findAll() {
        return users.values();
    }

    @Override
    public E update(E entity) throws IllegalArgumentException, ValidatorException, RepositoryException{
        if (entity == null)
            throw new IllegalArgumentException("Invalid Entity");

        validator.validate(entity);

        if(users.get(entity.getID()) == null) {
            throw new RepositoryException("Nonexistent ID");
        }

        return users.replace(entity.getID(),entity);
    }

    @Override
    public E delete(ID id) throws RepositoryException{
        if(users.get(id) == null) {
            throw new RepositoryException("Nonexistent ID");
        }

        return users.remove(id);
    }
}
