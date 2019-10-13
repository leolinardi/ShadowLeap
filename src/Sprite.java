/* Shadow Leap Game (2D)
 * Project 1, SWEN20003: Object Oriented Software Development 2018
 * Extended by: Leonardo Linardi, 855915 <llinardi@student.unimelb.edu.au>
 */

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Image;
import utilities.BoundingBox;

/* 'Sprite' class for the game.
 * Initialize a single sprite, updates the sprite (if needed),
 * renders the image of a sprite and checks if 2 sprites makes a contact.
 */

public class Sprite {
	
	private Image image;
	private float x;
	private float y;
	private BoundingBox boundingBox;

	// Stores the image and the current coordinates of the sprite
	public Sprite(String imageSrc, float x, float y) throws SlickException {
		this.x=x;
		this.y=y;
		this.image = new Image(imageSrc);
		this.boundingBox = new BoundingBox(image, x, y);
		
	}
	
	// Updates the sprite (ie. Tiles) according to the input
	// All updates regarding the Player and Obstacles are made in their respective classes
	// NB. For now, there are no other sprites that requires update. Assuming it does, when extended.
	public void update(Input input, int delta) {
	}
	
	// Renders the image for a single sprite
	public void render() {
		image.drawCentered(this.x, this.y);
	}
	
	// Actions to be made if 2 specific sprites contacts each other
	// NB. For now, exits the game if contacts were made. Assuming more outcomes are to come, when extended. 
	public void contactSprite(Sprite other) {
		if (this.boundingBox.intersects(other.boundingBox)) {
			System.exit(0);
		}
	}
	
	public float getX() {
        return this.x;
    }
    public float getY() {
        return this.y;
    }
    public void setX(float x) {
        this.x=x;
    }    
    public void setY(float y) {
        this.y=y;
    }
    public void updateBoundingBox() {
    	this.boundingBox.setX(this.x);
    	this.boundingBox.setY(this.y);
    }
}
