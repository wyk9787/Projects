package edu.grinnell.sortingvisualizer.sortevents;

import java.util.ArrayList;
import java.util.List;

public class CopyEvent<T> implements SortEvent<T>{

    private int index;
    private T value;
    public ArrayList<T> valueList;
    
    public CopyEvent(int i, T value) {
        this.index = i;
        this.value = value;
    }
    
    public void apply(ArrayList<T> l) {
        l.set(index, value);
    }

    public List<Integer> getAffectedIndices() {
        List<Integer> l = new ArrayList<>();
        l.add(index);
        return l;
    }

    public boolean isEmphasized() {
        return true;
    }
    
}
