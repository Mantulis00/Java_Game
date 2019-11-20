package bombOb;

import java.awt.Point;
import java.awt.image.BufferedImage;

import graphics.ImageLoader;

public class HeavyBomb implements BombType {

	Point p;
	
	public void updatePosition(Bomb bomb) {
		
		p = bomb.getLocation();
		p.y += 2 + bomb.getV().y;
		
		bomb.setLocation(p);
		
	}


	public BufferedImage getImg() 
	{
		return ImageLoader.loadImage("bomb1.jpg");
	}


	public int getPowerLvl() 
	{
		return 10;
	}

	
}
