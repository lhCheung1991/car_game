import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Boost extends Item{

	private int countDown;
	
	private Player boostPlayer;
	
	public Boost(double x, double y) 
	throws SlickException
	{
		super(x, y, new Image(Game.ASSETS_PATH + "/items/boost.png"));
		// TODO Auto-generated constructor stub
		countDown = 3000;
	}

	@Override
	public void use(Player player, World world) {
		// TODO Auto-generated method stub
		if (!isUsed())
		{
			player.setAccelerationTimes(1.6);
			isUsed = true;
			boostPlayer = player;
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
		super.render(g, camera);
		
		if (isPickedUp() && isUsed() && countDown >= 0)
		{
			countDown--;
			g.drawString("Boost Count Down : " + countDown + " ms", 500, 100);
		}
		else
		{
			if (boostPlayer != null)
			{
				boostPlayer.setAccelerationTimes(1);
				boostPlayer = null;
			}
		}
		
	}
	
	
}
