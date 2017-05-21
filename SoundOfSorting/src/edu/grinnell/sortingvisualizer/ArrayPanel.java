package edu.grinnell.sortingvisualizer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ArrayPanel extends JPanel {

	private NoteIndices notes;
	private int width;
	private int height;
	
	/**
	 * Constructs a new ArrayPanel that renders the given note indices to
	 * the screen.
	 * @param notes the indices to render
	 * @param width the width of the panel
	 * @param height the height of the panel
	 */
	public ArrayPanel(NoteIndices notes, int width, int height) {
		this.width = width;
		this.height = height;
		this.notes = notes;
		this.setPreferredSize(new Dimension(width, height));
	}

	@Override
	public void paintComponent(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
		int recWidth = this.width / notes.getNotes().size();
		int recHeight = this.height / notes.getNotes().size();
		for(int i = 0; i < notes.getNotes().size(); i++) {
			if(notes.isHighlighted(i)) {
				g.setColor(Color.YELLOW);
			} else {
				g.setColor(new Color(0, 
									 255 * notes.getNotes().get(i) / notes.getNotes().size(), 
									 255 - 255 * notes.getNotes().get(i) / notes.getNotes().size()));
			}
			g.fillRect(i * recWidth, (notes.getNotes().size() - notes.getNotes().get(i) - 1) * recHeight, recWidth, (notes.getNotes().get(i) + 1) * recHeight);
		}		
	}
}