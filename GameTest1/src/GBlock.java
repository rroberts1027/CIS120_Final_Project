import java.awt.Graphics;

//A Block that allows lasers to pass through it but not players
public class GBlock extends Block {

	public GBlock(int x, int y) {
		super(x, y);
	}
	
	@Override
	public void draw(Graphics g){
		Picture.draw(g, "GBlock.png", x, y);
	}

}
