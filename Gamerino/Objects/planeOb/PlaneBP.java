package planeOb;

import java.awt.Point;
import java.awt.image.BufferedImage;

public interface PlaneBP {

	public Point getLocation();
	public void setLocation(Point location);
	
	public int getV();
	public void setV(int v);
	
	public BufferedImage getImg();
	public void setImg(BufferedImage img);
	
	public void followPoint(Point pointLoc);
	
	
}
