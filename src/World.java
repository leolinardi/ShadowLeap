/* Shadow Leap Game (2D)
 * Project 1, SWEN20003: Object Oriented Software Development 2018
 * Extended by: Leonardo Linardi, 855915 <llinardi@student.unimelb.edu.au>
 */

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/* 'World' class, initialize all of the sprites representing the game. 
 * Renders all of the images and updates the game statuses. Includes:
 * 1 Player(s)  : Frog
 * 1 Obstacle(s): Bus
 * 2 Tile(s)    : Grass, Water
 * Assuming that different types of player, obstacles and tiles are to be added, when extended.
 */

public class World {
	// Details of the tiles building up the game
	private static final float TILE_SIZE = 48;
	private static final float TILE_INIT_XCOOR = 0;
	private static final int TOTAL_TILE_PER_ROW = (int)(Math.ceil(App.SCREEN_WIDTH/TILE_SIZE));
	
	// Sets the initial positions of the Tiles (type: Grass)
	private static final float[] GRASS_ROWS_YCOOR = {672, 384};
	private static final int GRASS_TOTAL_ROW = 2;
	
	// Sets the initial positions of the Tiles (type: Water)
	private static final float WATER_FROM_YCOOR = 336;
	private static final float WATER_TO_YCOOR = 48;
	private static final int WATER_TOTAL_ROW = (int)((WATER_FROM_YCOOR-WATER_TO_YCOOR)/WATER_TO_YCOOR);
	
	// Sets the initial positions of the Obstacle 
	// type: Bus
	private static final float[] BUS_ROWS_YCOOR = {432, 480, 528, 576, 624};
	private static final float[] BUS_ROWS_OFF = {48, 0, 64, 128, 250};
	private static final float[] BUS_SEP_DISTS = {6.5f, 5, 12, 5, 6.5f};
	private static final int BUS_TOTAL_ROW = 5;
	
	private static final int OBSTACLE_TOTAL_ROW = BUS_TOTAL_ROW;
	
	// Initializes some variables and allocates memory
	private Player player;
	private Sprite[][] grass = new Sprite[GRASS_TOTAL_ROW][TOTAL_TILE_PER_ROW];
	private Sprite[][] water = new Sprite[WATER_TOTAL_ROW][TOTAL_TILE_PER_ROW];
	private Obstacle[][] obstacle = new Obstacle[OBSTACLE_TOTAL_ROW][];

	// Perform initialization logic
	public World() throws SlickException {
		player = new Player();
		
		// Initialize rows of Tiles (type: Grass)
		for (int i=0; i<GRASS_TOTAL_ROW; i++) {
			for (int j=0; j<TOTAL_TILE_PER_ROW; j++) {
				grass[i][j] = new Sprite("assets/grass.png", TILE_INIT_XCOOR+(j*TILE_SIZE), GRASS_ROWS_YCOOR[i]);
			}
		}
			
		// Initialize rows of Tiles (type: Water)
		for (int i=0; i<WATER_TOTAL_ROW; i++) {
			for (int j=0; j<TOTAL_TILE_PER_ROW; j++) {
				water[i][j] = new Sprite("assets/water.png", TILE_INIT_XCOOR+(j*TILE_SIZE), WATER_FROM_YCOOR-(i*TILE_SIZE));
			}
		}
		
		// Sets the number of Obstacles per row
		for (int i=0; i<OBSTACLE_TOTAL_ROW; i++) {
			// Obstacle type: Bus
			if (0<=i && i<=BUS_TOTAL_ROW) {
				obstacle[i] = new Obstacle[(int)Math.ceil((App.SCREEN_WIDTH+TILE_SIZE)/(BUS_SEP_DISTS[i]*TILE_SIZE))];
			}
			// Obstacle type: Others (if present)
		}
		
		// Initialize rows of Obstacles
		for (int i=0; i<OBSTACLE_TOTAL_ROW; i++) {
			// Obstacle type: Bus
			if (0<=i && i<=BUS_TOTAL_ROW) {
				for (int j=0; j<obstacle[i].length; j++) {
					obstacle[i][j] = new Obstacle("assets/bus.png", BUS_ROWS_OFF[i]+(j*BUS_SEP_DISTS[i]*TILE_SIZE), BUS_ROWS_YCOOR[i]);
				}
			}
			// Obstacle type: Others (if present)
		}
	}
	
	// Updates all of the sprites in the game
	public void update(Input input, int delta) {
		player.move(input, delta);
		
		// Update the positions of the obstacles
		for (int i=0; i<OBSTACLE_TOTAL_ROW; i++) {
			for (int j=0; j<obstacle[i].length; j++) {
				
				// Checks if the player hits any obstacles
				player.contactSprite(obstacle[i][j]);
				
				// Move the obstacles accordingly as specified
				if (i%2!=0) {
					obstacle[i][j].moveLeft(delta);
				} else {
					obstacle[i][j].moveRight(delta);
				}
			}
		}
		
		// Checks if the player hits the water
		for (int i=0; i<WATER_TOTAL_ROW; i++) {
			for (int j=0; j<TOTAL_TILE_PER_ROW; j++) {
				player.contactSprite(water[i][j]);
			}
		}
	}
	
	// Draws all of the sprites in the game
	public void render(Graphics g) {
		// Renders rows of grass
		for (int i=0; i<GRASS_TOTAL_ROW; i++) {
			for (int j=0; j<TOTAL_TILE_PER_ROW; j++) {
			grass[i][j].render();
			}
		}
		// Renders rows of water
		for (int i=0; i<WATER_TOTAL_ROW; i++) {
			for (int j=0; j<TOTAL_TILE_PER_ROW; j++) {
				water[i][j].render();
			}
		}
		// Render rows of Obstacles
		for (int i=0; i<OBSTACLE_TOTAL_ROW; i++) {
			for (int j=0; j<obstacle[i].length; j++) {
				obstacle[i][j].render();
			}
		}
		player.render();
	}
}
