/*
 * Author: Kaden Payne
 * Date: 10/23/2020
 * 
 * Class with methods to manage a merge sort
 */
import java.util.Comparator;
/**
 *
 * @author kjpay
 */
public class MergeSort {
    //Methods for sorting
    public static <E extends Comparable<E>> void mergeSort(E[] list) {
        if (list.length > 1) {
            E[] firstHalf = (E[])new Comparable[list.length / 2];
            System.arraycopy(list, 0, firstHalf, 0, list.length / 2);
            mergeSort(firstHalf);

            int secondHalfLength = list.length - list.length / 2;
            E[] secondHalf = (E[])new Comparable[secondHalfLength];
            System.arraycopy(list, list.length / 2, secondHalf, 0, secondHalfLength);
            mergeSort(secondHalf);

            E[] temp = merge(firstHalf, secondHalf, list);
            System.arraycopy(temp, 0, list, 0, temp.length);
        }
    }
    
    public static<E extends Comparable<E>> E[] merge(E[] list1, E[] list2, E[] temp) {
        int current1 = 0;
        int current2 = 0;
        int current3 = 0;
        
        while (current1 < list1.length && current2 < list2.length) {
            if (list1[current1].compareTo(list2[current2]) < 0) {
                temp[current3++] = list1[current1++];
            }
            else {
                temp[current3++] = list2[current2++];
            }
        }
        
        while (current1 < list1.length) {
            temp[current3++] = list1[current1++];
        }
        
        while (current2 < list2.length) {
            temp[current3++] = list2[current2++];
        }
        
        return temp;
    }
    
    public static <E> void mergeSort(E[] list, Comparator<? super E> comparator) {
        if (list.length > 1) {
            E[] firstHalf = (E[])new Object[list.length / 2];
            System.arraycopy(list, 0, firstHalf, 0, list.length / 2);
            mergeSort(firstHalf, comparator);
            
            int secondHalfLength = list.length - list.length / 2;
            E[] secondHalf = (E[])new Object[secondHalfLength];
            System.arraycopy(list, list.length / 2,secondHalf, 0, secondHalfLength);
            mergeSort(secondHalf, comparator);
            
            E[] temp = merge1(firstHalf, secondHalf, comparator);
            System.arraycopy(temp, 0, list, 0, temp.length);
        }
    }
    
    private static<E> E[] merge1(E[] list1, E[] list2, Comparator<? super E> comparator) {
        E[] temp = (E[])new Object[list1.length + list2.length];
        
        int current1 = 0; // Index in list1
        int current2 = 0; // Index in list2
        int current3 = 0; // Index in temp
        
        while (current1 < list1.length && current2 < list2.length) {
            if (comparator.compare(list1[current1], list2[current2]) < 0) {
                temp[current3++] = list1[current1++];
            }
            else {
                temp[current3++] = list2[current2++];
            }
        }
        
        while (current1 < list1.length) {
            temp[current3++] = list1[current1++];
        }
        
        while (current2 < list2.length) {
            temp[current3++] = list2[current2++];
        }
        
        return temp;
    }
}