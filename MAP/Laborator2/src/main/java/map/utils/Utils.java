package map.utils;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Utils {

    public static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm");

    public static void BubbleSort(ArrayList<Integer> array) {

        boolean sorted = false;
        do {
            sorted = true;
            for(int i = 0; i < array.size() - 1; i++) {
                if(array.get(i) > array.get(i+1)) {
                    Collections.swap(array, i, i+1);
                    sorted = false;
                }
            }
        }while(!sorted);

    }

    public static void QuickSort(ArrayList<Integer> array, int begin, int end) {
            if(begin < end) {
                int partitionIndex = partition(array, begin, end);
                QuickSort(array, begin, partitionIndex - 1);
                QuickSort(array, partitionIndex + 1, end);
            }
    }

    private static int partition(ArrayList<Integer> array, int begin, int end) {

        int pivot = array.get(end);
        int i = begin - 1;

        for(int j = begin; j < end; j++) {
            if(array.get(j) <= pivot) {
                i++;

                Collections.swap(array, i, j);

            }
        }

        Collections.swap(array, i+1, end);
        return i+1;
    }

}
