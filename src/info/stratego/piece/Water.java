package info.stratego.piece;

import info.stratego.grid.Space;

import java.awt.Color;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Water extends Piece
{
	public Water()
	{
		super("W", Color.BLACK);
		setIcon(ICON);
	}
	
	public Piece defend(Piece other)
	{
		throw new IllegalStateException("Piece cannot move here!");
	}
	
	public void moveTo(Space nextSpace)
	{
		throw new IllegalStateException("This piece cannot move!");
	}
	
	public Piece attack(Piece other)
	{
		throw new IllegalStateException("This piece cannot attack!");
	}
	
	private static final Icon ICON = new ImageIcon(Piece.root + "/rsc/water/water.jpg");
}
