package Test1;

import java.util.HashSet;
import java.util.Set;

public class Task1 {
    public static void main(String[] args) {
        int[] array = {3, 43, 5, 13, 1};
        int count = countDistinctElements(array);
        System.out.println("Количество различных элементов: " + count);
    }

    public static int countDistinctElements(int[] array) {
        Set<Integer> uniqueElements = new HashSet<>();

        for (int element : array) {
            uniqueElements.add(element);
        }

        return uniqueElements.size();
    }
}

