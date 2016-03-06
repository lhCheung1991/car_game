import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class OilSlick extends Hazard 
{

	private World world;
	
	private Kart affectedKart;
	
	public OilSlick(double x, double y,  World w) 
	throws SlickException 
	{
		super(x, y, new Image(Game.ASSETS_PATH + "/items/oilslick.png"));
		// TODO Auto-generated constructor stub
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
	public void move() {
		// TODO Auto-generated method stub
		
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

}
