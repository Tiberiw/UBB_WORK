package map.runner;

import map.domain.Task;
import map.factory.AbstractContainer;

public abstract class AbstractTaskRunner implements TaskRunner{

    private final TaskRunner taskRunner;

    public AbstractTaskRunner(TaskRunner taskRunner) {
        this.taskRunner = taskRunner;
    }


    public void executeOneTask() {
        this.taskRunner.executeOneTask();
    }
    public void executeAll() {
        while(this.hasTask()) {
            this.executeOneTask();
        }
    }
    public void addTask(Task t) {
        this.taskRunner.addTask(t);
    }
    public boolean hasTask() {
        return this.taskRunner.hasTask();
    }

}
