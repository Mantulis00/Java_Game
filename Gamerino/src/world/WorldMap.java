package world;

import java.awt.Point;
import java.util.ArrayList;


// groundObjects <- all ground object, has x, y (Point) components
// topGroundObjects <- Holds indexes of top layer objects

public class WorldMap {

	private ArrayList<Point> groundObjects;
	private ArrayList<Integer> topGroundObjects;
	private int blockSize = 1;
	private Point blockLocation;
	private ArrayList<Integer> hangingObjects;
	private int height, lenght;
	
	
	public WorldMap()
	{
		groundObjects = new ArrayList<Point>();
		topGroundObjects = new ArrayList<Integer>();
		hangingObjects = new ArrayList<Integer>();
	}
	
	
	public void generateDesert(int width, int height, int groundHeight)
	{
		groundObjects.clear();
		topGroundObjects.clear();
		
		this.height = height;
		this.lenght = width;
		
		for (int x = 0; x < width; x+=blockSize)
		{
			topGroundObjects.add(groundObjects.size());
			
			for (int y = height; y < groundHeight; y+=blockSize)
			{
				blockLocation = new Point();
				blockLocation.x = x;
				blockLocation.y = y;
				
				groundObjects.add(blockLocation);
			}
		}
	}
	
	public int getLenght()
	{
		return lenght;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	
	public int getBlockSize()
	{
		return blockSize;
	}
	
	public void setBlockSize(int size)
	{
		this.blockSize =  size;
	}
	
	

	public ArrayList<Point> getGroundObjects()
	{
		return groundObjects;
	}


	public ArrayList<Integer> getTopGroundObjects() 
	{
		return topGroundObjects;
	}

	public ArrayList<Integer> getHangingObjects()
	{
		return hangingObjects;
	}
	
	
}

