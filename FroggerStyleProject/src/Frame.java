import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Frame extends JPanel implements ActionListener, MouseListener, KeyListener {
	
	//any debugging code we add
	public static boolean debugging = true;
	
	
	
	private Image bg = getImage("/imgs/"+ "BG.png"); 
	
	//Timer related variables
	int waveTimer = 5; //each wave of enemies is 20s
	long ellapseTime = 0;
	Font timeFont = new Font("Courier", Font.BOLD, 70);
	int level = 0;
	
	
	Font myFont = new Font("Courier", Font.BOLD, 40);
	SimpleAudioPlayer backgroundMusic = new SimpleAudioPlayer("scifi.wav", false);
//	Music soundBang = new Music("bang.wav", false);
//	Music soundHaha = new Music("haha.wav", false);
	
	public boolean riding = false;
	
	BG bg2 = new BG();
	
	//create alien objects
	GreenAlien mainAlien = new GreenAlien();
	//GreenAlien newAlien2 = new GreenAlien(100, 200);
	
	//create red alien objects
	//RedAlien red = new RedAlien(300, 200);
	
	//frame width/height
	int width = 600;
	int height = 600;	
	
	RedAlienScroller[] row1 = new RedAlienScroller[4];
	BlueCometScroll[] row2 = new BlueCometScroll[5];
	PlatformScroll[] row3 = new PlatformScroll[3];
	PlatformScroll2[] row4 = new PlatformScroll2[3];
	RedAlienScroller[] row5 = new RedAlienScroller[4];
	

	public void paint(Graphics g) {
		super.paintComponent(g);
		
		
		bg2.paint(g);
		
		
		for(PlatformScroll obj : row3) {
			obj.paint(g);
		}
		
		
		for(PlatformScroll2 obj4 : row4) {
			obj4.paint(g);;
				
		}
		
for(PlatformScroll obj : row3) {
			
			if(obj.collided(mainAlien)) {
				mainAlien.setVx(4);
				riding = true;
				break;
			} else {
				riding = false;
			}
		}
	
	for(PlatformScroll2 obj : row4) {
		
		if(obj.collided(mainAlien)) {
			mainAlien.setVx(-5);
			riding = true;
			break;
		} else {
			riding = false;
		}
	}
		
		mainAlien.paint(g);
		//newAlien2.paint(g);
		//red.paint(g);
		
		for(RedAlienScroller obj : row1) {
			obj.paint(g);
			if(obj.collided(mainAlien)) {
				mainAlien.setCoord(600/2-mainAlien.getWidth(), 505);
			}
		}
	
		
		for(RedAlienScroller obj : row5) {
			obj.paint(g);
			if(obj.collided(mainAlien)) {
				mainAlien.setCoord(600/2-mainAlien.getWidth(), 505);
			}
		}
		
		for(BlueCometScroll obj : row2) {
			obj.paint(g);
			
			if(obj.collided(mainAlien)) {
				mainAlien.setCoord(600/2-mainAlien.getWidth(), 505);
			}
		}
		
		
	
		if((!riding && mainAlien.getY() <= 270 && mainAlien.getY() >=245)) {
			mainAlien.setVx(0);
			System.out.println("danger zone");
			mainAlien.setCoord(600/2-mainAlien.getWidth(), 505);
		} /* else if(!riding && mainAlien.getY() <= 200 && mainAlien.getY() >= 190) {
			mainAlien.setVx(0);
			System.out.println("danger zone");
			mainAlien.setCoord(600/2-mainAlien.getWidth(), 505);
		}
		*/
		else if(!riding) {
			mainAlien.setVx(0);
			
		}
		
		
			
				
				
				
			}
		
	
	public static void main(String[] arg) {
		Frame f = new Frame();
		
	}
	public Frame() {
		JFrame f = new JFrame("Duck Hunt");
		f.setSize(new Dimension(width, height));
		f.setBackground(Color.white);
		f.add(this);
		f.setResizable(false);
 		f.addMouseListener(this);
		f.addKeyListener(this);
	
		backgroundMusic.play();
		
		for(int i = 0; i < row1.length; i++) {
			row1[i] = new RedAlienScroller(i*210, 420);
		}
		
		for(int i = 0; i < row2.length; i++) {
			row2[i] = new BlueCometScroll(i*180, 323);
		}
		
		for(int i = 0; i < row3.length; i++) {
			row3[i] = new PlatformScroll(i*310, 205);
		}
		
		for(int i = 0; i < row4.length; i++) {
			row4[i] = new PlatformScroll2(i*280, 120);
		}
		
		for(int i = 0; i < row5.length; i++) {
			row5[i] = new RedAlienScroller(i*220, 75);
		}
		
		
	

		
		//the cursor image must be outside of the src folder
		//you will need to import a couple of classes to make it fully 
		//functional! use eclipse quick-fixes
		setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
				new ImageIcon("torch.png").getImage(),
				new Point(0,0),"custom cursor"));	
		
		
		Timer t = new Timer(16, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	
	/*
	private Image getImage(String string) {
		Image tempImage = null;
		try {
			URL imageURL = GreenAlien.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}
	
	private Image bg = getImage("/imgs/" + "BG.png");
	*/
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(arg0);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent m) {
		
	
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(arg0.getKeyCode());
		
		
		if(arg0.getKeyCode()==87 || arg0.getKeyCode()==38){
			//move up
			mainAlien.move(0);
		} else if(arg0.getKeyCode()==83 || arg0.getKeyCode()==40) {
			//move down
			mainAlien.move(1);
		} else if(arg0.getKeyCode()==65 || arg0.getKeyCode()==37) {
			//move left
			mainAlien.move(2);
		} else if(arg0.getKeyCode()==68 || arg0.getKeyCode()==39) {
			//move right
			mainAlien.move(3);
		}
		
		
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
	/* if(arg0.getKeyCode()==38) {
		
	}
	*/
		
	}
	
	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = GreenAlien.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}
