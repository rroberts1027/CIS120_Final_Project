import java.awt.Graphics;

//A block that bounces the player away from it when touched
public class BBlock extends Block {

	public BBlock(int x, int y) {
		super(x, y);
	}

	@Override
	public void draw(Graphics g){
		Picture.draw(g, "BBlock.png", x, y);
	}

}
