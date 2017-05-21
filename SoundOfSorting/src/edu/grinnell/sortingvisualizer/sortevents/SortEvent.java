package edu.grinnell.sortingvisualizer.sortevents;

import java.util.ArrayList;
import java.util.List;

public interface SortEvent<T> {
    void apply(ArrayList<T> l);
    
    List<Integer> getAffectedIndices();
    
    boolean isEmphasized();
}
