package org.map.repository;

import org.map.domain.Entity;

public interface Repository<ID, E extends Entity<ID>> {

    E save(E entity);
    E findOne(ID id);
    Iterable<E> findAll();
    E update(E entity);
    E delete(ID id);

}
