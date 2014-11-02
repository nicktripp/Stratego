package info.stratego.piece;

import info.stratego.grid.Space;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Bomb extends Piece
{
	public Bomb(Color aColor) //CHANGE PARAM
	{
		super("Bomb", aColor);
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
		if (attacker.getIntValue() == MINER)
		{
			return attacker;
		}
		else
		{
			return this;
		}
	}
	
	public Piece attack(Piece other)
	{
		throw new IllegalStateException("Bombs cannot attack");
	}
	
	@Override
	public ArrayList<Space> getMoveSpaces()
	{
		return new ArrayList<Space>();
	}
	
	public String getValue()
	{
		return "Bomb";
	}
	
	private static final int MINER = 8;
}
