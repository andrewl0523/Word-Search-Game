import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.io.*;
public class Grid extends JPanel
{
/**
* String DICTIONARY_FILE is the dictionary
*/
	public static final String DICTIONARY_FILE = "dictionary.txt";
/**
* ArrayList<String> dictionary is the ArrayList in which the dictionary will be held
*/
	public static ArrayList<String> dictionary = new ArrayList<String>();
/**
* int width is the width of the grid
*/
	private int width;
/**
* int height is the height of the grid
*/
	private int height;
/**
* constructor, initializes variables
* @param gWidth is the width of the grid
* @param gHeight is the height of the grid
*/
	public Grid(int gWidth, int gHeight)
	{
		width = gWidth;
		height = gHeight;
	}
/**
* ArrayList<Character> selectLetters() chooses the letters to be used
* @return ArrayList<Character> with the letters to be used
*/
	public ArrayList<Character> selectLetters()
	{
		ArrayList<Character> selected = new ArrayList<Character>();
		//weights the characters by common-ness
		char[] alphabet = {'A','A','A','A','A','A','A','A','B','B','C','C','D','D','D','D','E','E','E','E','E','E','E','E','E','E','F','F','G','G','G','H','H','I','I','I','I','I','I','I','I','J','K','L','L','L','L','M','M','N','N','N','N','N','N','O','O','O','O','O','O','O','O','P','P','Q','R','R','R','R','R','R','S','S','S','S','S','S','S','T','T','T','T','T','T','U','U','U','U','V','V','W','W','X','Y','Y','Z'};
		Random r = new Random();
		for(int ii = 0; ii < width * height; ii++)
		{
			selected.add(alphabet[r.nextInt(alphabet.length)]);//chooses random characters
		}
		return selected;
	}
/**
* Tile[] createTiles() creates an array of tiles to be used in the grid
* @return Tile[] is the array of tiles to be used in the grid
*/
	public Tile[] createTiles()
	{
		Tile[] tiles = new Tile[16];//16 tiles in a grid
		ArrayList<Character> select = selectLetters();
		Collections.shuffle(select);//mixes up the order
		for(int jj = 0; jj < 16; jj++)
		{
			tiles[jj] = new Tile(jj, select.get(0));
			select.remove(0);
		}
		return tiles;
	}
/**
* loadDictionary() loads the dictionary
*/
	public void loadDictionary()
	{
		//checks for a FileNotFoundException
		try
		{
			Scanner input = new Scanner(new File(DICTIONARY_FILE));
			while (input.hasNext())
			{
				dictionary.add(input.next().toUpperCase());//moves everything to upper case to match the tiles
			}
		}
		catch(FileNotFoundException e)
		{
		}
	}

}