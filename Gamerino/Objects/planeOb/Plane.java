package planeOb;

import java.awt.Point;
import java.awt.image.BufferedImage;

public class Plane implements PlaneBP {

	private Point location, speeds;
	private int v;
	private BufferedImage img;
    private int vy, vx;
	
	Plane()
	{
		location = new Point();
		speeds = new Point();
	}
	
	
	public Point getLocation()
	{
		return location;
	}

	public void setLocation(Point location)
	{
		this.location = location;
	}
	
	public int getV() {
		return v;
	}
	
	public void setV (int v)
	{
		this.v = v;
	}

	public BufferedImage getImg() {
		return img;
	}

	
	public void setImg(BufferedImage img) {
		this.img = img;
		
	}


	// type of movement, plane specific
	public void followPoint(Point pointLoc)
	{
		int xys;
		
		xys = (int)Math.sqrt(Math.pow((pointLoc.x - location.x), 2) + Math.pow((pointLoc.y - location.y), 2));
		
	//	if (plane.getX()+plane.getV()*()>width || plane.getX()+plane.getV()<0)
		//{
		//	plane.setV(plane.getV() * -1);
		//}
		if (xys > 20)
		{
			if ((pointLoc.x-location.x)/xys>0 && (pointLoc.x-location.x)/xys<1) vx++;
			vx = v * (pointLoc.x-location.x)/xys;
			if ((pointLoc.y-location.y)/xys>0 && (pointLoc.y-location.y)/xys<1) vy++;
			vy = v * (pointLoc.y-location.y)/xys;
		}
		else
		{
			//vy = 0;
		//	vx = 0;
		}
		
		// if (pointLoc.y>400)
		//{
	//		vy = -1*vy;
	//	}
		
		location.x += vx;
		location.y += vy;
		
		
		
	}

	public Point getSpeeds()
	{
		
		speeds.x = vx;
		speeds.y = vy;
		
		return speeds;
	}
	
	
	
	
}
