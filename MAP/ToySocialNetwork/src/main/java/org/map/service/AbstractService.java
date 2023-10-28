package org.map.service;

public abstract class AbstractService<E> {

    abstract E saveToRepository();

    abstract E getFromRepository();

    abstract Iterable<E> getAll();

    abstract E updateToRepository();

    abstract E removeFromRepository();
}
