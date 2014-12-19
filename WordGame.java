import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class WordGame
{
/**
* boolean endGame determines whether or not the game is to be ended
*/
	public static boolean endGame = false;
/**
* Grid grid is the grid being used to hold the tiles
*/
	public static Grid grid = new Grid(600,600);
/**
* String displayWord is the word to be displayed as each word is made
*/
	public static String displayWord = "";
/**
*	int wordScore keeps track of the score of each individual word
*/
	public static int wordScore = 0;
/**
* int currentScore keeps track of the cumulative score
*/
	public static int currentScore = 0;
/**
* int TIME keeps track of the time
*/
	public static int TIME = -1;
/**
*	int previousScore keeps tack of the last recorded score
*/
	private static int previousScore = -1;
/**
* int previousTileNum keeps track of the last tile highlighted
*/
	private static int previousTileNum = -2;
/**
* Tile[] tiles is the array of tiles to be used
*/
	public static Tile[] tiles;
/**
* boolean highlight determines whether or not a certain tile is to be highlighted
*/
	public static boolean highlight = false;
	private static class WordGameDisplay extends JPanel
	{
/**
* paintComponent overwrites the System's version of the method
* @param Graphics g is the Graphics object used
*/
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			if(endGame)//if the game is over
			{
				Font endGameFont = new Font("Arial", Font.BOLD, 32);
				g.setFont(endGameFont);
				g.drawString("Game Over", 300, 300);
				g.drawString("Score :" + currentScore, 300, 400);
			}
			else
			{
				Font letterFont = new Font( "Arial", Font.BOLD, 48);
				Font valueFont = new Font("Arial", Font.BOLD, 12);
				Font displayWordFont = new Font("Arial", Font.BOLD, 16);
				g.setFont(displayWordFont);
				g.drawString("Score: " + currentScore, 250, 20);//prints current score
				int currentTileNum = -1;
				int counter = -1;
				for(Tile t: tiles)//printing all the tiles
				{
					//this is for if the tile is clicked (highlight it)
	 				if(t.pressed && t.getMouseX() > t.getXBegin() && t.getMouseX() < t.getXEnd() && t.getMouseY() > t.getYBegin() && t.getMouseY() < t.getYEnd())
	 				{
	 					g.setColor(Color.WHITE);
	 					g.fillRect(t.getXBegin(),t.getYBegin(),92,92);
	 					g.setColor(Color.BLACK);
	 					g.drawRect(t.getXBegin(),t.getYBegin(),92,92);
	 					g.setFont(letterFont);
	 					g.drawString(t.getChar() + "", t.getXBegin() + 33, t.getYBegin() + 62);
	 					g.setFont(valueFont);
	 					g.drawString(t.getValue() + "", t.getXBegin() + 8, t.getYBegin() + 12);
	
						if(counter < 0)//makes sure only one tile is highlighted
						{
							currentTileNum = t.getNum();
							if(previousTileNum != currentTileNum)//makes sure the highlight only changes when the cursor moves to a different tile
							{
								displayWord += t.getChar();
								wordScore += t.getValue();
								previousTileNum = currentTileNum;
							}
							counter++;
						}	
						g.setFont(displayWordFont);
						g.drawString(displayWord, 30, 20);	
	 				}
					else//if tile is not pressed or dragged on/over
					{
						g.setColor(Color.ORANGE);
						g.fillRect(t.getXBegin(),t.getYBegin(),92,92);
						g.setColor(Color.BLACK);
						g.drawRect(t.getXBegin(),t.getYBegin(),92,92);
						g.setFont(letterFont);
						g.drawString(t.getChar() + "", t.getXBegin() + 33, t.getYBegin() + 62);
						g.setFont(valueFont);
						g.drawString(t.getValue() + "", t.getXBegin() + 8, t.getYBegin() + 12);	
					}
				}
			}
		}
	}
   private static class ButtonHandler implements ActionListener
	{
/**
* actionPerformed keeps track of if the button is pressed
* @param ActionEvent e is the event which happened
*/
      public void actionPerformed(ActionEvent e)
		{
         System.exit(0);
      }
   }
/**
* main method which paints and repaints the tiles
*/
   public static void main(String[] args)
	{
		tiles = grid.createTiles();//creates tiles
		grid.loadDictionary();//loads dictionary
      
      WordGameDisplay displayPanel = new WordGameDisplay();//creates the display
      JButton exitButton = new JButton("EXIT");
      ButtonHandler listener = new ButtonHandler();
      exitButton.addActionListener(listener);
      JPanel content = new JPanel();//creates the content
      content.setLayout(new BorderLayout());
      content.add(displayPanel, BorderLayout.CENTER);
      content.add(exitButton, BorderLayout.SOUTH);
		
      JFrame window = new JFrame("Word Game");//creates the window
      window.setContentPane(content);
      window.setSize(525,550);
      window.setLocation(100,100);
      window.setVisible(true);
	
		for(Tile t: tiles)//adds listeners to all of the tiles
		{
			Tile mouseListen = t;
			content.addMouseListener(mouseListen);
			Tile mouseMotionListen = t;
			content.addMouseMotionListener(mouseMotionListen);
		}
		

		while(TIME != 0)//continues repainting
		{
			content.repaint();
		}
   }
}