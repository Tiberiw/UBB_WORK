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
        return Repository.save( new User(firstName,secondName) );
    }

    public Optional<User> getFromRepository(Long id) throws RepositoryException{
        return Repository.findOne(id);
    }

    public Iterable<User> getAll() {
        return Repository.findAll();
    }

    public Optional<User> updateToRepository(Long id, String firstName, String lastName) throws IllegalArgumentException, ValidatorException, RepositoryException {
        Optional<User> optionalUser = Repository.findOne(id);

        optionalUser.ifPresent( user -> {
            user.setFirstName(firstName);
            user.setLastName(lastName);
            Repository.update(user);
        });

        return optionalUser;
    }

    public Optional<User> removeFromRepository(Long id) throws RepositoryException {
        return Repository.delete(id);
    }

}
