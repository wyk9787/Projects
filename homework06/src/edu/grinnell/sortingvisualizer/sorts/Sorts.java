package edu.grinnell.sortingvisualizer.sorts;

import java.util.ArrayList;
import edu.grinnell.sortingvisualizer.sortevents.CompareEvent;
import edu.grinnell.sortingvisualizer.sortevents.CopyEvent;
import edu.grinnell.sortingvisualizer.sortevents.SortEvent;
import edu.grinnell.sortingvisualizer.sortevents.SwapEvent;

public class Sorts {

	public static <T extends Comparable <T>>void swap(ArrayList<T> l, int i, int j) {
		T temp = l.get(i);
		l.set(i, l.get(j));
		l.set(j, temp);
	}

	public static <T extends Comparable <T>> ArrayList<SortEvent<T>> selectionSort(ArrayList<T> l){
		ArrayList<SortEvent<T>> sortEventList = new ArrayList<SortEvent<T>>();
		for(int i = 0; i < l.size(); i++) {
			int pos = i;
			for(int j = i + 1; j < l.size(); j ++) {
				SortEvent<T> com = new CompareEvent<T>(j, pos);
				sortEventList.add(com);
				if(l.get(j).compareTo(l.get(pos)) < 0) {
					pos = j;
				}
			}
			SortEvent<T> swap = new SwapEvent<T>(i, pos);
			sortEventList.add(swap);
			swap(l, i, pos);
		}
		return sortEventList;
	}

	public static <T extends Comparable <T>> ArrayList<SortEvent<T>> insertionSort(ArrayList<T> l){
		ArrayList<SortEvent<T>> sortEventList = new ArrayList<SortEvent<T>>();
		for(int i = 0; i < l.size(); i++){
			for(int j = i ; j > 0; j--){
				SortEvent<T> com = new CompareEvent<T>(j - 1, j);
				sortEventList.add(com);
				if(l.get(j - 1).compareTo(l.get(j)) > 0){
					SortEvent<T> swap = new SwapEvent<T>(j - 1, j);
					sortEventList.add(swap);
					swap(l, j - 1, j);
				}
			}
		}
		return sortEventList;
	}

	public static <T extends Comparable <T>> ArrayList<SortEvent<T>> bubbleSort(ArrayList<Integer> l) {
		ArrayList<SortEvent<T>> sortEventList = new ArrayList<SortEvent<T>>();
		for(int i = 0; i < l.size(); i++ ) {
			for(int j = 1; j < l.size() - i; j++) {
				SortEvent<T> com = new CompareEvent<T>(j - 1, j);
				sortEventList.add(com);
				if (l.get(j - 1).compareTo(l.get(j)) > 0) {
					SortEvent<T> swap = new SwapEvent<T>(j - 1, j);
					sortEventList.add(swap);
					swap(l, j, j - 1);            
				} 
			}
		} 
		return sortEventList;
	} 

	private static <T extends Comparable<T>> void merge(ArrayList<T> l, int lo, int mid, int hi, ArrayList<SortEvent<T>> sortEventList) {
		ArrayList<T> temp = new ArrayList<>();
		int i = lo;
		int j = mid + 1;
		while(i <= mid && j <= hi) {
			SortEvent<T> com = new CompareEvent<T>(i, j);
			sortEventList.add(com);
			if(l.get(i).compareTo(l.get(j)) < 0) {
				temp.add(l.get(i++));
			} else {
				temp.add(l.get(j++));
			}
		}
		while(i <= mid) {
			temp.add(l.get(i++));
		}
		while(j <= hi) {
			temp.add(l.get(j++));
		}

		int m = 0;
		for(int k = lo; k <= hi; k++) {
			SortEvent<T> copy = new CopyEvent<T>(k, temp.get(m));
			sortEventList.add(copy);
			l.set(k, temp.get(m++));
		}
	}

	private static <T extends Comparable<T>> void mergeSortHelper(ArrayList<T> list, int lo, int hi, ArrayList<SortEvent<T>> sortEventList) {
		if(lo < hi) {
			int middle = (hi + lo) / 2;
			mergeSortHelper(list, lo, middle, sortEventList);
			mergeSortHelper(list, middle + 1, hi, sortEventList);
			merge(list, lo, middle, hi, sortEventList);
		}
	}

	public static <T extends Comparable<T>> ArrayList<SortEvent<T>> mergeSort(ArrayList<T> list) {
		ArrayList<SortEvent<T>> sortEventList = new ArrayList<SortEvent<T>>();
		mergeSortHelper(list, 0, list.size() - 1, sortEventList);
		return sortEventList;
	}
	private static <T extends Comparable<T>> int partition(ArrayList<T> l, int low, int hi, int pivotIndex, ArrayList<SortEvent<T>> sortEventList) {
		SortEvent<T> swap = new SwapEvent<T>(low + pivotIndex, hi);
		sortEventList.add(swap);
		swap(l, low + pivotIndex, hi);
		int left = low;
		int right = hi - 1;
		while(left <= right) {
			while(l.get(left).compareTo(l.get(hi)) < 0) {
				SortEvent<T> com = new CompareEvent<T>(left, hi);
				sortEventList.add(com);
				left++;
				if(left > hi - 1) {
					break;
				}
			}

			while(l.get(right).compareTo(l.get(hi)) > 0) {
				SortEvent<T> com = new CompareEvent<T>(right, hi);
				sortEventList.add(com);
				right--;
				if(right < low) {
					break;
				}
			}

			if(left <= right) {
				swap = new SwapEvent<T>(left, right);
				sortEventList.add(swap);
				swap(l, left++, right--);
			}
		}
		swap = new SwapEvent<T>(left, hi);
		sortEventList.add(swap);
		swap(l, left, hi);
		return left;
	}

	private static <T extends Comparable<T>> void quickSortHelper(ArrayList<T> l, int low, int hi, ArrayList<SortEvent<T>> sortEventList) {
		if(low >= hi) {
			return;
		}
		int k =  (int) (Math.random() * (hi - low));
		int m = partition(l, low, hi, k, sortEventList);
		quickSortHelper(l, low, m - 1, sortEventList);
		quickSortHelper(l, m + 1, hi, sortEventList);
	}

	public static <T extends Comparable<T>> ArrayList<SortEvent<T>> quickSort(ArrayList<T> l) {
		ArrayList<SortEvent<T>> sortEventList = new ArrayList<SortEvent<T>>();
		quickSortHelper(l, 0, l.size() - 1, sortEventList);
		return sortEventList;
	}

	public static <T extends Comparable<T>>  ArrayList<SortEvent<T>> customSort(ArrayList<T> l){
		ArrayList<SortEvent<T>> sortEventList = new ArrayList<SortEvent<T>>();
		ArrayList <T> temp = new ArrayList<T>();
        for(int i = 0; i < l.size(); i ++) {
            temp.add(l.get(0));
        }
        int seen = 0;
        for(int i=0; i<l.size(); i++){
            int place = 0;
            for(int j=0; j<l.size(); j++){
            	SortEvent<T> com = new CompareEvent<T>(i, j);
    			sortEventList.add(com);
                if(l.get(i).compareTo(l.get(j)) > 0){
                    place++;
                }
            }
            if(l.get(i).compareTo(l.get(0)) == 0){
                place = place + seen;
                seen++;
            }else {
                while(temp.get(place).compareTo(l.get(i)) == 0){
                    place++;
                    if(place > temp.size() - 1) {
                        break;
                    }
                }
            }
            temp.set(place, l.get(i));
        }
        for(int i = 0; i<l.size(); i++){
            SortEvent<T> copy = new CopyEvent<T>(i, temp.get(i));
            sortEventList.add(copy);
            l.set(i, temp.get(i));
        }
        
        return sortEventList;
    }    

	public static <T extends Comparable<T>> void eventSort(ArrayList<T> l, ArrayList<SortEvent<T>> events){
		for(int i = 0; i < events.size(); i++) {
			events.get(i).apply(l);
		}
	}
	
}
