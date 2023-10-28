package map.runner;

import map.domain.Task;
import map.utils.Utils;

import java.time.LocalDateTime;

public class PrinterTaskRunner extends AbstractTaskRunner{

    public PrinterTaskRunner(TaskRunner taskRunner) {
        super(taskRunner);
    }


    //DECORATES THE TASK RUNNER (This method is added on top of the task runner)
    @Override
    public void executeOneTask() {
        super.executeOneTask(); // Face ce face super clasa + inca ceva
        System.out.println("Task executat cu succes la ora: " + LocalDateTime.now().format(Utils.dateFormatter));
    }

}
