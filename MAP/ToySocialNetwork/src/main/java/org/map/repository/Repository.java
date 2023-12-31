package org.map.repository;

import org.map.domain.Entity;

import java.util.Optional;

public interface Repository<ID, E extends Entity<ID>> {

    Optional<E> save(E entity);
    Optional<E> findOne(ID id);
    Iterable<E> findAll();
    Optional<E> update(E entity);
    Optional<E> delete(ID id);

}
