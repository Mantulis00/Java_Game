package calculations;

import world.WorldMap;

public class Gravity {

	
	
	// tweek in else statment instead of 600 get screen height
	// idk now it refreshes every layer every frame, maybe repeat this until hanglist is empty ?
	// could repeat this until is empty, ez;
	
	public WorldMap mapGravity (WorldMap map)
	{
		for (int x=0; x<map.getHangingObjects().size(); x++)
		{
			// if there is block below hanging object
			if (map.getGroundObjects().get(map.getHangingObjects().get(x)).x
					== map.getGroundObjects().get(map.getHangingObjects().get(x)+1).x)
			{
				map.getGroundObjects().get(map.getHangingObjects().get(x)).y = 
						map.getGroundObjects().get(map.getHangingObjects().get(x)+1).y - map.getBlockSize(); 
				
				if (map.getHangingObjects().get(x) != 0 && 
						map.getGroundObjects().get(map.getHangingObjects().get(x)).x
						== map.getGroundObjects().get(map.getHangingObjects().get(x)-1).x)
				{
					map.getHangingObjects().set(x, map.getHangingObjects().get(x)-1);
				}
				else
					map.getHangingObjects().remove(x);
				
			}
			else
			{
				map.getGroundObjects().get(map.getHangingObjects().get(x)).y = 600-map.getBlockSize();//map.getHeight()-map.getBlockSize();
						
				if (map.getHangingObjects().get(x) != 0 && 
						map.getGroundObjects().get(map.getHangingObjects().get(x)).x
						== map.getGroundObjects().get(map.getHangingObjects().get(x)-1).x)
				{
					map.getHangingObjects().set(x, map.getHangingObjects().get(x)-1);
				}
				else
					map.getHangingObjects().remove(x);
			}
		}
		
		
		
		return map;
		
	}
	
}
