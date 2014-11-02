package info.stratego.grid;

import info.stratego.piece.Bomb;
import info.stratego.piece.Flag;
import info.stratego.piece.Piece;
import info.stratego.piece.Water;

import java.awt.Color;
import java.util.ArrayList;

public class Grid<E> 
{
	private Object[][] occupants;
	
	public Grid(int rows, int cols)
    {
        if (rows <= 0)
            throw new IllegalArgumentException("rows <= 0");
        if (cols <= 0)
            throw new IllegalArgumentException("cols <= 0");
        occupants = new Object[rows][cols];
    }
	
	public int getNumRows()
    {
        return occupants.length;
    }
	
	public int getNumCols()
    {
        return occupants[0].length;
    }
	
	public ArrayList<Piece> getAllPieces()
	{
		ArrayList<Piece> pieces = new ArrayList<Piece>();
		for (Object[] objArr : occupants)
		{
			for (Object obj : objArr)
			{
				if (obj != null)
					pieces.add((Piece)obj);
			}
		}
		return pieces;
	}
	
	public ArrayList<Piece> getColoredPieces(Color color)
	{
		ArrayList<Piece> pieces = new ArrayList<Piece>();
		for (Object[] objArr : occupants)
		{
			for (Object obj : objArr)
			{
				if (obj != null && ((Piece)obj).getColor().equals(color))
					pieces.add((Piece)obj);
			}
		}
		return pieces;
	}
	
	public E put(Space space, E obj)
    {
        if (!isValid(space))
            throw new IllegalArgumentException("Location " + space
                    + " is not valid");
        if (obj == null)
            throw new NullPointerException("obj == null");

        // Add the object to the grid.
        E oldOccupant = get(space);
        occupants[space.getRow()][space.getCol()] = obj;
        return oldOccupant;
    }
	
	@SuppressWarnings("unchecked")
	public E get(Space space)
    {
        if (!isValid(space))
            throw new IllegalArgumentException("Location " + space
                    + " is not valid");
        return (E) occupants[space.getRow()][space.getCol()];
    }
	
	public boolean isValid(Space space)
    {
        return 0 <= space.getRow() && space.getRow() < getNumRows()
                && 0 <= space.getCol() && space.getCol() < getNumCols();
    }
	
	public E remove(Space space)
    {
        if (!isValid(space))
            throw new IllegalArgumentException("Location " + space
                    + " is not valid");
        
        // Remove the object from the grid.
        E r = get(space);
        occupants[space.getRow()][space.getCol()] = null;
        return r;
    }

	public ArrayList<Space> getAdjacentSpaces(Space space)
	{
		ArrayList<Space> adj = new ArrayList<Space>();
		for(int i = 0; i < 360; i += 90)
		{
			Space next = space.getAdjacentSpace(i);
			if(isValid(next))
				adj.add(next);
		}
		
		return adj;
	}
	
	public ArrayList<Space> getAttackable(Space attackingLoc)
	{
		ArrayList<Space> targets = new ArrayList<Space>();
		Piece attacker = (Piece) this.get(attackingLoc);
		if(attacker != null && !((Object) (attacker) instanceof Water) && !((Object) (attacker) instanceof Bomb) && !((Object) (attacker) instanceof Flag))
		{
			ArrayList<Space> adjSpaces = getAdjacentSpaces(attackingLoc);
			for (Space e : adjSpaces)
			{
				Piece defender = (Piece) this.get(e); // The e here used to be attackingLoc
				if(defender != null && !((Object) (defender) instanceof Water) && !attacker.getColor().equals(defender.getColor()))
					targets.add(e);
			}
		}
			
		return targets;
	}
}
