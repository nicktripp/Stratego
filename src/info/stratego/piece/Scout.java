package info.stratego.piece;

import info.stratego.grid.Space;

import java.awt.Color;
import java.util.ArrayList;

public class Scout extends Piece
{
	public Scout(Color aColor)
	{
		super("9", aColor);
	}
	
	@Override
	public ArrayList<Space> getMoveSpaces()
	{
		ArrayList<Space> locs = new ArrayList<Space>();
		for(int x = 0; x < 360; x += 90)
		{
			Space nextLoc = getSpace().getAdjacentSpace(x);
			while(getGrid().isValid(nextLoc) && getGrid().get(nextLoc) == null)
			{
				locs.add(nextLoc);
				nextLoc = nextLoc.getAdjacentSpace(x);
			}
		}
		
		return locs;
	}
}

