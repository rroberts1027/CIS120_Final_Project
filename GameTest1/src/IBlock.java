
import java.awt.Graphics;

//An indestructible block
public class IBlock extends Block {

	public IBlock(int x, int y) {
		super(x, y);
	}
	
	@Override
	public void draw(Graphics g){
		Picture.draw(g, "IBlock.png", x, y);
	}

}
