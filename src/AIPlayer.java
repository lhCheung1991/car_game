import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public abstract class AIPlayer extends Kart{
	
	protected WayPoints wayPoints;
	
	protected int stopCountdown;
	
	public AIPlayer(double x, double y, Image image, Angle angle)
			throws SlickException 
	{
		super(x, y, image, angle);
		// TODO Auto-generated constructor stub
		stopCountdown = 4000;
	}

	public abstract void followWayPoints(World world);

}
