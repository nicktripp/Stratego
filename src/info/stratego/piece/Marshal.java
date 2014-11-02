package info.stratego.piece;

import java.awt.Color;

public class Marshal extends Piece
{
	public Marshal(Color aColor)
	{
		super("1", aColor);
	}
	
	@Override
	public Piece defend(Piece other)
	{
		if (other.getIntValue() == 10)
		{
			return other;
		}
		else 
			return super.defend(other);
	}
}

