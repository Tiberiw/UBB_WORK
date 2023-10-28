package map.runner;

import map.domain.Task;
import map.factory.Container;
import map.factory.Strategy;
import map.factory.TaskContainerFactory;

public class StrategyTaskRunner implements TaskRunner{

    private final Container container;

    public StrategyTaskRunner(Strategy strategy) {
        this.container = TaskContainerFactory.getInstance().createContainer(strategy);
    }

    public void executeOneTask() {
        if(this.hasTask()) {
            this.container.remove().execute();
        }

    }

    public void executeAll() {
        while(this.hasTask()) {
            this.container.remove().execute();
        }

    }

    public void addTask(Task t) {
        this.container.add(t);
    }

    public boolean hasTask() {
        return !this.container.isEmpty();
    }
}
