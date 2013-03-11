import java.awt.Graphics;

//The standard terrain piece
public abstract class Block extends GameObject {
	
	final static int BLOCK_SIZE = 20;

	public Block(int x, int y) {
		super(x, y, 0, 0, BLOCK_SIZE, BLOCK_SIZE);
	}
	
	@Override
	public void accelerate() {
		velocityX = 0;
		velocityY = 0;
	}

	@Override
	public void draw(Graphics g){
		g.fillRect(x, y, BLOCK_SIZE, BLOCK_SIZE);
	}

}