package org.map.service;

import org.map.domain.User;
import org.map.exception.RepositoryException;
import org.map.exception.ValidatorException;
import org.map.repository.Repository;

import java.util.Optional;

public class UserService {

    Repository<Long, User> Repository;

    public UserService(Repository<Long, User> Repository) {
        this.Repository = Repository;
    }

    public Optional<User> saveToRepository(String firstName, String secondName) throws IllegalArgumentException, ValidatorException, RepositoryException{

        User newUser = new User(firstName,secondName);
        return Repository.save(newUser);
    }

    public Optional<User> getFromRepository(Long id) throws RepositoryException{
        return Repository.findOne(id);
    }

    public Iterable<User> getAll() {
        return Repository.findAll();
    }

    public Optional<User> updateToRepository(Long id, String firstName, String lastName) throws IllegalArgumentException, ValidatorException, RepositoryException {

        Optional<User> oldUser = Repository.findOne(id);
        oldUser.ifPresent( o -> {
           oldUser.get().setFirstName(firstName);
           oldUser.get().setLastName(lastName);
        });
        return Repository.update(oldUser.get());
    }

    public Optional<User> removeFromRepository(Long id) throws RepositoryException {
        return Repository.delete(id);
    }

}
