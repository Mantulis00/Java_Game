package listeners;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseControl implements MouseMotionListener{

	
	private Point location;
	
	public MouseControl()
	{
		location = new Point();
	}
	
	
	public Point getLocation()
	{
		return location;
	}
	
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	public void mouseMoved(MouseEvent e) {
		location.x = e.getX();
		location.y = e.getY();
	}

}
