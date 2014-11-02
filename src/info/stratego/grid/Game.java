package info.stratego.grid;

import gui.GridPanel;
import gui.GuiFrame;
import gui.PieceButton;
import info.stratego.piece.Bomb;
import info.stratego.piece.Flag;
import info.stratego.piece.Marshal;
import info.stratego.piece.Piece;
import info.stratego.piece.Scout;
import info.stratego.piece.Spy;
import info.stratego.piece.Water;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Game 
{
	private static final ArrayList<Piece> redArmy = new ArrayList<Piece>();
	private static final ArrayList<Piece> blueArmy = new ArrayList<Piece>();
	private static enum Army { RED, BLUE };
	private Army activeArmy;
	private Army otherArmy;
	private GuiFrame gui;
	
	public static final int PLACEMENT = 0;
	public static final int PLAYING1 = 1;
	public static final int PLAYING2 = 2;
	public static final int WIN = 42;
	public static final int TRANSITION1 = 3;
	public static final int TRANSITION2 = 4;
	
	private int state;
	private int previousState;

	public Game()
	{
		state = PLACEMENT;
		previousState = -1;
				
		populateArmy(Army.BLUE);
		populateArmy(Army.RED);
		
		activeArmy = Army.RED;
		otherArmy = Army.BLUE;
		
		ActionListener pblisten = new PBListener();
		
		gui = new GuiFrame(pblisten);
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setLocationRelativeTo(null);
		gui.setVisible(true);
		placeWater();
 	}
 		
	public Color getActiveArmyColor()
	{
		if (activeArmy == Army.RED)
			return Color.RED;
		else
			return Color.BLUE;
	}
	
	/**
	 * Ends the current game, disabling the GUI
	 */
	public void end()
	{
		gui.getGridPanel().showAll();
		gui.setTextField("*|~~~ FLAG CAPTURED: " + activeArmy.name() + " WINS!! ~~~|*\nFor a new Game, close and restart Stratego.");
		gui.getGridPanel().getActiveButton().greenBorder();
		state = WIN;
	}
		
	public void toggleArmies()
	{
		if (activeArmy == Army.BLUE)
			selectArmy(Army.RED);
		else if (activeArmy == Army.RED)
			selectArmy(Army.BLUE);
	}
	
	public void selectArmy(Army nextArmy)
	{
		otherArmy = activeArmy;
		gui.getGridPanel().hideAll();
		activeArmy = nextArmy;
	}
	
	/**
	 * SETS THE RED ARMY FIRST AND THEN THE BLUE ARMY
	 * @return
	 */
	private Piece getNextPieceToSet(Color color) 
	{
		ArrayList<Piece> all = null;
		if (color.equals(Color.RED))
			all = redArmy;
		else
			all = blueArmy;
		
		for (Piece e : all)
		{
			if (e.getGrid() == null)
				return e;
		}
		
		return null;
	}
	
	private void populateArmy(Army anArmy)
	{
		ArrayList<Piece> army = null;
		Color color = null;
		if (anArmy == Army.BLUE)
		{
			color = Color.BLUE;
			army = blueArmy;
		}
		else if (anArmy == Army.RED)
		{
			color = Color.RED;
			army = redArmy;
		}
		
		army.add(new Flag(color));
		for (int x = 1; x <= 6; x++)
		{
			army.add(new Bomb(color));
		}
		for (int red = 1; red < 2; red++)
		{
			army.add(new Marshal(color));
		}
		army.add(new Piece("2", color));
		for (int x = 1; x <= 2; x++)
		{
			army.add(new Piece("3", color));
		}
		for (int x = 1; x <= 3; x++)
		{
			army.add(new Piece("4", color));
		}
		for (int x = 1; x <= 4; x++)
		{
			army.add(new Piece("5", color));
		}
		for (int x = 1; x <= 4; x++)
		{
			army.add(new Piece("6", color));
		}
		for (int x = 1; x <= 4; x++)
		{
			army.add(new Piece("7", color));
		}
		for (int x = 1; x <= 5; x++)
		{
			army.add(new Piece("8", color));
		}
		army.add(new Spy(color));
		for (int x = 1; x <= 8; x++)
		{
			army.add(new Scout(color));
		}
		
	}
	
	private void placeWater()
	{
		GridPanel gp = gui.getGridPanel();
		gp.setPiece(new Space(2, 4), new Water());
		gp.setPiece(new Space(3, 4), new Water());
		gp.setPiece(new Space(2, 5), new Water());
		gp.setPiece(new Space(3, 5), new Water());
		gp.setPiece(new Space(6, 4), new Water());
		gp.setPiece(new Space(7, 4), new Water());
		gp.setPiece(new Space(6, 5), new Water());
		gp.setPiece(new Space(7, 5), new Water());
	}
	
	public Army getArmy(Color color)
	{
		if (color == Color.RED)
			return Army.RED;
		else if (color == Color.BLUE)
			return Army.BLUE;
		else 
			return null;
	}
	
	public Color getArmyColor(Army anArmy)
	{
		if (anArmy == Army.BLUE)
			return Color.blue;
		else if (anArmy == Army.RED)
			return Color.RED;
		else 
			return null;
	}
	
	/**
	 * Sets the state to PLACEMENT
	 */
	public void promptPiecePlacement() 
	{	
		for (Piece x : blueArmy)
		{
			gui.setTextField("BLUE ARMY:/nClick on a location to place a " + x.getValue() + " piece");
			
		}
		for (Piece x: redArmy)
		{
			gui.setTextField("RED ARMY:/nClick on a location to place a " + x.getValue() + " piece");
			
		}
	}
	
	private void normalizeBorders()
	{
		if (gui.getGridPanel().getActiveButton() != null)
		{
			ArrayList<Space> oldSpaces = gui.getGridPanel().getGrid().getAdjacentSpaces(gui.getGridPanel().getActiveButton().getSpace());
			for (Space e : oldSpaces)
			{
				gui.getGridPanel().getButton(e).normalBorder();
			}
			gui.getGridPanel().getActiveButton().normalBorder();
			
			if (gui.getGridPanel().getActiveButton().getPiece() instanceof Scout)
			{
				oldSpaces = gui.getGridPanel().getActiveButton().getPiece().getMoveSpaces();
				for (Space e : oldSpaces)
				{
					gui.getGridPanel().getButton(e).normalBorder();
				}
			}
		}
	}

	/**
	 * A PBListener is an actionListener, and is the main one used by PieceButtons.  As each click is registered, the PBListener decides what to do based on previous game states and which button was clicked.
	 * Ultimately contains the core code for Game flow.
	 * @author Andrew + Nick
	 *
	 */
	class PBListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			PieceButton clickedButton = (PieceButton) event.getSource();
			Piece currentResident = clickedButton.getPiece();
			
			if (state < 3) //For normalizing previous borders and thickening the selected one.  Only if game is in Placement or Playing.
			{
				normalizeBorders();
				clickedButton.thickenBorder();
				gui.getGridPanel().setActiveButton(clickedButton);
			}
			
			if (state == PLACEMENT) //Deployment of Pieces
			{
				int teamMod = 0;
				if (activeArmy == Army.BLUE)
					teamMod = 6;
				boolean isTeamSpace = clickedButton.getSpace().getRow() >= teamMod && clickedButton.getSpace().getRow() < teamMod + 4; //Check to see if clicked space is valid for team
				String temp = "";
				
				gui.getGridPanel().showArmy(getActiveArmyColor());
				
				Piece next = getNextPieceToSet(getActiveArmyColor());
				if (next != null) //Only place pieces if there are some left.
				{	
					if (  (currentResident == null || currentResident.getColor().equals(getActiveArmyColor()))  &&  previousState == PLACEMENT  &&  isTeamSpace) //placing the piece if it meets requirements
						gui.getGridPanel().setPiece(clickedButton.getSpace(), next);
					
					if (getNextPieceToSet(getActiveArmyColor())!= null ) //Setting the Appropriate Message with this if/else statement
					{
						if (!isTeamSpace && previousState == PLACEMENT)
						{
							clickedButton.redBorder();
							temp = "Invalid Space. ";
						}
						
							gui.setTextField(temp + activeArmy.name() + " ARMY: Please click where to place a " 
									+ getNextPieceToSet(getActiveArmyColor()).getValue() + " Piece!");
							
					}
					else
					{
 						gui.setTextField(activeArmy.name() + " ARMY: Click water when done.");
					}
				}
				else
				{
					state = TRANSITION1;
					previousState = PLACEMENT;
					if (activeArmy == Army.BLUE)
					{
						state = TRANSITION2;
					}
					gui.setTextField("");
				}
				
				previousState = PLACEMENT;	
			}
			
			if (state == PLAYING2) //if attakable/movable, do that. else go to PLAYING1;
			{
				previousState = PLAYING2;
				
				boolean attackable = false, movable = false;
				for (PieceButton a : oldTargets)
				{
					if (a.getSpace().equals(clickedButton.getSpace()))
						attackable = true;
				}
				for (PieceButton m : oldMoves)
				{
					if (m.getSpace().equals(clickedButton.getSpace()))
						movable = true;
				}
				
				if(attackable)
				{
					if (clickedButton.getPiece() instanceof Flag) //END GAME
					{
						end();
						return;
					}
					
					clickedButton.redBorder();
					
					lastAttacker = oldAttacker.getPiece().getValue();
					lastDefender = clickedButton.getPiece().getValue();
					
					Piece winner = oldAttacker.getPiece().attack(clickedButton.getPiece());
					
					gui.getGridPanel().removePiece(clickedButton.getSpace());
					gui.getGridPanel().removePiece(oldAttacker.getSpace());
					
					gui.getGridPanel().setPiece(clickedButton.getSpace(), winner);
					
					oldMoves.clear();
					oldTargets.clear();
					
					lastAction = ATTACK;
					state = TRANSITION2;
				}
				else if (movable)
				{
					clickedButton.greenBorder();
					Piece oldPiece = gui.getGridPanel().removePiece(oldAttacker.getSpace());
					gui.getGridPanel().setPiece(clickedButton.getSpace(), oldPiece);
					
					oldMoves.clear();
					oldTargets.clear();
					
					lastAction = MOVE;
					state = TRANSITION2;
				}
				else
				{
					state = PLAYING1;
					previousState = PLAYING1;
				}
			}
			
			if (state == PLAYING1) //HIGHLIGHT Appropriate squares for attacking/moving
			{
				gui.setTextField(activeArmy.name() + " ARMY: Click a piece to select it.\nClick again to move (Green), attack (RED), or select another piece.");
				gui.getGridPanel().hideAll();
				gui.getGridPanel().showArmy(getActiveArmyColor());
				
			
				if (currentResident != null && currentResident.getColor().equals(getArmyColor(activeArmy)) && !(currentResident instanceof Water) && previousState == PLAYING1)
				{
					oldTargets.clear();
					oldMoves.clear();
					
					oldAttacker = clickedButton; //OLD ATTACKER TO BE USED IN playing2
					ArrayList<Space> attackableLocs = gui.getGridPanel().getGrid().getAttackable(clickedButton.getSpace());
					for (Space targetLoc : attackableLocs)
					{
						PieceButton target = gui.getGridPanel().getButton(targetLoc);
						target.redBorder();
						oldTargets.add(target);
					}
					ArrayList<Space> moveLocs = currentResident.getMoveSpaces();
					for (Space moveLoc : moveLocs)
					{
						PieceButton move = gui.getGridPanel().getButton(moveLoc);
						move.greenBorder();
						oldMoves.add(move);
					}					
				}
				state = PLAYING2;
				previousState = PLAYING1;
			}
			
			if (state == TRANSITION1)//Transition Between placement turns
			{
				toggleArmies();
				gui.setTextField("RED ARMY: Placement completed.  Please turn away for the BLUE player!\nBLUE ARMY: Click the screen when you have privacy!");
				state = PLACEMENT;
				previousState = TRANSITION1;
			}
			
			if (state == TRANSITION2) //Transition Between playing turns
			{
				toggleArmies();
				String action = "";
				switch (lastAction)
				{
				case 1: action = "MOVE completed. ";
						oldAttacker.greenBorder();
						clickedButton.greenBorder();
					break;
				case 2: action = otherArmy.name() + " " + lastAttacker + " attacked " + activeArmy.name() + " " + lastDefender + ". ";
						oldAttacker.redBorder();
						clickedButton.redBorder();
						oldAttacker.flipPiece();
						clickedButton.flipPiece();
					break;
				}
				gui.setTextField(action + otherArmy.name() + " ARMY: Please turn away for the "+ activeArmy.name() + " Army.\n" + activeArmy.name() + " ARMY: Click the screen when you have privacy!");
				state = PLAYING1;
				previousState = TRANSITION2;
				oldAttacker = null;
			}
			
		}
	}
	
	private static final int MOVE = 1;
	private static final int ATTACK = 2;
	private static int lastAction = 0;
	
	private static String lastAttacker = null;
	private static String lastDefender = null;
	
	private static PieceButton oldAttacker = null;
	private static ArrayList<PieceButton> oldTargets = new ArrayList<PieceButton>();
	private static ArrayList<PieceButton> oldMoves = new ArrayList<PieceButton>();
}
	
