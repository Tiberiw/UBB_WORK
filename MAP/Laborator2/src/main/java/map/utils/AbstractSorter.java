package map.utils;

import map.factory.SortStrategy;

import java.util.ArrayList;

public class AbstractSorter {

    public void sort(ArrayList<Integer> array, SortStrategy strategy) {

        switch (strategy) {
            case BUBBLE:
                Utils.BubbleSort(array);
                break;
            case QUICK:
                Utils.QuickSort(array, 0 , array.size() - 1);
        }
    }
}
