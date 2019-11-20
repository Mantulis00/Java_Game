package listeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBoardListener implements KeyListener  {

	boolean bombT;
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyChar() == 'w')
		{
			
		}
		else if (e.getKeyChar() == 's')
		{
			
		}
		else if (e.getKeyChar() == ' ')
		{
			bombT = true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean bombTrigger()
	{
		if (bombT)
		{
			bombT = false;
			return true;
		}

		
		return false;
	}
	
	
	

}
