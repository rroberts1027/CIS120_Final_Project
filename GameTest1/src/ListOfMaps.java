import java.util.ArrayList;

//A class that holds ArrayLists of Blocks which store block makeups
//of all the game's maps
public class ListOfMaps {
	
	//Sets borders of indestructible maps to a map
	public static ArrayList<Block> setBorder(ArrayList<Block> b){
		for(int i = 0; i < 1000; i += 20){
			b.add(new IBlock(i, 0));
			b.add(new IBlock(i, 500 - 20));
			b.add(new IBlock(i, -20));
			b.add(new IBlock(i, 500));
		}
		for(int i = 20; i < 500 - 20; i += 20){
			b.add(new IBlock(0, i));
			b.add(new IBlock(1000 - 20, i));
			b.add(new IBlock(-20, i));
			b.add(new IBlock(1000, i));
		}
		
		return b;
	}
	 
	public static ArrayList<Block> getReflectMap(){
		ArrayList<Block> map = new ArrayList<Block>();
		
		for(int i = 0; i < 1000; i += 20){
			map.add(new RBlock(i, 0));
			map.add(new RBlock(i, 500 - 20));
			map.add(new IBlock(i, -20));
			map.add(new IBlock(i, 500));
		}
		for(int i = 20; i < 500 - 20; i += 20){
			map.add(new RBlock(0, i));
			map.add(new RBlock(1000 - 20, i));
			map.add(new IBlock(-20, i));
			map.add(new IBlock(1000, i));
		}
		return map;
	}
	
	public static ArrayList<Block> getBounceMap(){
		ArrayList<Block> map = new ArrayList<Block>();
		
		for(int i = 60; i < 480; i += 200){
			for(int j = 80; j < 940; j += 166){
				map.add(new BBlock(j, i));
			}
		}
		
		for(int i = 170; i < 4400; i += 200){
			for(int j = 170; j < 940; j += 166){
				map.add(new BBlock(j, i));
			}
		}
		
		return setBorder(map);
	}

	public static ArrayList<Block> getMiddleSquare(){
		ArrayList<Block> map = new ArrayList<Block>();
		
		for(int i = 500; i < 700; i+=20){
			for(int j = 200; j < 400; j+=20){
				map.add(new DBlock(i, j));
			}
		}
		map.add(new RBlock(500, 460));
		map.add(new RBlock(700, 460));
		map.add(new RBlock(800, 200));
		
		map.add(new GBlock(600, 460));
		map.add(new GBlock(900, 200));
		
		map.add(new BBlock(200, 240));
		map.add(new BBlock(200, 260));
		map.add(new BBlock(200, 280));
		map.add(new BBlock(200, 300));
		
		return setBorder(map);
	}

	public static ArrayList<Block> getCis120(){
		ArrayList<Block> map = new ArrayList<Block>();
		
		//C
		for(int i = 100; i < 200; i+=20){
			map.add(new IBlock(i, 280));
			map.add(new IBlock(i, 460));
		}
		for(int i = 300; i < 460; i += 20){
			map.add(new IBlock(100, i));
		}
		
		//O
		for(int i = 800; i < 900; i+=20){
			map.add(new IBlock(i, 280));
			map.add(new IBlock(i, 460));
		}
		for(int i = 280; i < 480; i += 20){
			map.add(new IBlock(900, i));
			map.add(new IBlock(800, i));
		}
		
		//I
		for(int i = 260; i < 360; i+=20){
			map.add(new IBlock(i, 155));
			map.add(new IBlock(i, 335));
		}
		for(int i = 155; i < 355; i+=20){
			map.add(new IBlock(300, i));
		}
		
		//2
		for(int i = 640; i < 740; i+=20){
			map.add(new IBlock(i, 155));
			map.add(new IBlock(i, 235));
			map.add(new IBlock(i, 335));
		}
		for(int i = 175; i < 235; i+=20){
			map.add(new IBlock(720, i));
		}
		for(int i = 255; i < 335; i+=20){
			map.add(new IBlock(640, i));
		}
		
		//S
		for(int i = 395; i < 495; i+=20){
			map.add(new IBlock(i, 20));
			map.add(new IBlock(i, 100));
			map.add(new IBlock(i, 200));
		}
		for(int i = 40; i < 100; i+=20){
			map.add(new IBlock(395, i));
		}
		for(int i = 120; i < 200; i+=20){
			map.add(new IBlock(475, i));
		}
		
		//1
		for(int i = 505; i < 605; i+=20){
			map.add(new IBlock(i, 200));
		}
		for(int i = 505; i < 555; i+=20){
			map.add(new IBlock(i, 20));
		}
		for(int i = 20; i < 200; i+=20){
			map.add(new IBlock(545, i));
		}

		return setBorder(map);
	}
	
	public static ArrayList<Block> getStonesMap(){
		ArrayList<Block> map = new ArrayList<Block>();
		
		
		for(int i = 60; i < 440; i += 20){
			map.add(new GBlock(480, i));
			map.add(new GBlock(500, i));
		}
		
		for(int i = 100; i < 900; i += 20){
			map.add(new GBlock(i, 240));
		}
		
		map.add(new GBlock(260, 120));
		map.add(new GBlock(280, 140));
		map.add(new GBlock(260, 140));
		map.add(new GBlock(280, 120));
		
		map.add(new GBlock(720, 120));
		map.add(new GBlock(700, 140));
		map.add(new GBlock(720, 140));
		map.add(new GBlock(700, 120));
		
		map.add(new GBlock(720, 360));
		map.add(new GBlock(700, 340));
		map.add(new GBlock(720, 340));
		map.add(new GBlock(700, 360));
		
		map.add(new GBlock(260, 360));
		map.add(new GBlock(280, 340));
		map.add(new GBlock(260, 340));
		map.add(new GBlock(280, 360));
		
		return setBorder(map);
	}
	
	public static ArrayList<Block> getWoodMap(){
		ArrayList<Block> map = new ArrayList<Block>();
		
		for(int i = 20; i < 440; i+=20){
			for(int j = 80; j < 900; j += 160){
				map.add(new DBlock(j, i));
				map.add(new DBlock(j + 20, i));
			}
		}
		for(int i = 80; i < 480; i+=20){
			for(int j = 160; j < 900; j += 160){
				map.add(new DBlock(j, i));
				map.add(new DBlock(j + 20, i));
			}
		}
	
		return setBorder(map);
	}
	
	public static ArrayList<Block> getSnipeMap(){
		ArrayList<Block> map = new ArrayList<Block>();
		
		for(int i = 20; i < 480; i += 20){
			map.add(new GBlock(100, i));
			map.add(new GBlock(880, i));
			map.add(new DBlock(140, i));
			map.add(new DBlock(840, i));
			
		}
		
		map.add(new RBlock(490, 200));
		map.add(new RBlock(490, 400));
		map.add(new RBlock(490, 100));
		map.add(new RBlock(490, 300));
		
		
		return setBorder(map);
	}
	
	public static ArrayList<Block> getCloseMap(){
		ArrayList<Block> map = new ArrayList<Block>();
		
		for(int i = 60; i < 480; i += 20){
			for(int j = 60; j < 320; j += 20){
				map.add(new IBlock(j, i));
				map.add(new IBlock(1000 - j - 20, i));
			}
		}
		
		for(int i = 60; i < 300; i+=20){
			for(int j = 320; j < 440; j += 20){
				map.add(new IBlock(j, i));
				map.add(new IBlock(1000 - j - 20, i));
			}
		}
		
		for(int i = 20; i < 300; i += 20){
			map.add(new IBlock(480, i));
			map.add(new IBlock(500, i));
		}
			
	
		return setBorder(map);
	}
}
