
//Creates a player that functions with AI determined in the court
public class ArtificialPlayer extends Player implements Health {

	private int delayStep = 0;
	private int difficulty;
	
	public ArtificialPlayer(int x, int y, int d, String img, String hitImg) {
		super(x, y, img, hitImg);
		difficulty = d;
	}
	
	// Sets the difficulty (movement speed) of the AI player
	public void setDifficulty(int i){
		difficulty = i;
	}
	
	public int getDifficulty(){
		return difficulty;
	}


	 //Determines which keys should be internally pressed to move the 
	 //AI player closer to the user, and presses them
	public boolean canAttack(){
		if(delayStep <= difficulty){
			delayStep++;
			return false;
		}
		delayStep = 0;
		return true;
	}

}
