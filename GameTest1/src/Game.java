import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel; 
import javax.swing.SwingUtilities;

import javax.sound.sampled.*;


public class Game implements Runnable {
	
	private static boolean isAI = false;
	final static JFrame frame = new JFrame("Recoil");
	final static JPanel mainPanel = new JPanel();
	final static JPanel mapPanel = new JPanel();
	final static JPanel gamePanel = new JPanel();
	final static JPanel numberPanel = new JPanel();
	final static JPanel difficultyPanel = new JPanel();
	final static JPanel instructionPanel = new JPanel();
	final static JPanel ocamlWinPanel = new JPanel();
	final static JPanel javaWinPanel = new JPanel();
	
	public Game() {
	}
	
	//Display the winner after a game
	public static void displayWinner(boolean javaWon){
		if(javaWon){
			frame.remove(gamePanel);
			frame.add(javaWinPanel);
			frame.pack();
		}
		else{
			frame.remove(gamePanel);
			frame.add(ocamlWinPanel);
			frame.pack();
		}
	}

	@Override
	public void run() {
			
		//Plays the games music
			try {
				AudioInputStream a = AudioSystem.getAudioInputStream(
						new File("skylight.wav"));
				Clip c = AudioSystem.getClip();
				c.open(a);
				c.loop(Clip.LOOP_CONTINUOUSLY);
				
			} catch (UnsupportedAudioFileException e2) {
				e2.printStackTrace();
			} catch (IOException e2) {
				e2.printStackTrace();
			} catch (LineUnavailableException e1) {
				e1.printStackTrace();
			}

			final Player player1 = new Player(960, 460,
					"JPlayerR.png", "JPlayerL.png");
			final Player player2AI = new ArtificialPlayer(20, 460, 20,
					"CPlayerR.png", "CPlayerL.png");
			final Player player2User = new Player(20, 460,
					"CPlayerR.png", "CPlayerL.png");
	      

			frame.setLocation(60, 30);
			frame.setSize(1000, 500);
			frame.setMinimumSize(new Dimension(1000, 560));
			frame.setPreferredSize(new Dimension(1200, 560));
			
			
			//Buttons that represent the main menu options
		    final JButton title = new JButton(new ImageIcon("MenuBGTop.png"));
		    title.addActionListener(new ActionListener() {
		       public void actionPerformed(ActionEvent e) {
		       }
		    });
		    mainPanel.add(title, BorderLayout.NORTH);
		  
			
		  final JButton instructions = new JButton(
				  new ImageIcon("MenuBGLeft.png"));
	      instructions.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 frame.remove(mainPanel);
	        	 frame.add(instructionPanel);
	        	 frame.pack();
	         }
	      });
	      mainPanel.add(instructions, BorderLayout.SOUTH);
	      
	      final JButton playGame = new JButton(
	    		  new ImageIcon("MenuBGRight.png"));
	      playGame.addActionListener(new ActionListener(){
	    	 public void actionPerformed(ActionEvent e){
	    		 frame.remove(mainPanel);
	    		 frame.add(numberPanel);
	 			 frame.pack();
	    	 }
	      });
	      mainPanel.add(playGame, BorderLayout.SOUTH);
	      
		    final JButton instMain = new JButton(
		    		new ImageIcon("Instructions.png"));
		    instMain.addActionListener(new ActionListener() {
		       public void actionPerformed(ActionEvent e) {
		    	   frame.remove(instructionPanel);
		    	   frame.add(mainPanel);
		    	   frame.pack();
		       }
		    });
		    instructionPanel.add(instMain, BorderLayout.NORTH);
		    
		    //Buttons that represent the different maps
		    final JButton blank = new JButton(new ImageIcon("MapTGIA.png"));
		    blank.addActionListener(new ActionListener() {
		       public void actionPerformed(ActionEvent e) {
		    	   
		    	   GameCourt court = new GameCourt(ListOfMaps.setBorder(
		    			   new ArrayList<Block>()), player1,
		    			   (isAI ? player2AI : player2User));
		    	   gamePanel.removeAll();
		           gamePanel.add(court, BorderLayout.CENTER);
		           frame.remove(mapPanel);
		           frame.remove(difficultyPanel);
		           frame.add(gamePanel);
		           frame.pack();
		           court.reset();
		       }
		    });
		    mapPanel.add(blank, BorderLayout.NORTH);
			
	 JButton cis120 = new JButton(new ImageIcon("MapCIS.png"));
		    cis120.addActionListener(new ActionListener() {
		       public void actionPerformed(ActionEvent e) {
		    	   GameCourt court = new GameCourt(ListOfMaps.getCis120(),
		    			   player1, (isAI ? player2AI : player2User));
		    	   gamePanel.removeAll();
		   	       gamePanel.add(court, BorderLayout.CENTER);
		           frame.remove(mapPanel);
		           frame.remove(difficultyPanel);
		           frame.add(gamePanel);
		           frame.pack();
		           court.reset();
		       }
		    });
		    mapPanel.add(cis120, BorderLayout.NORTH);
		    
		    final JButton reflect = new JButton(new ImageIcon("MapMM.png"));
		    reflect.addActionListener(new ActionListener() {
		       public void actionPerformed(ActionEvent e) {
		    	   GameCourt court = new GameCourt(ListOfMaps.getReflectMap(),
		    			   player1, (isAI ? player2AI : player2User));
		    	   gamePanel.removeAll();
		   	       gamePanel.add(court, BorderLayout.CENTER);
		           frame.remove(mapPanel);
		           frame.remove(difficultyPanel);
		           frame.add(gamePanel);
		           frame.pack();
		           court.reset();
		       }
		    });
		    mapPanel.add(reflect, BorderLayout.NORTH);
		    
		    final JButton stones = new JButton(new ImageIcon("MapSNTS.png"));
		    stones.addActionListener(new ActionListener() {
		       public void actionPerformed(ActionEvent e) {
		    	   GameCourt court = new GameCourt(ListOfMaps.getStonesMap(),
		    			   player1, (isAI ? player2AI : player2User));
		    	   gamePanel.removeAll();
		   	       gamePanel.add(court, BorderLayout.CENTER);
		           frame.remove(mapPanel);
		           frame.remove(difficultyPanel);
		           frame.add(gamePanel);
		           frame.pack();
		           court.reset();
		       }
		    });
		    mapPanel.add(stones, BorderLayout.NORTH);
		    
		    final JButton wood = new JButton(new ImageIcon("MapW.png"));
		    wood.addActionListener(new ActionListener() {
		       public void actionPerformed(ActionEvent e) {
		    	   GameCourt court = new GameCourt(ListOfMaps.getWoodMap(),
		    			   player1, (isAI ? player2AI : player2User));
		    	   gamePanel.removeAll();
		   	       gamePanel.add(court, BorderLayout.CENTER);
		           frame.remove(mapPanel);
		           frame.remove(difficultyPanel);
		           frame.add(gamePanel);
		           frame.pack();
		           court.reset();
		       }
		    });
		    mapPanel.add(wood, BorderLayout.SOUTH);
		    
		    final JButton bounce = new JButton(new ImageIcon("MapBT.png"));
		    bounce.addActionListener(new ActionListener() {
		       public void actionPerformed(ActionEvent e) {
		    	   GameCourt court = new GameCourt(ListOfMaps.getBounceMap(),
		    			   player1, (isAI ? player2AI : player2User));
		    	   gamePanel.removeAll();
		   	       gamePanel.add(court, BorderLayout.CENTER);
		           frame.remove(mapPanel);
		           frame.remove(difficultyPanel);
		           frame.add(gamePanel);
		           frame.pack();
		           court.reset();
		       }
		    });
		    mapPanel.add(bounce, BorderLayout.SOUTH);
		    
		    final JButton snipe = new JButton(new ImageIcon("MapBPBE.png"));
		    snipe.addActionListener(new ActionListener() {
		       public void actionPerformed(ActionEvent e) {
		    	   GameCourt court = new GameCourt(ListOfMaps.getSnipeMap(),
		    			   player1, (isAI ? player2AI : player2User));
		    	   gamePanel.removeAll();
		   	       gamePanel.add(court, BorderLayout.CENTER);
		           frame.remove(mapPanel);
		           frame.remove(difficultyPanel);
		           frame.add(gamePanel);
		           frame.pack();
		           court.reset();
		       }
		    });
		    mapPanel.add(snipe, BorderLayout.SOUTH);
		    
		    final JButton close = new JButton(new ImageIcon("MapBOTI.png"));
		    close.addActionListener(new ActionListener() {
		       public void actionPerformed(ActionEvent e) {
		    	   GameCourt court = new GameCourt(ListOfMaps.getCloseMap(),
		    			   player1, (isAI ? player2AI : player2User));
		    	   gamePanel.removeAll();
		   	       gamePanel.add(court, BorderLayout.CENTER);
		           frame.remove(mapPanel);
		           frame.remove(difficultyPanel);
		           frame.add(gamePanel);
		           frame.pack();
		           court.reset();
		       }
		    });
		    mapPanel.add(close, BorderLayout.SOUTH);
		    
		    //Buttons that represent how many humans are playing the game
		    final JButton multiPlayer = new JButton(
		    		new ImageIcon("PlayerNumber2.png"));
		    multiPlayer.addActionListener(new ActionListener() {
			       public void actionPerformed(ActionEvent e) {
			    	   isAI = false;
			    	   frame.remove(numberPanel);
			    	   frame.add(mapPanel);
			    	   frame.pack();
			       }
			    });
		    numberPanel.add(multiPlayer);
		    
		    final JButton singlePlayer = new JButton(
		    		new ImageIcon("PlayerNumber1.png"));
		    singlePlayer.addActionListener(new ActionListener() {
			       public void actionPerformed(ActionEvent e) {
			    	   isAI = true;
			    	   frame.remove(numberPanel);
			   		   frame.add(difficultyPanel);
			   		   frame.pack();
			       }
			    });    
		    numberPanel.add(singlePlayer);
		    
		    
		    //Buttons that represent AI difficulty
		    final JButton easy = new JButton(
		    		new ImageIcon("DifficultyEasy.png"));
		    easy.addActionListener(new ActionListener() {
			       public void actionPerformed(ActionEvent e) {
			    	   ((ArtificialPlayer) player2AI).setDifficulty(20);
			    	   frame.remove(difficultyPanel);
			    	   frame.add(mapPanel);
			    	   frame.pack();
			       }
			    });    
		    difficultyPanel.add(easy);
		    
		    final JButton medium = new JButton(
		    		new ImageIcon("DifficultyMedium.png"));
		    medium.addActionListener(new ActionListener() {
			       public void actionPerformed(ActionEvent e) {
			    	   ((ArtificialPlayer) player2AI).setDifficulty(13);
			    	   frame.remove(difficultyPanel);
			    	   frame.add(mapPanel);
			    	   frame.pack();
			       }
			    });    
		    difficultyPanel.add(medium);
		    
		    final JButton hard = new JButton(
		    		new ImageIcon("DifficultyHard.png"));
		    hard.addActionListener(new ActionListener() {
			       public void actionPerformed(ActionEvent e) {
			    	   ((ArtificialPlayer) player2AI).setDifficulty(8);
			    	   frame.remove(difficultyPanel);
			    	   frame.add(mapPanel);
			    	   frame.pack();
			       }
			    });    
		    difficultyPanel.add(hard);
		    
		    final JButton murder = new JButton(
		    		new ImageIcon("DifficultyMurder.png"));
		    murder.addActionListener(new ActionListener() {
			       public void actionPerformed(ActionEvent e) {
			    	   ((ArtificialPlayer) player2AI).setDifficulty(0);
			    	   frame.remove(difficultyPanel);
			    	   frame.add(mapPanel);
			    	   frame.pack();
			       }
			    });    
		    difficultyPanel.add(murder);
		    
		    //Buttons that represent which player won the game
		    final JButton ocamlWin = new JButton(new ImageIcon("WinO.png"));
		    ocamlWin.addActionListener(new ActionListener() {
		       public void actionPerformed(ActionEvent e) {
		    	   System.exit(0);
		       }
		    });
		    ocamlWinPanel.add(ocamlWin);
		    
		    final JButton javaWin = new JButton(new ImageIcon("WinJ.png"));
		    javaWin.addActionListener(new ActionListener() {
		       public void actionPerformed(ActionEvent e) {
		    	   System.exit(0);
		       }
		    });
		    javaWinPanel.add(javaWin);
	      
	      // Put the frame on the screen
		  frame.add(mainPanel);
	      frame.pack();
	      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      frame.setVisible(true);
	      frame.setResizable(false);

	      Cursor newCursor = new Cursor(Cursor.CROSSHAIR_CURSOR);
	      frame.setCursor(newCursor);
	}

	public static void main(String[] args) {
	       SwingUtilities.invokeLater(new Game());
	}

}
