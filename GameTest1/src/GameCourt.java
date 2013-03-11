import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;

@SuppressWarnings("serial")
public class GameCourt extends JComponent {
	private Player player1;  //How is the current player 1
	private Player player2;  //How is the current player 2
	
	private Player basePlayer1;  //How did player 1 start
	private Player basePlayer2;  //How did player 2 start

	private int interval = 15; // Milliseconds between updates.
	private Timer timer;       // Each time timer fires we animate one step.
	private boolean isPaused;  //Whether or not the game is paused

	final int COURTWIDTH  = 1000;
	final int COURTHEIGHT = 500;

	final int PLAYER_X_VEL  = 10;  // How fast does the player move X
	final int PLAYER_Y_VEL  = 15;  //How fast does the player move Y
	final int LASER_VEL = 10;  //How fast does a laser move
	
	private Font f;  //Font that displays player's health
	
	final int LASER_DAMAGE = 20;  //How much damage does a laser deal
	
	ArrayList<Block> allBlocks; //What blocks are on the court
	ArrayList<Laser> laserList;  //What lasers are on the court

	public GameCourt(ArrayList<Block> map, Player p1, Player p2) {
		
		try {
			f = Font.createFont(Font.TRUETYPE_FONT, new File("OCRAStd.otf"));
			f = f.deriveFont(20f);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		basePlayer1 = p1;
		basePlayer2 = p2;
		
		allBlocks = new ArrayList<Block>();
		laserList = new ArrayList<Laser>();
		
		if(map != null){
			for(int i = 0; i < map.size(); i++){
				allBlocks.add(map.get(i));
			}
		}
		
		isPaused = false;
		
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setFocusable(true);

		timer = new Timer(interval, new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				if(!isPaused)
					tick(); 
				}});
		timer.start(); 

		addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				
				int velx1 = 0;
				int vely1 = 0;
				int velx2 = 0;
				int vely2 = 0;
				
				int posx1 = player1.getMidX();
				int posy1 = player1.getMidY();
				int posx2 = player2.getMidX();
				int posy2 = player2.getMidY();
				
				if(!isPaused){
					if (e.getKeyCode() == KeyEvent.VK_DOWN) {
						player1.setY(player1.getY() - 1);
						player1.setVelocity(player1.getVelocityX(),
								-PLAYER_Y_VEL);
						posy1 += player1.getHeight() / 2 + Laser.DIAMETER / 2;
						vely1 = LASER_VEL;
					}
					else if (e.getKeyCode() == KeyEvent.VK_UP) {
						player1.setVelocity(player1.getVelocityX(),
								PLAYER_Y_VEL);
						posy1 -= player1.getHeight() / 2 + Laser.DIAMETER;
						vely1 = -LASER_VEL;
					}
					if (e.getKeyCode() == KeyEvent.VK_LEFT) {
						player1.setVelocity(PLAYER_X_VEL,
								player1.getVelocityY());
						player1.turnLeft();
						posx1 -= player1.getWidth() / 2 + Laser.DIAMETER / 2;
						velx1 = -LASER_VEL;
					}
					else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
						player1.setVelocity(-PLAYER_X_VEL,
								player1.getVelocityY());
						player1.turnRight();
						posx1 += player1.getWidth() / 2 + Laser.DIAMETER / 2;
						velx1 = LASER_VEL;
					}
					if (e.getKeyCode() == KeyEvent.VK_S) {
						player2.setY(player2.getY() - 1);
						player2.setVelocity(player2.getVelocityX(),
								-PLAYER_Y_VEL);
						posy2 += player2.getHeight() / 2 + Laser.DIAMETER / 2;
						vely2 = LASER_VEL;
					}
					else if (e.getKeyCode() == KeyEvent.VK_W) {
						player2.setVelocity(player2.getVelocityX(),
								PLAYER_Y_VEL);
						posy2 -= player2.getHeight() / 2 + Laser.DIAMETER;
						vely2 = -LASER_VEL;
					}
					if (e.getKeyCode() == KeyEvent.VK_A) {
						player2.setVelocity(PLAYER_X_VEL,
								player2.getVelocityY());
						player2.turnLeft();
						posx2 -= player2.getWidth() / 2 + Laser.DIAMETER / 2;
						velx2 = -LASER_VEL;
					}
					else if (e.getKeyCode() == KeyEvent.VK_D) {
						player2.setVelocity(-PLAYER_X_VEL,
								player2.getVelocityY());
						player2.turnRight();
						posx2 += player2.getWidth() / 2 + Laser.DIAMETER / 2;
						velx2 = LASER_VEL;
					}
				}
				if(velx1 != 0 || vely1 != 0){
					laserList.add(new Laser (
							posx1, posy1, velx1, vely1, false));
				}
				if(velx2 != 0 || vely2 != 0){
					laserList.add(new Laser (posx2, posy2, velx2, vely2, true));
				}
				if (e.getKeyCode() == KeyEvent.VK_P){
					isPaused = !isPaused;
				}
				
			}

		});
	}

	/** Set the state of the state of the game to its initial value and 
	    prepare the game for keyboard input. */
	public void reset() {
		laserList = new ArrayList<Laser>();
		player1 = basePlayer1;
		player2 = basePlayer2;
		requestFocusInWindow();
	}
	
	//Checks for collisions with players and blocks and adjusts appropriately
	public void checkCollisions(){
		for(Block b : allBlocks){
			boolean isBounce = (b instanceof BBlock);
			Intersection p1Block = player1.intersects(b);
			Intersection p2Block = player2.intersects(b);
			
			switch(p1Block){
				case NONE :
					break;
				case DOWN :
					player1.setVelocity(player1.getVelocityX(), 
							(isBounce ? -player1.getVelocityY() : 0));
					player1.setY(b.getY() - player1.getHeight());
					break;	
				case UP :
					player1.setVelocity(player1.getVelocityX(), 
							(isBounce ? -2 * player1.getVelocityY() : 1));
					player1.setY(b.getY() + b.getHeight());
					break; 
				case LEFT :
					player1.setVelocity((isBounce ? 2 * PLAYER_X_VEL : 1),
							player1.getVelocityY());
					player1.setX(b.getX() + b.getWidth());
					break; 
				case RIGHT :
					player1.setVelocity((isBounce ? -2 * PLAYER_X_VEL : -1),
							player1.getVelocityY());
					player1.setX(b.getX() - player1.getWidth());
					break; 
			}
			
			switch(p2Block){
				case NONE :
					break;
				case DOWN :
					player2.setVelocity(player2.getVelocityX(), 
							(isBounce ? -player2.getVelocityY() : 0));
					player2.setY(b.getY() - player2.getHeight());
					break;	
				case UP :
					player2.setVelocity(player2.getVelocityX(), 
							(isBounce ? -2 * player2.getVelocityY() : 1));
					player2.setY(b.getY() + b.getHeight());
					break; 
				case LEFT :
					player2.setVelocity((isBounce ? 2 * PLAYER_X_VEL : 1),
							player2.getVelocityY());
					player2.setX(b.getX() + b.getWidth());
					break; 
				case RIGHT :
					player2.setVelocity((isBounce ? -2 * PLAYER_X_VEL : -1),
							player2.getVelocityY());
					player2.setX(b.getX() - player2.getWidth());
					break; 
			}
		}
	}

	//Determines actions based on whether lasers hit players or blocks
	public void processLaser(int l){
		boolean hasHit = false;
		Laser cur = laserList.get(l);
		cur.move();
		if(cur.hasCollided(player1)){
			player1.changeHealth(-cur.getDamage());
			hasHit = true;
		}
		else if(cur.hasCollided(player2) && !(cur.isRed() && 
				player2 instanceof ArtificialPlayer)){
			player2.changeHealth(-cur.getDamage());
			hasHit = true;
		}
		else{
			for(Block b : allBlocks){
				if(b.hasCollided(cur)){
					if(b instanceof RBlock){
						cur.setVelocity(-cur.getVelocityX(),
								-cur.getVelocityY());
					}
					else if(b instanceof Health){
						((Health) b).changeHealth(-cur.getDamage());
						hasHit = true;
					}
					else if(!(b instanceof GBlock)){
						hasHit = true;
					}
				}
			}
		}
		if(hasHit){
			laserList.remove(l);
			l--;
		}
		return;
	}
	
   /** Update the game one timestep by moving the ball and the player. */
	void tick() {
		
		checkWinner();
		checkCollisions();
		player1.setBounds(getWidth(), getHeight());
		player1.move();
		player2.setBounds(getWidth(), getHeight());
		player2.move();
		if(player2 instanceof ArtificialPlayer){
			attack();
		}
		if(!laserList.isEmpty()){
			for(int l = 0; l < laserList.size(); l++){
				processLaser(l);
			}
		}
		for(int b = 0; b < allBlocks.size(); b++){
			Block cur = allBlocks.get(b);
			if ((cur instanceof Health) && (((Health) cur).healthIsZero())){
				allBlocks.remove(b);
				b--;
			}
		}  
		repaint(); // Repaint indirectly calls paintComponent.
	}

	//Returns a String with the player's name and health
	public String getPlayerInfobar(Player p){
		if(p.equals(player1)){
			return "Java : Health = " + p.getHealth();
		}
		else{
			return "OCaml : Health = " + p.getHealth();
		}
	}
	
   @Override
	public void paintComponent(Graphics g) {
	    Picture.draw(g, "Testbg.png", 0, 0);
		super.paintComponent(g); // Paint background, border
		player1.draw(g);
		player2.draw(g);
		for(Laser l : laserList){
			l.draw(g);
		}
		for(Block b : allBlocks){
			b.draw(g);
		}
		g.setColor(Color.GREEN);
		g.setFont(f);
		g.drawString(getPlayerInfobar(player2), 10, 25);
		g.drawString(getPlayerInfobar(player1), COURTWIDTH - 275, 25);

	}

   @Override
	public Dimension getPreferredSize() {
		return new Dimension(COURTWIDTH, COURTHEIGHT);
   }
   
   //Checks to see if either player's health is zero
   public void checkWinner(){
	   if(player1.healthIsZero()){
		   Game.displayWinner(false);
		   timer.stop();
	   }
	   else if(player2.healthIsZero()){
		   Game.displayWinner(true);
		   timer.stop();
	   }
   }
   
   //Moves the AI player if it is AI
   public void attack(){
	   if(((ArtificialPlayer) player2).canAttack()){
			int velx2 = 0;
			int vely2 = 0;
			int posx2 = player2.getMidX();
			int posy2 = player2.getMidY();
			
			if(player2.getX() - player1.getX() > 10){
				player2.setVelocity(-PLAYER_X_VEL,
						player2.getVelocityY());
				player2.turnRight();
				posx2 += player2.getWidth() / 2 + Laser.DIAMETER / 2;
				velx2 = LASER_VEL;
			}
			else{
				player2.setVelocity(PLAYER_X_VEL,
						player2.getVelocityY());
				player2.turnLeft();
				posx2 -= player2.getWidth() / 2 + Laser.DIAMETER / 2;
				velx2 = -LASER_VEL;
			}
			if(player2.getY() > player1.getY()){
				player2.setVelocity(player2.getVelocityX(),
						PLAYER_Y_VEL);
				posy2 -= player2.getHeight() / 2 + Laser.DIAMETER;
				vely2 = -LASER_VEL;
			}
			else{
				player2.setY(player2.getY() - 1);
				player2.setVelocity(player2.getVelocityX(),
						-PLAYER_Y_VEL);
				posy2 += player2.getHeight() / 2 + Laser.DIAMETER / 2;
				vely2 = LASER_VEL;
			}
			
			if(vely2 != 0){
				laserList.add(new Laser (posx2, posy2, 0, vely2, true));
			}
			if(velx2 != 0){
				laserList.add(new Laser (posx2, posy2, velx2, 0, true));
				laserList.add(new Laser (posx2, posy2, -velx2, 0, true));
			}
	   }
   }
   
}
