package edu.grinnell.sortingvisualizer;

import java.util.ArrayList;
import java.util.Random;

/**
 * A collection of indices into a Scale object.
 * These indices are the subject of the various sorting algorithms
 * in the program.
 */
public class NoteIndices {

	private ArrayList<Integer> indicesArray;
	private ArrayList<Boolean> highlightedIndices;
	
    /**
     * @param n the size of the scale object that these indices map into
     */
    public NoteIndices(int n) {
        indicesArray = new ArrayList<Integer>();
        highlightedIndices = new ArrayList<Boolean>();
    }
    
    /**
     * Reinitializes this collection of indices to map into a new scale object
     * of the given size.  The collection is also shuffled to provide an
     * initial starting point for the sorting process.
     * @param n the size of the scale object that these indices map into
     */
    public void initializeAndShuffle(int n) {
    	indicesArray.clear();
		for(int i = 0; i < n; i++) {
			indicesArray.add(i);
		}
    	Random rand = new Random();
    	for (int i = 0; i < n - 1; i++) {
    		int j = rand.nextInt(n - i) + i;
    		int temp = indicesArray.get(j);
    		indicesArray.set(j, indicesArray.get(i));
    		indicesArray.set(i, temp);
    	}
        for(int i = 0; i < n; i++) {
        	highlightedIndices.add(false);
        }
    }
    
    /** @return the indices of this NoteIndices object */
    public ArrayList<Integer> getNotes() { 
    	return indicesArray;
    }
    
    /**
     * Highlights the given index of the note array
     * @param index the index to highlight
     */
    public void highlightNote(int index) {
        highlightedIndices.set(index, true);
    }
    
    /** @return true if the given index is highlighted */
    public boolean isHighlighted(int index) {
    	return highlightedIndices.get(index);
    }
    
    /** Clears all highlighted indices from this collection */
    public void clearAllHighlighted() {
        for(int i = 0; i < highlightedIndices.size(); i++) {
        	highlightedIndices.set(i, false);
        }
    }
}
