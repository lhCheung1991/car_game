import java.util.List;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Projectile extends Hazard 
{
	public static final double velocity = 1.7;
	
	private Angle flyAngle;
	
	private World world;
	
	private Kart affectedKart;
	
	public Projectile(double x, double y, Angle angle, World w) 
	throws SlickException 
	{
		super(x, y, new Image(Game.ASSETS_PATH + "/items/tomato-projectile.png"));
		// TODO Auto-generated constructor stub
		flyAngle = angle;
		world = w;
	}

	@Override
	public void affect(Kart kart) {
		// TODO Auto-generated method stub
		if (!isHazard() && distTo(kart) < 40)
		{
			isHazard = true;
			affectedKart = kart;
			affectedKart.setSpinRatateTimes(2);
			affectedKart.setAccelerationTimes(0);
			affectedKart.setSpinning(true);
		}
	}

	@Override
	public boolean collides(GameObject gameObject) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void render(Graphics g, Camera camera) {
		// TODO Auto-generated method stub
		if (isHazard())
		{
			if (--spinCountDown <= 0 && affectedKart != null)
			{
				affectedKart.setSpinRatateTimes(1);
				affectedKart.setAccelerationTimes(1);
				affectedKart.setSpinning(false);
				affectedKart = null;
			}
			
			return;
		}
		
		int screen_x = (int) (x - camera.getLeft());
        int screen_y = (int) (y - camera.getTop());
        img.drawCentered(screen_x, screen_y);
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		if (isHazard())
			return;
		
        double amount = velocity;
        // Compute the next position, but don't move there yet
        double next_x = x + flyAngle.getXComponent(amount);
        double next_y = y + flyAngle.getYComponent(amount);
        // If the intended destination is a blocking tile, do not move there
        // (crash) -- instead, set velocity to 0
        if (world.blockingAt((int) next_x, (int) next_y))
        {
        	isHazard = true;
        }
        else
        {
            // Actually move to the intended destination
            x = next_x;
            y = next_y;
        }
	}

}
