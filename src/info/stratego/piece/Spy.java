package info.stratego.piece;

import java.awt.Color;

import javax.swing.ImageIcon;

public class Spy extends Piece
{
	public Spy(Color aColor)
	{
		super("10", aColor);
		
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
	
	public String getValue()
	{
		return "Spy";
	}
}
