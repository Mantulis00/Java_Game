package bombOb;

import java.awt.Point;
import java.awt.image.BufferedImage;

import graphics.ImageLoader;

public class SimpleBomb implements BombType {
	
	Point p;
	
	public void updatePosition(Bomb bomb)
	{
		
		p = bomb.getLocation();
		p.x += bomb.getV().x;
		p.y += bomb.getV().y;
		
		if (bomb.getV().x + bomb.getV().y < 1)
		{
			p.y += 2;
		}
		
		bomb.setLocation(p);
		
	}


	public BufferedImage getImg() 
	{
		return ImageLoader.loadImage("bomb2.jpg");
	}


	public int getPowerLvl() 
	{
		return 5;
	}

	
}
