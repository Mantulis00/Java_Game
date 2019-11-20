package bombOb;

import java.awt.Point;

public class Bomb {

	private Point location, v;
	private BombType type;
    private boolean status;
	
	public Bomb()
	{
		location = new Point();
		v = new Point();
		type = new SimpleBomb();
	}

	public Point getLocation() 
	{
		return location;
	}

	public void setLocation(Point location)
	{
		this.location.x = location.x;
		this.location.y = location.y;
	}

	public Point getV()
	{
		return v;
	}

	public void setV(Point v)
	{
		this.v.x = v.x;
		this.v.y = v.y + 1;
	}

	public BombType getType() 
	{
		return type;
	}
	
	public void setBombType(boolean heavy)
	{
		if (heavy)
			type = new HeavyBomb();
		else
			type = new SimpleBomb();
	}
	
	public boolean getStatus()
	{
		return status;
	}
	
	public void setStatus(boolean status)
	{
		this.status = status;
	}
	
	
	
	
	
}
