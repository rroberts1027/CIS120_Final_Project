import java.awt.Graphics;

public abstract class GameObject {
	int x; // x and y coordinates upper left
	int y;

	int width;
	int height;

	int velocityX; // Pixels to move each time move() is called.
	int velocityY;

	int rightBound; // Maximum permissible x, y values.
	int bottomBound;

	public GameObject(int x, int y, int velocityX, int velocityY, int width,
			int height) {
		this.x = x;
		this.y = y;
		this.velocityX = velocityX;
		this.velocityY = velocityY;
		this.width = width;
		this.height = height;
	}

	public void setBounds(int width, int height) {
		rightBound = width - this.width;
		bottomBound = height - this.height;
	}

	public void setVelocity(int velocityX, int velocityY) {
		this.velocityX = velocityX;
		this.velocityY = velocityY;
	}

	// Move the object at the given velocity and then accelerate it.
	public void move() {
		x += velocityX;
		y += velocityY;
		accelerate();
		clip();
	}

	// Keep the object in the bounds of the court
	public void clip() {
		if (x < 0)
			x = 0;
		else if (x > rightBound)
			x = rightBound;

		if (y < 0)
			y = 0;
		else if (y > bottomBound)
			y = bottomBound;
	}
	
	//Returns the X position of the object
	public int getX(){
		return x;
	}
	
	//Returns the Y position of the object
	public int getY(){
		return y;
	}
	
	//Returns the X midpoint of the object from its width
	public int getMidX(){
		return x + width / 2;
	}
	
	//Returns the Y midpoint of the object from its height
	public int getMidY(){
		return y + height / 2;
	}
	
	//Returns the X velocity of the object
	public int getVelocityX(){
		return velocityX;
	}
	
	//Returns the Y velocity of the object
	public int getVelocityY(){
		return velocityY;
	}
	
	//Returns the height of the object
	public int getHeight(){
		return height;
	}
	
	//Returns the width of the object
	public int getWidth(){
		return width;
	}
	
	//Changes the X coordinate of the object
	public void setX(int newX){
		x = newX;
	}
	
	//Changes the Y coordinate of the object
	public void setY(int newY){
		y = newY;
	}
	

	/**
	 * Compute whether two GameObjects intersect.
	 * 
	 * @param other
	 *            The other game object to test for intersection with.
	 * @return NONE if the objects do not intersect. Otherwise, a direction
	 *         (relative to <code>this</code>) which points towards the other
	 *         object.
	 */
	
	public boolean hasCollided(GameObject other){
		return !(intersects(other) == Intersection.NONE);
	}
	
	public Intersection intersects(GameObject other) {
		if (       other.x > x + width
				|| other.y > y + height
				|| other.x + other.width < x
				|| other.y + other.height < y)
			return Intersection.NONE;

		// compute the vector from the center of this object to the center of
		// the other
		double dx = other.x + other.width /2 - (x + width / 2);
		double dy = other.y + other.height /2 - (y + height / 2);

		double theta = Math.atan2(dy, dx);
		double diagTheta = Math.atan2(height, width);

		if ( diagTheta <= theta && theta <= Math.PI - diagTheta )
			return Intersection.DOWN;
		if ( -diagTheta <= theta && theta <= diagTheta )
			return Intersection.RIGHT;
		if ( Math.PI - diagTheta <= theta || theta <= diagTheta - Math.PI )
			return Intersection.LEFT;
		// if ( diagTheta - Math.PI <= theta && theta <= diagTheta)
		return Intersection.UP;
	}

	/**
	 * Should be overridden to allow game objects to change velocity or 
	 * direction.  See Ball.java for an example.
	 */
	public abstract void accelerate();

	/**
	 * Should be overridden to provide game-object specific drawing.
	 * 
	 * @param g
	 * 		The <code>Graphics</code> context used for drawing the object.
	 */
	public abstract void draw(Graphics g);
}
