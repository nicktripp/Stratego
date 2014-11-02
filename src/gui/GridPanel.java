package gui;

import info.stratego.grid.Grid;
import info.stratego.grid.Space;
import info.stratego.piece.Piece;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GridPanel extends JPanel
{
 	public GridPanel(ActionListener listener)
	{
		 super(new GridLayout(COLS, ROWS));
		 this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		 this.setSize(800, 800);
		 this.setMinimumSize(getSize());
		 this.setMaximumSize(getSize());
		 this.setPreferredSize(getSize());
		 myListener = listener;
		 
		 
		 myGrid = new Grid<Piece>(COLS, ROWS);
		 
		
		 for (int i = 0; i < 100; i++)
		 {
			 addButton(new PieceButton());
		 }
 	}
 	
 	public void showArmy(Color color) 
 	{
 		ArrayList<Piece> anArmy = myGrid.getColoredPieces(color);
 		
		for (Piece x: anArmy)
		{
			PieceButton y = getButton(x.getSpace());
			if (!y.isFrontVisible())
				y.flipPiece();
		}
 	}
 	
 	public void hideAll()
 	{
 		ArrayList<Piece> allPieces = myGrid.getAllPieces();
 		
 		for (Piece e : allPieces)
 		{
 			PieceButton button = this.getButton(e.getSpace());
 			if (button.isFrontVisible())
 				button.flipPiece();
 		}
 	}
 	
 	private void addButton(PieceButton pb)
 	{
 		pb.addActionListener(myListener);
 		this.add(pb);
 	}
 	
	public PieceButton getButton(Space loc)
	{
		int n = loc.getRow() * COLS + loc.getCol();
		return (PieceButton) getComponent(n);
	}
	
	public void showAll()
 	{
 		ArrayList<Piece> allPieces = myGrid.getAllPieces();
 		
 		for (Piece e : allPieces)
 		{
 			PieceButton button = this.getButton(e.getSpace());
 			if (!button.isFrontVisible())
 				button.flipPiece();
 		}
 	}
	
	public void setPiece(Space loc, Piece aPiece) 
	{
		if (aPiece != null)
			aPiece.putSelfInGrid(myGrid, loc);
		PieceButton button = getButton(loc);
		button.setPiece(aPiece);
	}
	
	public Piece removePiece(Space loc)
	{
		PieceButton button = getButton(loc);
		Piece oldPiece = button.getPiece();
		button.setPiece(null);
		button.clearIcons();
		if (oldPiece != null)
			oldPiece.removeSelfFromGrid();
		return oldPiece;
	}
	
	public void setActiveButton(PieceButton pb)
	{
		activeButton = pb;
	}
	
	public PieceButton getActiveButton() { return activeButton; }
	
	public Grid<Piece> getGrid() { return myGrid; }
	
	/*public ArrayList<Piece> getAdjacentPieces(PieceButton aPieceButton) //NOT NEEDED
	{
		ArrayList<Piece> adjPieces = new ArrayList<Piece>();
		Space east = new Space(aPieceButton.getPiece().getSpace().getCol() + 1, aPieceButton.getPiece().getSpace().getRow()); 
		Space west = new Space(aPieceButton.getPiece().getSpace().getCol() - 1, aPieceButton.getPiece().getSpace().getRow());
		Space south = new Space(aPieceButton.getPiece().getSpace().getCol(), aPieceButton.getPiece().getSpace().getRow() + 1);
		Space north = new Space(aPieceButton.getPiece().getSpace().getCol(), aPieceButton.getPiece().getSpace().getRow() - 1);
		Space[] adjSpaces = {east, west, south, north};
		for (Space x: adjSpaces)
			if (x != null )
				adjPieces.add(getButton(x).getPiece());
		return adjPieces;
		
	}*/
	
	private Grid<Piece> myGrid;
	private PieceButton activeButton;
	private static final int ROWS = 10;
    private static final int COLS = 10;

	private ActionListener myListener;

 
}
