package mains;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import bombOb.Bomb;
import calculations.ColiderCheck;
import calculations.ExplosionCalculations;
import calculations.Gravity;
import graphics.Display;
import graphics.ImageLoader;
import listeners.KeyBoardListener;
import listeners.MouseControl;
import planeOb.Plane;
import planeOb.SimplePlaneBuilder;
import world.WorldMap;


///TO DO

// Run explosions on  seperate thread
// gravity
// scalable camera ?
// optimize drawing map, dont refresh ground which was not deleted




public class ActionField implements Runnable {
	
	
	Display display;
	
	BufferStrategy bs;
	Graphics g;
	

	BufferedImage image;
	
	
	boolean gameWindowVisible;
	Thread thread;
	
	int height, width;
	String title, path;
	
	WorldMap map;
	
	
	MouseControl mouseListener;
	KeyBoardListener keyListener;
	
	Plane plane;
	Bomb bomb;
	
	Point mouseLocation;
	
	ExplosionCalculations explosionEvent;
	ColiderCheck groundColider;
	Gravity gravity;
	
	ActionField(String title,int height,int width, String path)
	{
		this.title = title;
		this.height = height;
		this.width = width;
		this.path = path;

		map = new WorldMap();
			
		//image = ImageLoader.loadImage(path);
		
		mouseLocation = new Point();
	}
	
	
	
	public synchronized void start()
	{
		if (gameWindowVisible)
			return;
		
		gameWindowVisible = true;
		thread = new Thread(this);
		
		thread.start();
	}
	
	public synchronized void stop()
	{
		if (!gameWindowVisible)
			return;
		
		gameWindowVisible = false;
		try
		{
			thread.join();
		}
		catch (Exception e)
		{
			
		}
	}
	

	
	
	
	
	public void run()
	{
		init();
		double start, fps;
		double fpsSet  =  1000000000/60;
		
		while(gameWindowVisible)
		{
			start = System.nanoTime();
			
			
			update();
			render();
			fps = (System.nanoTime() - start);
			
			if (fps <= fpsSet)
			{
				try 
				{
					Thread.sleep((int)(fpsSet - fps)/1000000);
	        	} 
				catch (Exception e) 
				{
				
	        	}
			}
			System.out.println(1000000000/(System.nanoTime() - start));
		}
		
		stop();
	}
	
	private void init()
	{
		display = new Display(title, height, width);
		map.generateDesert(800, 300, 600);
		generatePlane();
		initListeners();
		initEvents();
	}
	
	private void initListeners()
	{
		mouseListener = new MouseControl();	
		display.getFrame().addMouseMotionListener(mouseListener);
		display.getCanvas().addMouseMotionListener(mouseListener);
		
		keyListener = new KeyBoardListener();
		display.getFrame().addKeyListener(keyListener);
		display.getCanvas().addKeyListener(keyListener);
	}
	
	private void initEvents()
	{
		explosionEvent = new ExplosionCalculations(map);
		groundColider = new ColiderCheck(map);
		gravity = new Gravity();
	}
	
	
	
	

	private void update()
	{
		checkListeners();
		updatePlaneLocation();
		
		if (bomb != null)
		updateBombLocation();
		
		
		
	}
	
	private void checkListeners()
	{
		if (keyListener.bombTrigger())
		{	
			generateBomb();
		}
		
		if (bomb != null)
		{
			if (bomb.getV().x != 0 || bomb.getV().y !=0)
			{
				if (groundColider.isTouchingGround(bomb)) 
				{
					explosionEvent.roundExplosion(bomb.getLocation().x, bomb.getLocation().y, 25);
					bomb = null;
				}
			}
		}
		
		gravity.mapGravity(map);
		
		
	}
	
	
	private void updatePlaneLocation()
	{
		plane.followPoint(mouseListener.getLocation());
	}
	
	private void updateBombLocation()
	{
		bomb.getType().updatePosition(bomb);
	}
	
	
	private void generatePlane()
	{
		 Point location = new Point(200, 200);
		 planeOb.PlaneBuilder simplebuild = new planeOb.SimplePlaneBuilder();
		 planeOb.PlaneEngineer SlowEng = new planeOb.PlaneEngineer(simplebuild, location, 5, image);
		 SlowEng.makePlane();
		 plane =  SlowEng.getPlane();
	}
	
	private void generateBomb()
	{
		bomb = new Bomb();
		bomb.setBombType(false);
		bomb.setStatus(true);
		bomb.setLocation(plane.getLocation());
		bomb.setV(plane.getSpeeds());
	}
	
	
	
	
	
	private void render()
	{
		 bs = display.getCanvas().getBufferStrategy();
		
		if (bs == null)
		{
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		
		g = bs.getDrawGraphics();
		g.clearRect(0, 0, width, height);
		
		
		// Grafiku pradzia
		
		drawMap();
		drawPlane();
		drawBomb();

		//g.setColor(Color.cyan);
		//g.fillRect(0, 0, 800, 600);
		//g.drawLine(x1, y1, x2, y2);
		//g.drawImage(image, 0, 0, height, width, null);
		
		
		
		bs.show();
		g.dispose();
	}
	
	private void drawMap()
	{
		//BufferedImage gTexture = ImageLoader.loadImage("pic.jpg");
		if(false)
		for (int x = 0; x<map.getGroundObjects().size(); x++)
		{
			//g.drawImage(gTexture ,map.getGroundObjects().get(x).x, 
				//	   map.getGroundObjects().get(x).y
					//   , map.getBlockSize(), map.getBlockSize(), null);
			if ((map.getGroundObjects().get(x).x+map.getGroundObjects().get(x).y)%2 == 0)
				g.setColor(Color.red);
			else
				g.setColor(Color.blue);
			
			g.fillRect(map.getGroundObjects().get(x).x, map.getGroundObjects().get(x).y, map.getBlockSize(), map.getBlockSize());
			
		}
		drawIndicators();
	}
	
	private void drawIndicators()
	{
		
		for (int x = 0; x<map.getTopGroundObjects().size(); x++)
		{
			if (map.getTopGroundObjects().get(x) != -1  && map.getTopGroundObjects().get(x) < map.getGroundObjects().size())
			{
				g.setColor(Color.black);
				g.fillRect(	map.getGroundObjects().get(map.getTopGroundObjects().get(x)).x, 
							map.getGroundObjects().get(map.getTopGroundObjects().get(x)).y, 
							map.getBlockSize(), map.getBlockSize());
				
				
				g.setColor(Color.red);
				if (map.getTopGroundObjects().get(x)+20 < map.getGroundObjects().size())
				{
				for (int y = map.getTopGroundObjects().get(x)+1; y<map.getTopGroundObjects().get(x)+20; y++)
				{
					if (map.getGroundObjects().get(map.getTopGroundObjects().get(x)).x == map.getGroundObjects().get(y).x)
					{
						g.fillRect(	map.getGroundObjects().get(y).x, 
								map.getGroundObjects().get(y).y, 
								map.getBlockSize(), map.getBlockSize());
					}
					else
						break;
				
				}
				}
				
			}
		}
		
		g.setColor(Color.cyan);
		for (int x = 0; x<map.getHangingObjects().size(); x++)
		{
			g.fillRect(	map.getGroundObjects().get(map.getHangingObjects().get(x)).x, 
						map.getGroundObjects().get(map.getHangingObjects().get(x)).y, 
						map.getBlockSize(), map.getBlockSize());
		}
		
		
	}
	
	
	
	
	private void drawPlane()
	{
		g.setColor(Color.cyan);
		g.fillRect(plane.getLocation().x, plane.getLocation().y, 20, 20);
	}
	
	private void drawBomb()
	{
		if (bomb != null)
		{
			g.setColor(Color.red);
			g.fillRect(bomb.getLocation().x, bomb.getLocation().y, 10, 10);
		}
	}
	
	
	private void tempMes()
	{
	//	System.out.println(plane.getLocation() + " " + mouseListener.getLocation());
	}
	
	
	
	
	
	
	
	

}
