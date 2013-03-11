import java.awt.Graphics;

//Block that reflects lasers in the other direction
public class RBlock extends Block {

	public RBlock(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics g){
		Picture.draw(g, "RBlock.png", x, y);
	}
	
}
