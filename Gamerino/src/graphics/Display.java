package graphics;


import javax.swing.*;
import java.awt.*;
import java.awt.Canvas;


public class Display extends JFrame{

	private JFrame frame;
	private Canvas canvas;
	
	
	
    public Display(String title, int width, int height){
         frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
         canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        
        frame.add(canvas);
        frame.pack();
    }



 
    public Canvas getCanvas()
    {
    	return canvas;
    }
    
    public JFrame getFrame()
    {
    	return frame;
    }
    
    
    
}
