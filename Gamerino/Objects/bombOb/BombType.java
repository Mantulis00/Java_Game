package bombOb;

import java.awt.image.BufferedImage;

public interface BombType {

	public void updatePosition(Bomb bomb);
	
	public BufferedImage getImg();
	
	public int getPowerLvl();
	
}
