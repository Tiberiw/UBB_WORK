package map.factory;

import map.domain.Task;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractContainer implements Container{

    protected final List<Task> list = new ArrayList<>();
    public abstract Task remove();

    public void add(Task task) {
        this.list.add(task);
    }

    public int size() {
        return this.list.size();
    }

    public boolean isEmpty() {
        return this.list.isEmpty();
    }
}
