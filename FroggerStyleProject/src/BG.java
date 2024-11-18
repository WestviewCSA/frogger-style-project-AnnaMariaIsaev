import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class BG{
	private Image forward; 	
	private AffineTransform tx;
	
	//attributes of this class
	int dir = 0; 					//0-forward, 1-backward, 2-left, 3-right
	int width, height;				//collision detection (hit box)
	int x, y;						//position of the object
	int vx, vy;						//movement variables
	double scaleWidth = 0.9;		//change to scale image
	double scaleHeight = 1.1; 		//change to scale image

	public BG() {
		forward 	= getImage("/imgs/"+"BG.png"); //load the image for Tree
		//backward 	= getImage("/imgs/"+"backward.png"); //load the image for Tree
		//left 		= getImage("/imgs/"+"left.png"); //load the image for Tree
		//right 		= getImage("/imgs/"+"right.png"); //load the image for Tree

		//alter these
		
		//width and height for hit box
		width = 200;
		height = 200;
		
		//used for placement on the jframe
		x = 0;
		y = 0;
		
		vx = 0;
		vy = 0;
		
		tx = AffineTransform.getTranslateInstance(0, 0);
		
		init(x, y); 				//initialize the location of the image
									//use your variables
		
	}
	
	//2d constructor - allow setting x and y
	public BG(int x, int y) {
		
		//call the default constructor for all the normal stuff
		this(); //invoked default constructor
		
		//do the specific tasks for THIS constructor
		this.x = x;
		this.y = y;
		
	}
	
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	

	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		
		//update x a d y if using vx, vy variables
		x+=vx;
		y+=vy;	
		
		init(x,y);
		

			g2.drawImage(forward, tx, null);
		
			/*
			if(Frame.debugging) {
				g.setColor(Color.green);
				g.drawRect(x+5, y+5, width, height);
			}
			*/
		
	}
	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(scaleWidth, scaleHeight);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = BG.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}