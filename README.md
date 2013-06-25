These are the files for my CIS120 (Programming Datastructures and Techniques I) final project.

The open-ended assignment was to create a game. I decided to combine the movement of a platformer with the chaos of a Bullet-Hell Shoot 'Em Up-style game. The idea is that the player cannot move on thier own, but must navigate using the recoil of the shots they fire off. Players thus must strategize in real time, as firing toward the opponent knocks the player away, and approaching the opponent leaves one open to attack.

Player One, the cup of coffee, begins in the lower right corner and uses the arroy keys to fire north, south, east and west. Player Two, starting in the lower left corner as a camel, uses the w, s, a, and d keys. The buttons correspond to the direction that the player will fire a bullet: Player one would press the left arrow to fire a bullet to the left, and the recoil from the shot would propel the player to the right. Player health can be seen in the upper corner corresponding to the side which the respective player started on. Character designs were based on the two programming languages covered in the course: Java and OCaml. Players may press P at any time to pause the game.

Players may also opt for a single-player variation of the game, which places the player against an AI set to one of four difficulties, determined by the opponent's competitiveness.

Several types of terrain were created for the games levels: 
Regular blocks stop player and bullet movement. 
Wooden blocks also block player and bullet movement, but detereorate as they tke bullets until they are destroyed. Mirror blocks stop player movement but reverse the direction of bullets.
Bounce Blocks stop bullet movement and knock the player away on collision.
Glass blocks stop player movement but allow bullets to pass through.

Eight levels were implemented in the final version of the game:
The Game Is Afoot is a standard level with regular block walls and no obstacles.
Wood You Kindly is a snaking course composed of wood blocks, in which players must choose to navigate quickly around the  
  level's walls, or brute-force their way to the opponent.
Should Not Throw Stones is composed of glass blocks, in which players must navigate around the center obstacles but
  bullets will pass through.
Mirror Mirror is similar to The Game Is afoot, but with mirror block walls instead of regular block ones. Players must
  be careful, as their own bullets may come back to damage them, and bullets that miss their target will remain in play
  bouncing off the walls until they collide with a player.
CIS120 contains the title's characters as obstacles composed of refular blocks.
Bounce Trounce intersperses bounce blocks throughout the player that make predicting player positions difficult.
Be Polite Be Efficient confines players to a narrow strip on either side of the level with glass blocks and a protective  
  layer of wood blocks. Timing and aim are critical to sniping out opponents.
Bigger on the Inside leads both players through narrow tunnels into a much smaller arena, causing quick and frantic
  matches with little room for error.
  
