package gui;

import info.stratego.grid.Space;
import info.stratego.piece.Piece;
import info.stratego.piece.Water;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class PieceButton extends JButton
{
	private boolean frontVisible;
	private Piece piece;
	private Space space;
	
	public PieceButton() 
	{
		super();
		
		piece = null;
		frontVisible = true;
		setDefaults();
		
		space = new Space(currentCol % 10, currentRow % 10);
		currentCol++;
		if (currentCol % 10 == 0)
			currentRow++;
	}
	
	public PieceButton(Piece aPiece)
	{
		super(aPiece.getIcon());
		
		piece = aPiece;
		frontVisible = true;
		this.setPieceBack();
		setDefaults();
		
		space = new Space(currentCol % 10, currentRow % 10);
		currentCol++;
		currentRow++;
	}
	
	private void setDefaults() 
	{
		this.setSize(80, 80);
		this.setMinimumSize(new Dimension(80, 80));
		this.setPreferredSize(new Dimension(80, 80));
		this.setBorder(normalBorder);
		this.setBackground(Color.WHITE);
		this.setOpaque(true);
	}
	
	public void thickenBorder()
	{
		this.setBorder(thickBorder);
	}
	
	public void normalBorder()
	{
		this.setBorder(normalBorder);
	}
	
	public void clearIcons()
	{
		setIcon(null);
		pieceBack = null;
	}
	
	public void setPiece(Piece newPiece) 
	{
		clearIcons();
		piece = newPiece;
		if (piece != null)
		{
			setPieceBack();
			setIcon(piece.getIcon());
		}
		frontVisible = true;
	}
	
	private void setPieceBack() 
	{
		if (piece.getColor().equals(Color.RED))
			pieceBack = new ImageIcon(Piece.root + "/rsc/RED/piece_BACK.jpg");
		else
			pieceBack = new ImageIcon(Piece.root + "/rsc/BLUE/piece_BACK.jpg");
		
		if (piece instanceof Water)
			pieceBack = new ImageIcon(Piece.root + "/rsc/water/water.jpg");
	}
	
	public void flipPiece()
	{
		if (piece != null)
		{
			if (frontVisible)
				this.setIcon(pieceBack);
			else
				this.setIcon(piece.getIcon());
			frontVisible = !frontVisible;
		}
	}
	
	public void redBorder()
	{
		setBorder(redBorder);
	}
	
	public void greenBorder()
	{
		setBorder(greenBorder);
	}
	
	public Space getSpace() { return space; }
	
	public Piece getPiece() { return piece; }
	
	public boolean isFrontVisible() { return frontVisible; }
	
	private static final Border normalBorder = BorderFactory.createLineBorder(Color.BLACK, 1);
	private static final Border thickBorder = BorderFactory.createLineBorder(Color.BLACK, 3);
	private static final Border greenBorder = BorderFactory.createLineBorder(Color.GREEN, 3);
	private static final Border redBorder = BorderFactory.createLineBorder(Color.RED, 3);
	private Icon pieceBack; 
	
	private static int currentCol = 0;
	private static int currentRow = 0;
}
