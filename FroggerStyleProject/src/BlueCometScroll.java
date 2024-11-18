import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class BlueCometScroll{
	private Image forward; 	
	private AffineTransform tx;
	
	//attributes of this class
	int dir = 0; 					//0-forward, 1-backward, 2-left, 3-right
	int width, height;				//collision detection (hit box)
	int x, y;						//position of the object
	int vx, vy;						//movement variables
	double scaleWidth = 0.2;		//change to scale image
	double scaleHeight = 0.2; 		//change to scale image

	int blueX;
	int blueY;
	int newHt;
	
	public BlueCometScroll() {
		forward 	= getImage("/imgs/"+"cometBlue.png"); //load the image for Tree
		//backward 	= getImage("/imgs/"+"backward.png"); //load the image for Tree
		//left 		= getImage("/imgs/"+"left.png"); //load the image for Tree
		//right 		= getImage("/imgs/"+"right.png"); //load the image for Tree

		//alter these
		
		//width and height for hit box
		width = 40;
		height = 40;
		
		//used for placement on the jframe
		x = 600/2-width/2;
		y = 600;
		
		vx = -7;
		vy = 0;
		
		tx = AffineTransform.getTranslateInstance(0, 0);
		
		init(x, y); 				//initialize the location of the image
									//use your variables
		
	}
	
	//2d constructor - allow setting x and y
	public BlueCometScroll(int x, int y) {
		
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
		
		if(x<-90) {
			x= 810;
		}
		
		init(x,y);
		

			g2.drawImage(forward, tx, null);
			
			if(Frame.debugging) {
				g.setColor(Color.green);
				g.drawRect(x+58, y+14, width, height-3);
			}
		
		blueX = x+58;
		blueY = y+14;
		newHt = height -3;
			
	}
	/*
	public int getBlueX() {
		return blueX;
	}
	
	public int getBlueY() {
		return blueY;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return newHt;
	}
	*/
	
	public boolean collided(GreenAlien character) {
		//represent each object as a rectangle
		//to check if they intersect
		
		Rectangle main = new Rectangle(
				character.getHitX(),
				character.getHitY(),
				character.getWidth(),
				character.getHeight()
				);
		
		Rectangle thisObject = new Rectangle(blueX, blueY, width, newHt);
		
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
			URL imageURL = BlueCometScroll.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}
