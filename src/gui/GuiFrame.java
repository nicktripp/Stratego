package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class GuiFrame extends JFrame
{
	public GuiFrame(ActionListener listener)
	{
		super("~~~ Stratego ~~~");
		this.setSize(820, 900);
		this.setMinimumSize(getSize());
		
		createTextField();
		createGridPanel(listener);
		createLabel();
		createMainPanel();	
	}
	
	private void createGridPanel(ActionListener listener)
	{
		grid = new GridPanel(listener);
	}
	
	public void setTextField(String aString)
	{
		field.setText(aString);
	}
	
	public void createLabel()
	{
		label = new JLabel("\u00A9 AndrewLangman NickTripp 2013");
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
	}
	
	private void createTextField()
	{
		field = new JTextArea();
		field.setBackground(new Color(255, 255, 200));
		field.setEditable(false);
		field.setText("Welcome!  BLUE ARMY: Please turn away for the RED ARMY!\n RED ARMY: Click when you have privacy!");
		field.setSize(800, 40);
		field.setPreferredSize(new Dimension(800, 40));
		field.setMaximumSize(new Dimension(800, 40));
		field.setWrapStyleWord(true);
		field.setAlignmentX(Component.CENTER_ALIGNMENT);
		field.setLineWrap(true);
		field.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
	}
	
	private void createMainPanel()
	{
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		
		mainPanel.add(Box.createRigidArea(new Dimension(800, 5)));
		mainPanel.add(field);
		mainPanel.add(Box.createRigidArea(new Dimension(800, 5)));
		mainPanel.add(grid);
		mainPanel.add(label);
		
		add(mainPanel);
	}
	
	public GridPanel getGridPanel() 
	{
		return grid;
	}

	public JTextArea getField() 
	{
		return field;
	}

	public JFrame getPlayerFrame() 
	{
		return playerFrame;
	}

	private JLabel label;
	private GridPanel grid;
	private JTextArea field;
	private JFrame playerFrame;
	
}
