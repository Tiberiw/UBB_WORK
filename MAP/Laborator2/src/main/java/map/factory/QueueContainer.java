package map.factory;

import map.domain.Task;

public class QueueContainer extends AbstractContainer{

  //  private final List<Task> list = new ArrayList<>();

    @Override
    public Task remove() {
        if(!list.isEmpty())
            return list.remove(0);
        return null;
    }
}
