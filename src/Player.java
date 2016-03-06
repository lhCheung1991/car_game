import java.util.List;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/** The player's kart (Donkey).
 */
public class Player extends Kart
{
	
	private Item item;

    /** Creates a new Player.
     * @param x The player's initial X location (pixels).
     * @param y The monster's initial Y location (pixels).
     * @param angle The player's initial angle.
     */
    public Player(double x, double y, Angle angle)
    throws SlickException
    {
    	super(x, y, new Image(Game.ASSETS_PATH + "/karts/donkey.png"), angle);
     
    }
    
    /** Update the player for a frame.
     * Adjusts the player's angle and velocity based on input, and updates the
     * player's position. Prevents the player from entering a blocking tile.
     * @param rotate_dir The player's direction of rotation
     *      (-1 for anti-clockwise, 1 for clockwise, or 0).
     * @param move_dir The player's movement in the car's axis (-1, 0 or 1).
     * @param delta Time passed since last frame (milliseconds).
     * @param world The world the player is on (to get friction and blocking).
     */
    public void move(double rotate_dir, double move_dir, World world)
    {
    	
    	if (isSpinning())
        {
        	velocity = 0;
        	rotate_dir = 1;
        	move_dir = 0;
        }
    	
    	if (y < 1026 && !isFinished)
		{
			ranking += (++startRanking);
			isFinished = true;
		}
    	
    	int delta = 1;
        // Modify the player's angle
        Angle rotateamount = new Angle(ROTATE_SPEED * rotate_dir * delta * spinRatateTimes);
        angle = angle.add(rotateamount);
        // Determine the friction of the current location
        double friction = world.frictionAt((int) x, (int) y);
        // Modify the player's velocity. First, increase due to acceleration.
        velocity += ACCELERATION * move_dir * delta * accelerationTimes;
        // Then, reduce due to friction (this has the effect of creating a
        // maximum velocity for a given coefficient of friction and
        // acceleration)
        velocity *= Math.pow(1 - friction, delta);

        
        // Modify the position, based on velocity
        // Calculate the amount to move in each direction
        double amount = velocity * delta;
        // Compute the next position, but don't move there yet
        double next_x = x + angle.getXComponent(amount);
        double next_y = y + angle.getYComponent(amount);
        // If the intended destination is a blocking tile, do not move there
        // (crash) -- instead, set velocity to 0
        if (world.blockingAt((int) next_x, (int) next_y))
        {
            velocity = 0;
        }
        else
        {
            // Actually move to the intended destination
            x = next_x;
            y = next_y;
            
            List<AIPlayer> aiPlayerList = world.getAiPlayerList();
            for (int i = 0; i < aiPlayerList.size(); i++)
            {
            	if (this.collides(aiPlayerList.get(i)))
            	{
            		velocity *= 0.75;
            		break;
            	}
            }
        }
    }

    /** Draw the player to the screen at the correct place.
     * @param g The current Graphics context.
     * @param camera The camera to draw relative to.
     */
    public void render(Graphics g, Camera camera)
    {
        // Calculate the player's on-screen location from the camera
        int screen_x = (int) (x - camera.getLeft());
        int screen_y = (int) (y - camera.getTop());
        img.setRotation((float) angle.getDegrees());
        img.drawCentered(screen_x, screen_y);
        g.drawString("Player Acceleration : " + accelerationTimes, 500, 50);
        g.drawString("Player Rotate Ratio : " + spinRatateTimes, 500, 30);
        if (isFinished)
        {
        	g.drawString("Player Ranking : " + ranking, 50, 30);
        }
    }

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
	
	
}
