package calculations;

import java.awt.Point;
import java.util.ArrayList;

import world.WorldMap;
import world.groundObject;

public class ExplosionCalculations {

	ArrayList<Point> removeList;
	ArrayList<Integer> removeWithIndex; //, removeWithIndexTopLevel;
	
	WorldMap map;
	
	public ExplosionCalculations(WorldMap map)
	{
		removeWithIndex = new ArrayList<Integer>();
	//	removeWithIndexTopLevel = new ArrayList<Integer>();
		removeList = new ArrayList<Point>();
		this.map = map;
	}
	// TO DO
	// if level doesnt have top level flaw ++
	// set new tops ++
	// run this on seperate thread
	
	// bugs
	// last chunk is untouchible
	//floating untouchible block bug
	// NOT becouse distance - fromObj +
	// Happens only when chunk bottom is reached on the right side and then left side is destroyed 
	
	
	
	//FLOATING blocks when bottom is reached
	
	public WorldMap roundExplosion ( int x, int y, int radius)
	{
		removeWithIndex.clear();
		removeList.clear();
		
		Point p;
		
		radius *= map.getBlockSize();
		int startX, startY;
		int xdis, ydis;
		
		startX = formalize(x);
		startY = formalize(y);
		
		for (int cx=radius; cx >= 0; cx-=map.getBlockSize())
		{
			ydis = (int)Math.sqrt(radius*radius-cx*cx);
			ydis = formalize(ydis);
			
			for (int cy = startY - ydis; cy<= startY + ydis; cy+=map.getBlockSize())
			{
				if (startX+cx >0 && cy>0)
				{
					p = new Point();
					p.x = startX-cx;
					p.y = cy;
				
					removeList.add(p);
				}
			}
		}
		
		for (int cx=map.getBlockSize(); cx <= radius; cx+=map.getBlockSize())
		{
			ydis = (int)Math.sqrt(radius*radius-cx*cx);
			ydis = formalize(ydis);
			
			for (int cy = startY - ydis; cy<= startY + ydis; cy+=map.getBlockSize())
			{
				
				if (startX+cx >0 && cy>0)
				{
					p = new Point();
					p.x = startX+cx;
					p.y = cy;
					
					removeList.add(p);
				}
				
				
			}
		}
		
	map = removeObjects( 2*radius/map.getBlockSize());
		
		return map;
	}
	
	
	private WorldMap removeObjects(int distance)
	{
		int fromObj = 0;
		//for finding fromObj, wee go through every ground object, and if we find first that maches one in remove list
		// (x coordinate) we mark that coordinate as starting point
		
		// CAN BE OPTIMIZED, yet is not becouse of universal use
		
		for (int x=0; x<map.getTopGroundObjects().size(); x++)
		{
			
			if (map.getGroundObjects().size() > map.getTopGroundObjects().get(x) && removeList.size()>0)
			{
				if (map.getTopGroundObjects().get(x) !=-1)
				{
					if (map.getGroundObjects().get(map.getTopGroundObjects().get(x)).x == removeList.get(0).x)
					{
						fromObj = x;
					}
					
				}
				else
					fromObj = removeList.get(0).x/map.getBlockSize();
			}
		}
		
		if (fromObj < 0) fromObj = 0;
		
		
		for (int x=0; x<distance; x++)
		{
			if (map.getTopGroundObjects().size() > fromObj + x)
			{
				if (fromObj+x+2 > map.getTopGroundObjects().size()) break;

				for (int y = map.getTopGroundObjects().get(fromObj+x); y < map.getTopGroundObjects().get(fromObj+x+1); y++ )
				{
			
					if (removeList.size() < 1 || map.getGroundObjects().size() < y+1) break;
					if (y<0) break;
					
					if (removeList.get(0).x == map.getGroundObjects().get(y).x)
					{
						 // found block that matches
						 if (map.getGroundObjects().get(y).y == removeList.get(0).y)
						{
							removeWithIndex.add(y);
							removeList.remove(0);
						}
						// all blocks are above removeList elements
						 else if(map.getGroundObjects().get(y).y > removeList.get(0).y)
						{
							removeList.remove(0);
							y--;
						}
						 
					}
				// there are no blocks to be removed on that chunk
					 else if (map.getGroundObjects().get(y).x > removeList.get(0).x)
					 {
						 if ( removeList.size()>0)
						 {
							 removeList.remove(0);
							 y--;
						 }
					 }
					else
					{
						break;
					}
				}
			}
		}
		
		
		for (int x=0; x<removeWithIndex.size(); x++)
		{
			map.getGroundObjects().get(removeWithIndex.get(x)).y = -1;
		}
		
		//tikrinu nuo esamo elemento elementus is getground objects, su tokiu paciu x, ir uzstatau su didziausiu y
		// i lista ideta: pvz.: 2 eile 5 eile;
		
		//patikrinu kiek reikes keist, tada einu nuo virsaus i apacia paziuriu ar koks elementas auksciausias ir jei jis nebus
		// trinamas ji nustatau toponiu
		/* FLAWEED
		for (int x=0; x<removeWithIndexTopLevel.size(); x++)
		{
			for (int y = map.getTopGroundObjects().get(removeWithIndexTopLevel.get(x)) ;
					y < map.getTopGroundObjects().get(removeWithIndexTopLevel.get(x)+1); y++)
			{
				if(map.getGroundObjects().get(y).y != -1)
				{
					map.getTopGroundObjects().set(removeWithIndexTopLevel.get(x), y);
					break;
				}
				else if ( y+1 == map.getTopGroundObjects().get(removeWithIndexTopLevel.get(x)+1))
				{
					map.getTopGroundObjects().set(removeWithIndexTopLevel.get(x), -1);
				}
			}
		}
		*/
		
		// FIRST IDEA
		// trying to move faster though out all members program jumps 20/blockSize steps
		// if x coordinate changes during jump it will do 10/blockSize steps back
		// if it changes again 5 if again 2 and again does 1
		
		// SECOND IDEA
		// When deleting members, check if object i delete and object behind has same x
		// if yes keep the top layer ( on that x)
		// if no check if element one forward is not to be deleted, then set it as top layer
		
		
		
		map.getHangingObjects().clear();
		int safeX = -1;
		int p =0, dist = 0;
		//eliminate MARKED elements, from the list
		for (int x = map.getTopGroundObjects().get(fromObj); x < map.getGroundObjects().size(); x++)
		{
			if (x < 0) x = 0; 
			
			if (map.getGroundObjects().get(x).y == -1 )
					//&& map.getGroundObjects().get(x-1).x + map.getBlockSize() == map.getGroundObjects().get(x).x )
			{			
				// to tell if there is a block above
				if (x!=0 && map.getGroundObjects().get(x-1).x == map.getGroundObjects().get(x).x 
						 && map.getGroundObjects().get(x-1).y != -1)
				{
					safeX = map.getGroundObjects().get(x).x;
					map.getHangingObjects().add(x-1);			
				}		
				
				// to tell if there is any block below ( if its not the end of chunk)
				if (map.getGroundObjects().get(x).x != safeX 
						&& map.getGroundObjects().get(x).x != map.getGroundObjects().get(x+1).x)
				{
					map.getTopGroundObjects().set(map.getGroundObjects().get(x).x/map.getBlockSize(), -1);	
				}
				
				map.getGroundObjects().remove(x);
				x--;
			}
			
			//if there is no block above, this block is highest
			else if ( map.getGroundObjects().get(x).x != safeX) 
				//  && map.getGroundObjects().get(x-1).x + map.getBlockSize() == map.getGroundObjects().get(x).x)
			{
				 safeX = map.getGroundObjects().get(x).x ;
				
				//System.out.println(safeX);
				// need to find in which layer this block is
				// can just devide coordinate by blockSize (NOT UNIVERSAL)
				//when all layer is delted facing problems !------------------------!
				
				map.getTopGroundObjects().set(safeX/map.getBlockSize(), x);
			}
			
		}
		
		return map;
		
	}
	
	private int formalize(int x)
	{
		return (x/map.getBlockSize())*map.getBlockSize();
	}
}
