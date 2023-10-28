package org.map.repository;

import org.map.domain.Entity;
import org.map.domain.User;
import org.map.exception.RepositoryException;
import org.map.exception.ValidatorException;
import org.map.validator.Validator;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryRepository<ID, E extends Entity<ID> > implements Repository<ID,E>{

    private final Map<ID,E> users;
    private final Validator<E> validator;

    public InMemoryRepository(Validator<E> validator) {
        users = new HashMap<>();
        this.validator = validator;
    }


    @Override
    public Optional<E> save(E entity) throws IllegalArgumentException, ValidatorException, RepositoryException{
        if (entity == null)
            throw new IllegalArgumentException("Invalid Entity");

        validator.validate(entity);

        if(users.get(entity.getID()) != null ) {
            throw new RepositoryException("Existent ID");
        }

        users.putIfAbsent(entity.getID(),entity);
        return Optional.of(entity);
    }

    @Override
    public Optional<E> findOne(ID id) throws RepositoryException{
        if(users.get(id) == null)
            throw new RepositoryException("Nonexistent ID");


        return Optional.ofNullable(users.get(id));
    }

    @Override
    public Iterable<E> findAll() {
        return users.values();
    }

    @Override
    public Optional<E> update(E entity) throws IllegalArgumentException, ValidatorException, RepositoryException{
        if (entity == null)
            throw new IllegalArgumentException("Invalid Entity");

        validator.validate(entity);

        if(users.get(entity.getID()) == null) {
            throw new RepositoryException("Nonexistent ID");
        }

        users.replace(entity.getID(),entity);
        return Optional.of(entity);
    }

    @Override
    public Optional<E> delete(ID id) throws RepositoryException{
        if(users.get(id) == null) {
            throw new RepositoryException("Nonexistent ID");
        }
        return Optional.ofNullable(users.remove(id));
    }
}
