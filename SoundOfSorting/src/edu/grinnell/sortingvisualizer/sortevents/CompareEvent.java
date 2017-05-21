package edu.grinnell.sortingvisualizer.sortevents;

import java.util.ArrayList;
import java.util.List;

public class CompareEvent<T> implements SortEvent<T>{
    
    public List<Integer> affectedIndices;
    
    public CompareEvent(int i, int j) {
    	affectedIndices = new ArrayList<Integer>();
        affectedIndices.add(i);
        affectedIndices.add(j);
    }

    public void apply(ArrayList<T> l) {
       return;
    }

    public List<Integer> getAffectedIndices() {
        return this.affectedIndices;
    }

    public boolean isEmphasized() {
        return false;
    }

}
