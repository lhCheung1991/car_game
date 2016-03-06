import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class OilCan extends Item{

	public OilCan(double x, double y)
	throws SlickException
	{
		super(x, y, new Image(Game.ASSETS_PATH + "/items/oilcan.png"));
		// TODO Auto-generated constructor stub
	}

	@Override
	public void use(Player player, World world)
	throws SlickException
	{
		// TODO Auto-generated method stub
		world.getHazardList().add(new OilSlick(
				player.getX() - player.getAngle().getXComponent(80),
				player.getY() - player.getAngle().getYComponent(80),
				world
				));
		isUsed = true;
		
	}

	@Override
	public boolean collides(GameObject gameObject) {
		// TODO Auto-generated method stub
		return false;
	}

}
