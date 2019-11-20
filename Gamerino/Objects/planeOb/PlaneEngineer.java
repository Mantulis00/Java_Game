package planeOb;

import planeOb.Plane;
import planeOb.PlaneBuilder;

import java.awt.Point;
import java.awt.image.BufferedImage;

public class PlaneEngineer {

	private PlaneBuilder builder;
	private int  v;
	private Point location;
	private BufferedImage img;
	
	
	public PlaneEngineer(PlaneBuilder planeBuilder, Point location, int v, BufferedImage img)
	{
		this.location = location;
		this.v = v;
		this.img = img;
		
		this.builder = planeBuilder;
	}
	
	public Plane getPlane()
	{
		return this.builder.getPlane();
	}
	
	public void makePlane()
	{
		this.builder.buildLocation(location);
		this.builder.buildPlaneV(v);
		this.builder.buildPlaneImg(img);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
