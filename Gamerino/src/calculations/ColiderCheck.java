package calculations;

import bombOb.Bomb;
import planeOb.Plane;
import world.WorldMap;

///Main task is to check if object is touching another object or not
/// give true or false value OR give String or int to distinguish what object is touching


// could connect all objects to interface, that means all objects would have neccesery vars like
// coordinates and could use determined methode for all objects w/o overloading functions


// Have to check only top layer objects if v(x) == 0, or all layers below top layer if target object is below top layer
// 


public class ColiderCheck {

	// find which top layers is closest to object
	int thresh;
	WorldMap map;
	
	public ColiderCheck(WorldMap map)
	{
		thresh = 1;
		this.map = map;	
	}
	
	
	public boolean isTouchingGround(Bomb bomb)
	{
		
		if (bomb.getLocation().x < 0) return false;
		
		
		int layerN = findLayer(formalize(bomb.getLocation().x));
		
		if (map.getTopGroundObjects().size()>layerN+2)
		{
				
				if (map.getTopGroundObjects().size() > layerN+1 && layerN >= 0)
				{
				for (int x=map.getTopGroundObjects().get(layerN); x<map.getTopGroundObjects().get(layerN+1); x++)
				{
					if (map.getGroundObjects().get(x).y > bomb.getLocation().y) break;
				
					// if gravity is on ONLY
					if (map.getGroundObjects().get(x).y-map.getBlockSize() <= bomb.getLocation().y * thresh)		
					{
						return true;
					}
					else if (bomb.getV().y <= 0)
					{
						break;
					}
			
					if (x+1 == map.getTopGroundObjects().get(layerN+1))
						return false;
				}
			}
		}		
		return false;
	}
	
	
	public boolean isTouchingGround(Plane plane)
	{
		return false;
	}
	
	
	
	//needs to be fixed
	/// crashes
	
	// bad top layer asigns
	
	
	private int findLayer(int x)
	{
		for (int z=0; z<map.getTopGroundObjects().size(); z++)
		{
			if (map.getTopGroundObjects().get(z) != -1 && map.getTopGroundObjects().get(z) < map.getGroundObjects().size())
			{
				if(map.getGroundObjects().get(map.getTopGroundObjects().get(z)).x == x)
				{
					return z;
				}
			}
		}
		return -1;
	}
	
	private int formalize(int x)
	{
		return (x/map.getBlockSize())*map.getBlockSize();
	}
	
	
}
