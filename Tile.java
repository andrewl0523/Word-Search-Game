import java.util.*;
import java.io.*;
import java.awt.event.*;
public class Tile implements MouseListener, MouseMotionListener
{
/**
* List<String> alreadyDone keeps track of the words already made
*/
	private List<String> alreadyDone = new ArrayList<String>();
/**
* int previousScore is the last recorded score
*/
	private int previousScore = -1;
/**
* String word is the character to be added on to the displayWord
*/
	private String word = "";
/**
* char wordChar is the character to be added on
*/
	private char wordChar = '.';
/**
* int mouseX is the x coordinate of the mouse
*/
	private int mouseX = -1;
/**
* mouseY is the y coordinate of the mouse
*/
	private int mouseY = -1;
/**
* boolean pressed is whether or not the mouse is pressed
*/
	public boolean pressed = false;
/**
* int tileNumber is the # of the tile of a tile
*/
	private int tileNumber;
/**
* char tileCharacter is a tile's character
*/
	private char tileCharacter;
/**
* constructor which initializes variables
* @param int tileNum is the tile number
* @param tileChar is the tile character
*/
	public Tile(int tileNum, char tileChar)
	{
		tileNumber = tileNum;
		tileCharacter = tileChar;
	}
/**
* int getXBegin()gets the X coordinate a tile begins with
* @return int is the X coordinate a tile begins with
*/
	public int getXBegin()
	{
		return (tileNumber % 4) * 100 + 50;
	}
/**
* int getXBegin()gets the X coordinate a tile ends with
* @return int is the X coordinate a tile ends with
*/
	public int getXEnd()
	{
		return (tileNumber % 4 + 1) * 100 + 40;
	}
/**
* int getXBegin()gets the Y coordinate a tile begins with
* @return int is the Y coordinate a tile begins with
*/
	public int getYBegin()
	{
		return (tileNumber / 4) * 100 + 50;
	}
/**
* int getXBegin()gets the Y coordinate a tile ends with
* @return int is the Y coordinate a tile ends with
*/
	public int getYEnd()
	{
		return (tileNumber / 4 + 1) * 100 + 40;
	}
/**
* char getChar() gets the character of a tile
* @return char is the character of a tile
*/
	public char getChar()
	{
		return tileCharacter;
	}
/**
* int getNum() gets the tileNumber
* @return in is the tile number
*/
	public int getNum()
	{
		return tileNumber;
	}
/**
* int getValue() gets the value of each tile
* @return int is the value of each tile
*/
	public int getValue()
	{
		int value = 0;
		//determines the value of each character
		if(tileCharacter == 'A' || tileCharacter == 'E' || tileCharacter == 'R' || tileCharacter == 'T' || tileCharacter == 'S')
		{
			value = 1;
		}
		else if(tileCharacter == 'D' || tileCharacter == 'I' || tileCharacter == 'L' || tileCharacter == 'N' || tileCharacter == 'O')
		{
			value = 2;
		}
		else if(tileCharacter == 'G' || tileCharacter == 'H')
		{
			value = 3;
		}
		else if(tileCharacter == 'B' || tileCharacter == 'C' || tileCharacter == 'F' || tileCharacter == 'M' || tileCharacter == 'P' || tileCharacter == 'U')
		{
			value = 4;
		}
		else if(tileCharacter == 'K')
		{
			value = 5;
		}
		else if(tileCharacter == 'W' || tileCharacter == 'V' || tileCharacter == 'Y')
		{
			value = 6;
		}
		else if(tileCharacter == 'J' || tileCharacter == 'X' || tileCharacter == 'Z')
		{
			value = 8;
		}
		else if(tileCharacter == 'Q')
		{
			value = 10;
		}
		return value;
	}
/**
* int getMouseX() gets the X coordinate of the mouse
* @return int is the X coordinate of the mouse
*/
	public int getMouseX()
	{
		return mouseX;
	}
/**
* int getMouseX() gets the Y coordinate of the mouse
* @return int is the Y coordinate of the mouse
*/
	public int getMouseY()
	{
		return mouseY;
	}
/**
* mousePressed is a MouseListener method which acts when the mouse is pressed
* @param MouseEvent evt is the event when this method is called
*/
	public void mousePressed(MouseEvent evt)
	{
		previousScore = WordGame.currentScore;
		mouseX = evt.getX();
		mouseY = evt.getY();
		pressed = true;
		wordChar = getChar();
		word += wordChar;
	}
/**
* mouseReleased is a MouseListener method which acts when the mouse is released
* @param MouseEvent evt is the event when this method is called
*/
	public void mouseReleased(MouseEvent evt)
	{
		int currentScore = WordGame.currentScore;
		for(String s : Grid.dictionary)//check word in the dictionary
		{
			if(s.equals(WordGame.displayWord) && !alreadyDone.contains(WordGame.displayWord))//don't add score if the word has already been done
			{
				WordGame.currentScore += WordGame.wordScore;
				alreadyDone.add(WordGame.displayWord);
			}
		}
 		if(previousScore == WordGame.currentScore)//determines whether or not to end the game
 		{
 			WordGame.endGame = true;
 		}
		WordGame.displayWord = "";
		
		WordGame.wordScore = 0;//sets the wordScore to 0 after the mouse is released
		pressed = false;//sets pressed to false when the mouse is released
	}
/**
* mouseClicked is a MouseListener method which acts when the mouse is clicked
* @param MouseEvent evt is the event when this method is called
*/
	public void mouseClicked(MouseEvent evt){}
/**
* mouseEntered is a MouseListener method which acts when the mouse enters
* @param MouseEvent evt is the event when this method is called
*/
	public void mouseEntered(MouseEvent evt){}
/**
* mouseExited is a MouseListener method which acts when the mouse exits
* @param MouseEvent evt is the event when this method is called
*/
	public void mouseExited(MouseEvent evt){}	
/**
* mouseDragged is a MouseMotionListener method which acts when the mouse is dragged
* @param MouseEvent evt is the event when this method is called
*/
	public void mouseDragged(MouseEvent evt)
	{	
		mouseX = evt.getX();//gets coordinates
		mouseY = evt.getY();
	}
/**
* mouseMoved is a MouseMotionListener method which acts when the mouse is moved
* @param MouseEvent evt is the event when this method is called
*/
	public void mouseMoved(MouseEvent evt){}
/**
* String getWord() gets the character to be added
* @return String is the character to be added
*/
	public String getWord()
	{
		return word;
	}
}