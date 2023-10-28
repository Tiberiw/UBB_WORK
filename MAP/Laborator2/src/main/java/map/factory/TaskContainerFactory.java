package map.factory;

import map.domain.Task;

public class TaskContainerFactory implements Factory{

    private final static TaskContainerFactory instance = new TaskContainerFactory();
    private TaskContainerFactory() {}
    public static TaskContainerFactory getInstance() {
        return instance;
    }
    public Container createContainer(Strategy strategy) {

        return switch (strategy) {
            case FIFO -> new QueueContainer();
            case LIFO -> new StackContainer();


        };

    }
}
