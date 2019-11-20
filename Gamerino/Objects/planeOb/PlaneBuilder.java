package planeOb;

import java.awt.Point;
import java.awt.image.BufferedImage;

public interface PlaneBuilder {

	public void buildLocation(Point location);
	
	public void buildPlaneV(int v);
	
	public void buildPlaneImg(BufferedImage img);
	
	public Plane getPlane();
	
	
}
