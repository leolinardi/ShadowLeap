/* Shadow Leap Game (2D)
 * Project 1, SWEN20003: Object Oriented Software Development 2018
 * Extended by: Leonardo Linardi, 855915 <llinardi@student.unimelb.edu.au>
 */

import org.newdawn.slick.SlickException;

/* 'Obstacle' class for the game.
 * Initialize a single obstacle and moves the obstacles by default
 * or automatically.
 */

public class Obstacle extends Sprite {
    private static final float TILE_SIZE = 48;
	private static final float BUS_SPEED = 0.15f;
	
	// Creates the different type of obstacles, relative to its initial positions
	public Obstacle(String imageSrc, float x, float y) throws SlickException {
    	super(imageSrc, x, y);
    }
	
	// Moves the obstacle to the left of the world
	public void moveLeft(int delta) {
		setX(getX()-delta*BUS_SPEED);
		
		// Makes the obstacle reappears on the right end
		if (getX() < -(TILE_SIZE/2)) {
			setX(App.SCREEN_WIDTH+(TILE_SIZE/2));
		}
		updateBoundingBox();
	}
	
	// Moves the obstacle to the right of the world
	public void moveRight(int delta) {
		setX(getX()+delta*BUS_SPEED);
		
		// Makes the obstacle reappears on the left end
		if (getX() > App.SCREEN_WIDTH+(TILE_SIZE/2)) {
			setX(-(TILE_SIZE/2));
		}
		updateBoundingBox();
	}
	
}
