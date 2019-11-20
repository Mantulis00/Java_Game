package planeOb;

import java.awt.Point;
import java.awt.image.BufferedImage;
import planeOb.Plane;


public class SimplePlaneBuilder implements PlaneBuilder {
	private Plane plane;
	
	public SimplePlaneBuilder()
	{
		this.plane = new Plane();
	}

	public void buildLocation(Point location) {
		plane.setLocation(location);
		
	}


	public void buildPlaneV(int v) {
		plane.setV(v);
		
	}

	public void buildPlaneImg(BufferedImage img) {
		plane.setImg ( img);
		
	}


	
	public Plane getPlane()
	{
		return this.plane;
	}

	
	
	
	
}