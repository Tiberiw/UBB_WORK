package map.factory;

import map.domain.Task;

public interface Container {

    Task remove();
    void add(Task task);
    int size();
    boolean isEmpty();
}
