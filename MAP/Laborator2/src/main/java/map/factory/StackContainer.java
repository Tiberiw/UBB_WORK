package map.factory;

import map.domain.Task;

import java.util.ArrayList;
import java.util.List;

public class StackContainer extends AbstractContainer{

    //private final List<Task> list = new ArrayList<>();

    @Override
    public Task remove() {
        if(!list.isEmpty())
            return list.remove(list.size() - 1);
        return null;
    }



}
