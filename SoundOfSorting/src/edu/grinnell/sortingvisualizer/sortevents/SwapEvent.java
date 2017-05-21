package edu.grinnell.sortingvisualizer.sortevents;

import java.util.ArrayList;
import java.util.List;

public class SwapEvent<T> implements SortEvent<T>{

    public List<Integer> affectedIndices;
    
    public SwapEvent(int i, int j) {
    	affectedIndices = new ArrayList<Integer>();
        affectedIndices.add(i);
        affectedIndices.add(j);
    }
    
    public void apply(ArrayList<T> l) {
        T temp = l.get(affectedIndices.get(0));
        l.set(affectedIndices.get(0), l.get(affectedIndices.get(1)));
        l.set(affectedIndices.get(1), temp);
    }
    
    public List<Integer> getAffectedIndices() {
        return affectedIndices;
    }

    public boolean isEmphasized() {
        return true;
    }
    
}
