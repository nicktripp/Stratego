package info.stratego.piece;

import info.stratego.grid.Space;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Flag extends Piece
{
	public Flag(Color aColor)
	{
		super("Flag", aColor);
	}
	
	@Override
	public void findIcon()
	{
		String colorString = null;
		if (getColor().equals(Color.RED))
			colorString = "RED";
		else
			colorString = "BLUE";
		String fileExtension = getValue();
		setIcon(new ImageIcon(Piece.root + "/rsc/" + colorString + "/piece_" + fileExtension + ".jpg"));
	}
	
	@Override
	public Piece defend(Piece attacker)
	{
		System.out.println(attacker.getColor());
		return attacker;
	}
	
	@Override
	public ArrayList<Space> getMoveSpaces()
	{
		return new ArrayList<Space>();
	}
	
	public String getValue()
	{
		return "Flag";
	}
	
}
