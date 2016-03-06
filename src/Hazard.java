import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public abstract class Hazard extends GameObject{

	protected boolean isHazard;
	protected int spinCountDown;
	
	public Hazard(double x, double y, Image img)
	throws SlickException
	{
		super(x, y, img);
		// TODO Auto-generated constructor stub
		isHazard = false;
		spinCountDown = 700;    // 0.7s
	}
	
	public abstract void affect(Kart kart);
	
	public abstract void move();

	public boolean isHazard() 
	{
		return isHazard;
	}
}
