import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class PlatformScroll{
	private Image forward; 	
	private AffineTransform tx;
	
	//attributes of this class
	int dir = 0; 					//0-forward, 1-backward, 2-left, 3-right
	int width, height;				//collision detection (hit box)
	int x, y;						//position of the object
	int vx, vy;						//movement variables
	double scaleWidth = 3.0;		//change to scale image
	double scaleHeight = 2.10; 		//change to scale image

	int pX;
	int pY;
	int newWidth;
	int newHeight;
	
	public PlatformScroll() {
		forward 	= getImage("/imgs/"+"froggerplatform.png"); //load the image for Tree
		//backward 	= getImage("/imgs/"+"backward.png"); //load the image for Tree
		//left 		= getImage("/imgs/"+"left.png"); //load the image for Tree
		//right 		= getImage("/imgs/"+"right.png"); //load the image for Tree

		//alter these
		
		//width and height for hit box
		width = 67;
		height = 40;
		
		//used for placement on the jframe
		x = 600/2-width/2;
		y = 600;
		
		vx = 4;
		vy = 0;
		
		tx = AffineTransform.getTranslateInstance(0, 0);
		
		init(x, y); 				//initialize the location of the image
									//use your variables
		
	}
	
	//2d constructor - allow setting x and y
	public PlatformScroll(int x, int y) {
		
		//call the default constructor for all the normal stuff
		this(); //invoked default constructor
		
		//do the specific tasks for THIS constructor
		this.x = x;
		this.y = y;
		
	}
	

	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		
		//update x a d y if using vx, vy variables
		x+=vx;
		y+=vy;	
		
		if(x>650) {
			x= -220;
		}
		
		init(x,y);
		

			g2.drawImage(forward, tx, null);
			
			if(Frame.debugging) {
				g.setColor(Color.green);
				g.drawRect(x+55, y+50, width+64, height-2);
			}
			
			pX = x+55;
			pY = y+50;
			newWidth = width+64;
			newHeight = height-2;
		
	}
	
	public int getpX() {
		return pX;
	}
	
	public int getpY() {
		return pY;
	}
	
	public int getWidth() {
		return newWidth;
	}
	
	public int getHeight() {
		return newHeight;
	}
	
	public void setVx(int speed) {
		vx = speed;
	}
	
	public boolean collided(GreenAlien character) {
		//represent each object as a rectangle
		//to check if they intersect
		
		Rectangle main = new Rectangle(
				character.getHitX(),
				character.getHitY(),
				character.getWidth(),
				character.getHeight()
				);
		
		Rectangle thisObject = new Rectangle(pX, pY-4, newWidth, newHeight);
		
		//user built in method to check intersection
				
		return main.intersects(thisObject);
	}
	
	
	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(scaleWidth, scaleHeight);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = PlatformScroll.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}
