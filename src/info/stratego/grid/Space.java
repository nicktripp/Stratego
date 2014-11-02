package info.stratego.grid;

public class Space 
{
	private int col;
	private int row;
	
	public Space(int Column, int Row)
	{
		col = Column;
		row = Row;
	}
	
	public int getRow()
	{
		return row;
	}
	
	public int getCol()
	{
		return col;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		Space other = (Space) obj;
		return other.getCol() == this.getCol() && other.getRow() == this.getRow();
	}
	
	@Override
	public String toString()
	{
		return "( " + getCol() + ", " + getRow() + ")";
	}
	
	/**
	 * Precondition: direction is > 0
	 * @param direction
	 * @return
	 */
	public Space getAdjacentSpace(int direction)
	{
		int realDir = direction % 360;
		
		Space loc = null;
		
		if (realDir > 315 || realDir <= 45)
		{
			loc = new Space(col, row + 1);
		}
		else if (realDir > 45 && realDir <= 135)
		{
			loc = new Space(col + 1, row);
		}
		else if (realDir > 135 && realDir <= 225)
		{
			loc = new Space(col, row - 1);
		}
		else if (realDir > 225 && realDir <= 315)
		{
			loc = new Space(col - 1, row);
		}
		
		return loc;
	}
	
	
	public final int FORWARD = 0;
	public final int BACKWARD = 180;
	public final int RIGHT = 90;
	public final int LEFT = 270;
}
