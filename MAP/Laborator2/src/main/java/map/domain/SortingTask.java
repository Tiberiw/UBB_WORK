package map.domain;

import map.factory.SortStrategy;
import map.runner.AbstractTaskRunner;
import map.utils.AbstractSorter;
import map.utils.Sorter;

import java.util.ArrayList;

public class SortingTask extends Task{

    private ArrayList<Integer> array;
    private String sortStrategy;
    private final AbstractSorter abstractSorter = new AbstractSorter();

    public SortingTask(String taskID, String description, ArrayList<Integer> array, String sortStrategy) {
        super(taskID,description);
        this.array = array;
        this.sortStrategy = sortStrategy;
    }

    public void execute() {

        //TODO validations for sort strategy
        if( SortStrategy.valueOf(this.sortStrategy) == SortStrategy.BUBBLE ||
                SortStrategy.valueOf(this.sortStrategy) == SortStrategy.QUICK) {

            this.abstractSorter.sort(this.array, SortStrategy.valueOf(this.sortStrategy));
            System.out.println(this.array);
        } else {
            System.out.println("INVALID STRATEGY!");
        }


    }

    public ArrayList<Integer> getArray() {
        return array;
    }

    public void setArray(ArrayList<Integer> array) {
        this.array = array;
    }

    public String getSortStrategy() {
        return sortStrategy;
    }

    public void setSortStrategy(String sortStrategy) {
        this.sortStrategy = sortStrategy;
    }
}
