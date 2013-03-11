
import java.awt.Graphics;

//A destructible block that takes damage over time
public class DBlock extends Block implements Health {

	private int health;
	
	final static int MAX_HEALTH = 100;
	
	public DBlock(int x, int y) {
		super(x, y);
		health = MAX_HEALTH;
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
	
	//Draws a state of the block dependant on what percentage of health
	//is remaining.
	@Override
	public void draw(Graphics g){
		if(health > (2 * MAX_HEALTH) / 3){
			Picture.draw(g, "DBlock1.png", x, y);
		}
		else if(health > MAX_HEALTH / 3){
			Picture.draw(g, "DBlock2.png", x, y);
		}
		else{
			Picture.draw(g, "DBlock3.png", x, y);
		}
	}

}
