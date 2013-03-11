import java.awt.Graphics;


public class Player extends GameObject implements Health {
	
	private int health;
	private String rightImage;
	private String leftImage;
	private boolean isLeft;
	
	final static int MAX_HEALTH = 500;
	final static int SHAPE_SIZE = 31;
	final static int X_ACCELERATION_FACTOR = 1;
	final static int Y_ACCELERATION_FACTOR = 1;
	
	public Player(int x, int y, String rImg, String lImg) {
		super(x, y, 0, 0, SHAPE_SIZE, SHAPE_SIZE);
		health = MAX_HEALTH;
		rightImage = rImg;
		leftImage = lImg;
		isLeft = true;
	}
	
	//States whether the player is facing left
	public void turnLeft(){
		isLeft = true;
	}
	
	//States whether the player is facing right
	public void turnRight(){
		isLeft = false;
	}

	//Accelerates the player in the given direction
	@Override
	public void accelerate() {
		if (velocityX < 0){
			velocityX += X_ACCELERATION_FACTOR;
		}
		else if (velocityX > 0){
			velocityX -= X_ACCELERATION_FACTOR;
		}
		
		velocityY += Y_ACCELERATION_FACTOR;
		if(velocityY > 20){
			velocityY = 20;
		}
		
	}
	
	//Draws the left or right sprite of the player depending on which direction 
	//it last moved
	@Override
	public void draw(Graphics g) {
		if(isLeft){
			Picture.draw(g, leftImage, x, y);
		}
		else{
			Picture.draw(g, rightImage, x, y);
		}
	}

	//Returns the right sprite image of the player
	public String getRightImage(){
		return rightImage;
	}

	@Override
	public int getHealth() {
		return health;
	}

	@Override
	public void changeHealth(int value) {
		health += value;
	}

	@Override
	public boolean healthIsZero() {
		return (health <= 0);
	}

}
