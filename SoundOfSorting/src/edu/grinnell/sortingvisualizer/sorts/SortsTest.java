package edu.grinnell.sortingvisualizer.sorts;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Test;

public class SortsTest {

    public int arr1[] = {};
    public int sortedArr1[] = {};

    public int arr2[] = {-1, 13, 1032, 27, -68, -68, 13, 500, 500};
    public int sortedArr2[] = {-68, -68, -1, 13, 13, 27, 500, 500, 1032};

    public int arr3[] = {1, 10, 87, 24, -1, 0, 23, 2, 15, 29, 100, 33};
    public int sortedArr3[] = {-1, 0, 1, 2, 10, 15, 23, 24, 29, 33, 87, 100};

    public int arr4[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    public int sortedArr4[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    public ArrayList<Integer> l1;
    public ArrayList<Integer> tempList;
    public ArrayList<Integer> l2;
    public ArrayList<Integer> sortedList;

    public static void convertArrayIntoArrayList(int[] arr, ArrayList<Integer> l){
        for(int i = 0; i < arr.length; i++) {
            l.add(arr[i]);
        }
    }

    	@SuppressWarnings("unchecked")
        @Test
    	public void testSelectionSort() {
    		l1 = new ArrayList<Integer>();
    		sortedList = new ArrayList<Integer>();
    		convertArrayIntoArrayList(arr1, l1);
    		convertArrayIntoArrayList(sortedArr1, sortedList);
    		l2 = (ArrayList<Integer>) l1.clone();
    		tempList = (ArrayList<Integer>) l1.clone();
    	    Sorts.selectionSort(l1);
    		Sorts.eventSort(l2, Sorts.selectionSort(tempList));
    		assertEquals(l1, sortedList);
    		assertEquals(l2, sortedList);
    		
    		
    		l1 = new ArrayList<Integer>();
    		sortedList = new ArrayList<Integer>();
    		convertArrayIntoArrayList(arr2, l1);
    		convertArrayIntoArrayList(sortedArr2, sortedList);
            l2 = (ArrayList<Integer>) l1.clone();
            tempList = (ArrayList<Integer>) l1.clone();
            Sorts.selectionSort(l1);
            Sorts.eventSort(l2, Sorts.selectionSort(tempList));
            assertEquals(l1, sortedList);
            assertEquals(l2, sortedList);
    		
    		l1 = new ArrayList<Integer>();
    		sortedList = new ArrayList<Integer>();
    		convertArrayIntoArrayList(arr3, l1);
    		convertArrayIntoArrayList(sortedArr3, sortedList);
            l2 = (ArrayList<Integer>) l1.clone();
            tempList = (ArrayList<Integer>) l1.clone();
            Sorts.selectionSort(l1);
            Sorts.eventSort(l2, Sorts.selectionSort(tempList));
            assertEquals(l1, sortedList);
            assertEquals(l2, sortedList);
    		
    		l1 = new ArrayList<Integer>();
    		sortedList = new ArrayList<Integer>();
    		convertArrayIntoArrayList(arr4, l1);
    		convertArrayIntoArrayList(sortedArr4, sortedList);
            l2 = (ArrayList<Integer>) l1.clone();
            tempList = (ArrayList<Integer>) l1.clone();
            Sorts.selectionSort(l1);
            Sorts.eventSort(l2, Sorts.selectionSort(tempList));
            assertEquals(l1, sortedList);
            assertEquals(l2, sortedList);
    	}

    @SuppressWarnings("unchecked")
    @Test
    public void testInsertionSort() {
        l1 = new ArrayList<Integer>();
        sortedList = new ArrayList<Integer>();
        convertArrayIntoArrayList(arr1, l1);
        convertArrayIntoArrayList(sortedArr1, sortedList);
        l2 = (ArrayList<Integer>) l1.clone();
        tempList = (ArrayList<Integer>) l1.clone();
        Sorts.insertionSort(l1);
        Sorts.eventSort(l2, Sorts.insertionSort(tempList));
        assertEquals(l1, sortedList);
        assertEquals(l2, sortedList);

        l1 = new ArrayList<Integer>();
        sortedList = new ArrayList<Integer>();
        convertArrayIntoArrayList(arr2, l1);
        convertArrayIntoArrayList(sortedArr2, sortedList);
        l2 = (ArrayList<Integer>) l1.clone();
        tempList = (ArrayList<Integer>) l1.clone();
        Sorts.insertionSort(l1);
        Sorts.eventSort(l2, Sorts.insertionSort(tempList));
        assertEquals(l1, sortedList);
        assertEquals(l2, sortedList);

        l1 = new ArrayList<Integer>();
        sortedList = new ArrayList<Integer>();
        convertArrayIntoArrayList(arr3, l1);
        convertArrayIntoArrayList(sortedArr3, sortedList);
        l2 = (ArrayList<Integer>) l1.clone();
        tempList = (ArrayList<Integer>) l1.clone();
        Sorts.insertionSort(l1);
        Sorts.eventSort(l2, Sorts.insertionSort(tempList));
        assertEquals(l1, sortedList);
        assertEquals(l2, sortedList);

        l1 = new ArrayList<Integer>();
        sortedList = new ArrayList<Integer>();
        convertArrayIntoArrayList(arr4, l1);
        convertArrayIntoArrayList(sortedArr4, sortedList);
        l2 = (ArrayList<Integer>) l1.clone();
        tempList = (ArrayList<Integer>) l1.clone();
        Sorts.insertionSort(l1);
        Sorts.eventSort(l2, Sorts.insertionSort(tempList));
        assertEquals(l1, sortedList);
        assertEquals(l2, sortedList);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testBubbleSort() {
        l1 = new ArrayList<Integer>();
        sortedList = new ArrayList<Integer>();
        convertArrayIntoArrayList(arr1, l1);
        convertArrayIntoArrayList(sortedArr1, sortedList);
        l2 = (ArrayList<Integer>) l1.clone();
        tempList = (ArrayList<Integer>) l1.clone();
        Sorts.bubbleSort(l1);
        Sorts.eventSort(l2, Sorts.bubbleSort(tempList));
        assertEquals(l1, sortedList);
        assertEquals(l2, sortedList);

        l1 = new ArrayList<Integer>();
        sortedList = new ArrayList<Integer>();
        convertArrayIntoArrayList(arr2, l1);
        convertArrayIntoArrayList(sortedArr2, sortedList);
        Sorts.bubbleSort(l1);
        l2 = (ArrayList<Integer>) l1.clone();
        tempList = (ArrayList<Integer>) l1.clone();
        Sorts.bubbleSort(l1);
        Sorts.eventSort(l2, Sorts.bubbleSort(tempList));
        assertEquals(l1, sortedList);
        assertEquals(l2, sortedList);

        l1 = new ArrayList<Integer>();
        sortedList = new ArrayList<Integer>();
        convertArrayIntoArrayList(arr3, l1);
        convertArrayIntoArrayList(sortedArr3, sortedList);
        l2 = (ArrayList<Integer>) l1.clone();
        tempList = (ArrayList<Integer>) l1.clone();
        Sorts.bubbleSort(l1);
        Sorts.eventSort(l2, Sorts.bubbleSort(tempList));
        assertEquals(l1, sortedList);
        assertEquals(l2, sortedList);
        
        l1 = new ArrayList<Integer>();
        sortedList = new ArrayList<Integer>();
        convertArrayIntoArrayList(arr4, l1);
        convertArrayIntoArrayList(sortedArr4, sortedList);
        l2 = (ArrayList<Integer>) l1.clone();
        tempList = (ArrayList<Integer>) l1.clone();
        Sorts.bubbleSort(l1);
        Sorts.eventSort(l2, Sorts.bubbleSort(tempList));
        assertEquals(l1, sortedList);
        assertEquals(l2, sortedList);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testMergeSort() {
        l1 = new ArrayList<Integer>();
        sortedList = new ArrayList<Integer>();
        convertArrayIntoArrayList(arr1, l1);
        convertArrayIntoArrayList(sortedArr1, sortedList);
        l2 = (ArrayList<Integer>) l1.clone();
        tempList = (ArrayList<Integer>) l1.clone();
        Sorts.mergeSort(l1);
        Sorts.eventSort(l2, Sorts.mergeSort(tempList));
        assertEquals(l1, sortedList);
        assertEquals(l2, sortedList);

        l1 = new ArrayList<Integer>();
        sortedList = new ArrayList<Integer>();
        convertArrayIntoArrayList(arr2, l1);
        convertArrayIntoArrayList(sortedArr2, sortedList);
        l2 = (ArrayList<Integer>) l1.clone();
        tempList = (ArrayList<Integer>) l1.clone();
        Sorts.mergeSort(l1);
        Sorts.eventSort(l2, Sorts.mergeSort(tempList));
        assertEquals(l1, sortedList);
        assertEquals(l2, sortedList);

        l1 = new ArrayList<Integer>();
        sortedList = new ArrayList<Integer>();
        convertArrayIntoArrayList(arr3, l1);
        convertArrayIntoArrayList(sortedArr3, sortedList);
        l2 = (ArrayList<Integer>) l1.clone();
        tempList = (ArrayList<Integer>) l1.clone();
        Sorts.mergeSort(l1);
        Sorts.eventSort(l2, Sorts.mergeSort(tempList));
        assertEquals(l1, sortedList);
        assertEquals(l2, sortedList);

        l1 = new ArrayList<Integer>();
        sortedList = new ArrayList<Integer>();
        convertArrayIntoArrayList(arr4, l1);
        convertArrayIntoArrayList(sortedArr4, sortedList);
        l2 = (ArrayList<Integer>) l1.clone();
        tempList = (ArrayList<Integer>) l1.clone();
        Sorts.mergeSort(l1);
        Sorts.eventSort(l2, Sorts.mergeSort(tempList));
        assertEquals(l1, sortedList);
        assertEquals(l2, sortedList);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testQuickSort() {
        l1 = new ArrayList<Integer>();
        sortedList = new ArrayList<Integer>();
        convertArrayIntoArrayList(arr1, l1);
        convertArrayIntoArrayList(sortedArr1, sortedList);
        l2 = (ArrayList<Integer>) l1.clone();
        tempList = (ArrayList<Integer>) l1.clone();
        Sorts.quickSort(l1);
        Sorts.eventSort(l2, Sorts.quickSort(tempList));
        assertEquals(l1, sortedList);
        assertEquals(l2, sortedList);

        l1 = new ArrayList<Integer>();
        sortedList = new ArrayList<Integer>();
        convertArrayIntoArrayList(arr2, l1);
        convertArrayIntoArrayList(sortedArr2, sortedList);
        l2 = (ArrayList<Integer>) l1.clone();
        tempList = (ArrayList<Integer>) l1.clone();
        Sorts.quickSort(l1);
        Sorts.eventSort(l2, Sorts.quickSort(tempList));
        assertEquals(l1, sortedList);
        assertEquals(l2, sortedList);

        l1 = new ArrayList<Integer>();
        sortedList = new ArrayList<Integer>();
        convertArrayIntoArrayList(arr3, l1);
        convertArrayIntoArrayList(sortedArr3, sortedList);
        l2 = (ArrayList<Integer>) l1.clone();
        tempList = (ArrayList<Integer>) l1.clone();
        Sorts.quickSort(l1);
        Sorts.eventSort(l2, Sorts.quickSort(tempList));
        assertEquals(l1, sortedList);
        assertEquals(l2, sortedList);

        l1 = new ArrayList<Integer>();
        sortedList = new ArrayList<Integer>();
        convertArrayIntoArrayList(arr4, l1);
        convertArrayIntoArrayList(sortedArr4, sortedList);
        l2 = (ArrayList<Integer>) l1.clone();
        tempList = (ArrayList<Integer>) l1.clone();
        Sorts.quickSort(l1);
        Sorts.eventSort(l2, Sorts.quickSort(tempList));
        assertEquals(l1, sortedList);
        assertEquals(l2, sortedList);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testCustomSort() {
        l1 = new ArrayList<Integer>();
        sortedList = new ArrayList<Integer>();
        convertArrayIntoArrayList(arr1, l1);
        convertArrayIntoArrayList(sortedArr1, sortedList);
        l2 = (ArrayList<Integer>) l1.clone();
        tempList = (ArrayList<Integer>) l1.clone();
        Sorts.customSort(l1);
        Sorts.eventSort(l2, Sorts.customSort(tempList));
        assertEquals(l1, sortedList);
        assertEquals(l2, sortedList);

        l1 = new ArrayList<Integer>();
        sortedList = new ArrayList<Integer>();
        convertArrayIntoArrayList(arr2, l1);
        convertArrayIntoArrayList(sortedArr2, sortedList);
        l2 = (ArrayList<Integer>) l1.clone();
        tempList = (ArrayList<Integer>) l1.clone();
        Sorts.customSort(l1);
        Sorts.eventSort(l2, Sorts.customSort(tempList));
        assertEquals(l1, sortedList);
        assertEquals(l2, sortedList);

        l1 = new ArrayList<Integer>();
        sortedList = new ArrayList<Integer>();
        convertArrayIntoArrayList(arr3, l1);
        convertArrayIntoArrayList(sortedArr3, sortedList);
        l2 = (ArrayList<Integer>) l1.clone();
        tempList = (ArrayList<Integer>) l1.clone();
        Sorts.customSort(l1);
        Sorts.eventSort(l2, Sorts.customSort(tempList));
        assertEquals(l1, sortedList);
        assertEquals(l2, sortedList);

        l1 = new ArrayList<Integer>();
        sortedList = new ArrayList<Integer>();
        convertArrayIntoArrayList(arr4, l1);
        convertArrayIntoArrayList(sortedArr4, sortedList);
        l2 = (ArrayList<Integer>) l1.clone();
        tempList = (ArrayList<Integer>) l1.clone();
        Sorts.customSort(l1);
        Sorts.eventSort(l2, Sorts.customSort(tempList));
        assertEquals(l1, sortedList);
        assertEquals(l2, sortedList);
    }

}
