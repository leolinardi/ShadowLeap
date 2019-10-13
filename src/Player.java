/* Shadow Leap Game (2D)
 * Project 1, SWEN20003: Object Oriented Software Development 2018
 * Extended by: Leonardo Linardi, 855915 <llinardi@student.unimelb.edu.au>
 */

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/* 'Player' class for the game.
 * Initialize a player and moves a player to the left, right,
 * up and down in the game.
 */

public class Player extends Sprite {
	// Details of the Player initial position
    private static final float TILE_SIZE = 48;
	private static final float PLAYER_INIT_XCOOR = 512;
	private static final float PLAYER_INIT_YCOOR = 720;
	
	// Creates the player
    public Player() throws SlickException {
    	super("assets/frog.png",PLAYER_INIT_XCOOR,PLAYER_INIT_YCOOR);
    }
    
    // Moves the player according to the keyboard input
    public void move(Input input, int delta) {
    	// Checks the input key pressed
        if (input.isKeyPressed(Input.KEY_LEFT)) {
			setX(getX()-TILE_SIZE);
		}
        if (input.isKeyPressed(Input.KEY_RIGHT)) {
			setX(getX()+TILE_SIZE);
		}
        if (input.isKeyPressed(Input.KEY_UP)) {
			setY(getY()-TILE_SIZE);
		}
        if (input.isKeyPressed(Input.KEY_DOWN)) {
			setY(getY()+TILE_SIZE);
		}
        
		// Checking boundaries, make sure it doesn't go out of bounds
		if (getX() < 0+TILE_SIZE) {
			setX(TILE_SIZE);
		}
		if (getX() > App.SCREEN_WIDTH-TILE_SIZE) {
			setX(App.SCREEN_WIDTH-TILE_SIZE);
		}
		if (getY() < 0+TILE_SIZE) {
			setY(TILE_SIZE);
		}
		if (getY() > App.SCREEN_HEIGHT-TILE_SIZE) {
			setY(App.SCREEN_HEIGHT-TILE_SIZE);
		}
		updateBoundingBox();
    }
    
}