import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Tomato extends Item{

	public Tomato(double x, double y) 
	throws SlickException
	{
		super(x, y, new Image(Game.ASSETS_PATH + "/items/tomato.png"));
		// TODO Auto-generated constructor stub
	}

	@Override
	public void use(Player player, World world) 
	throws SlickException
	{
		// TODO Auto-generated method stub
		world.getHazardList().add(new Projectile(
				player.getX() + player.getAngle().getXComponent(80),
				player.getY() + player.getAngle().getYComponent(80),
				Angle.fromRadians(player.getAngle().getRadians()),
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
