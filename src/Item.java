import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public abstract class Item extends GameObject{

	protected boolean pickedUp;
	
	protected boolean isUsed;
	
	public Item(double x, double y, Image img) {
		super(x, y, img);
		// TODO Auto-generated constructor stub
		pickedUp = false;
		isUsed = false;
	}
	
	public void pickup(Player player){
		
		if (player.distTo(this) <= 40)
		{
			player.setItem(this);
			pickedUp = true;
		}
	}
	
	public abstract void use(Player player, World world) throws SlickException;

	public void drawImage(int i, int panel_top, Graphics object) {
		// TODO Auto-generated method stub
		img.draw(i, panel_top);
	}

	public boolean isPickedUp() {
		return pickedUp;
	}
	
	public boolean isUsed() {
		return isUsed;
	}

	@Override
	public void render(Graphics g, Camera camera) 
	{
		// TODO Auto-generated method stub
		if (isPickedUp())
			return;
		
		int screen_x = (int) (x - camera.getLeft());
        int screen_y = (int) (y - camera.getTop());
        img.drawCentered(screen_x, screen_y);
	}
	
}
