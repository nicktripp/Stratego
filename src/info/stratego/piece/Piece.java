package info.stratego.piece;

import info.stratego.grid.Grid;
import info.stratego.grid.Space;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Piece 
{
	private String value;
	private Space space;
	private Grid<Piece> grid;
	private Color color;
	private Icon icon;
	public static final String root = System.getProperty("user.dir");
	
	public Piece(String aValue, Color aColor)
	{
		value = aValue;
		space = null;
		color = aColor;
		findIcon();
	}
	
	public void setIcon(Icon anIcon)
	{
		icon = anIcon;
	}
	
	public void findIcon()
	{
		String colorString = null;
		if (color.equals(Color.RED))
			colorString = "RED";
		else
			colorString = "BLUE";
		
		try 
		{
			String fileExtension = getValue();
			if (getIntValue() == 10)
				fileExtension = "S";
			if (getIntValue() == 1)
				fileExtension = "1";
			icon = new ImageIcon(root + "/rsc/" + colorString + "/piece_" + fileExtension + ".jpg");
		}
		catch (NumberFormatException exception)
		{
			//EVERYTHINGISGOINGACCORDINGTOPLAN
		}
			
	}
	
	public void putSelfInGrid(Grid<Piece> gr, Space loc)
    {
        if (grid != null)
            throw new IllegalStateException(
                    "This piece is already contained in a grid.");

        Piece piece = gr.get(loc);
        if (piece != null)
            piece.removeSelfFromGrid();
        gr.put(loc, this);
        grid = gr;
        space = loc;
    }
	
	public void removeSelfFromGrid()
    {
        if (grid == null)
            throw new IllegalStateException(
                    "This piece is not contained in a grid.");
        if (grid.get(space) != this)
            throw new IllegalStateException(
                    "The grid contains a different piece at location "
                            + space + ".");

        grid.remove(space);
        grid = null;
        space = null;
    }
	
	public String getValue() { return value; }
	
	public int getIntValue()
	{
		return Integer.parseInt(value);
	}
	
	public Piece attack(Piece other)
	{
		return other.defend(this);
	}
	
	public Piece defend(Piece attacker)
	{
		if (this.getIntValue() < attacker.getIntValue())
		{	
			return this;
		}
		else if (this.getIntValue() > attacker.getIntValue())
		{
			return attacker;
		}
		else
			return null;
	}
	
	public Color getColor()
	{
		return color;
	}
	
	public Icon getIcon()
	{
		return icon;
	}
	
	public void moveTo(Space nextSpace)
	{
		if (getGrid().isValid(nextSpace))
		{
			if (getGrid().get(nextSpace) != null)
				getGrid().get(nextSpace).removeSelfFromGrid();
			space = nextSpace;
		}
		else
			throw new NullPointerException("This Piece is not in a grid!");
	}
	
	public Space getSpace() { return space; }
	public Grid<Piece> getGrid() { return grid; }
	
	public ArrayList<Space> getMoveSpaces()
	{
		ArrayList<Space> locs = new ArrayList<Space>();
		
		for(int x = 0; x < 360; x += 90)
		{
			Space loc = getSpace().getAdjacentSpace(x);
			if (getGrid().isValid(loc) && getGrid().get(loc) == null)
			{
				locs.add(loc);
			}
		}
		
		return locs;
	}
	
	
}
