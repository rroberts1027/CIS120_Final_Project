
import java.awt.Graphics;

//An object fired by players that can subtract health from any object it hits
public class Laser extends GameObject {

	final static int DIAMETER = 15;
	final static int DAMAGE = 20;
	final int FRAME_COUNT = 20;
	
	private boolean goingUp;
	private boolean inFrame;
	private boolean isRed;
	private int currentFrame;
	
	public Laser(int x, int y, int velocityX, int velocityY, boolean red) {
		super(x, y, velocityX, velocityY, DIAMETER, DIAMETER);
		inFrame = true;
		isRed = red;
		currentFrame = 0;
		goingUp = true;
	}
	
	//Returns whether the laser is red or not
	public boolean isRed(){
		return isRed;
	}

	//Returns how much damage the laser deals
	public int getDamage(){
		return DAMAGE;
	}
	
	//Returns whether or not the object is in frame
	public boolean checkInFrame(){
		return inFrame;
	}
	
	@Override
	public void clip() {
		if (x < 0)
			inFrame = false;
		else if (x > rightBound)
			inFrame = false;

		if (y < 0)
			inFrame = false;
		else if (y > bottomBound)
			inFrame = false;
	}
	
	@Override
	public void accelerate() {
	}

	//Draws one of two sprites of the laser depending on how many ticks passed
	@Override
	public void draw(Graphics g) {
		if(goingUp && currentFrame < FRAME_COUNT){
			if(isRed){
				Picture.draw(g, "LRed1.png", x, y);
			}
			else{
				Picture.draw(g, "LBlue1.png", x, y);
			}
			currentFrame++;
		}
		else{
			goingUp = false;
			if(isRed){
				Picture.draw(g, "LRed2.png", x, y);
			}
			else{
				Picture.draw(g, "LBlue2.png", x, y);
			}
			currentFrame--;
			if(currentFrame == 0){
				goingUp = true;
			}
			
		}
	}

}
