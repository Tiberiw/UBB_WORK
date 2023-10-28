package org.map.service;

import org.map.domain.User;
import org.map.exception.RepositoryException;
import org.map.exception.ValidatorException;
import org.map.repository.InMemoryRepository;
import org.map.repository.Repository;

public class UserService {

    Repository<Long, User> Repository;

    public UserService(Repository<Long, User> Repository) {
        this.Repository = Repository;
    }

    public User saveToRepository(String firstName, String secondName) throws IllegalArgumentException, ValidatorException, RepositoryException{

        User newUser = new User(firstName,secondName);
        return Repository.save(newUser);
    }

    public User getFromRepository(Long id) throws RepositoryException{
        return Repository.findOne(id);
    }

    public Iterable<User> getAll() {
        return Repository.findAll();
    }

    public User updateToRepository(Long id, String firstName, String lastName) throws IllegalArgumentException, ValidatorException, RepositoryException {

        User oldUser = Repository.findOne(id);
        oldUser.setFirstName(firstName);
        oldUser.setLastName(lastName);
        return Repository.update(oldUser);
    }

    public User removeFromRepository(Long id) throws RepositoryException {
        return Repository.delete(id);
    }

}
