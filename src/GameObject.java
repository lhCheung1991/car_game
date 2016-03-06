import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public abstract class GameObject {

	 /** The image of the GameObject. */
    protected Image img;
    /** The X coordinate of the GameObject (pixels). */
    protected double x;
    /** The Y coordinate of the GameObject (pixels). */
    protected double y;
    
	public GameObject(double x, double y, Image img) {
		super();
		this.img = img;
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
    
    public double distTo(GameObject gameObject)
    {
    	return Math.sqrt((gameObject.getX() - x) * (gameObject.getX() - x) 
    			+ (gameObject.getY() - y) * (gameObject.getY() - y));
    }
    
    public abstract boolean collides(GameObject gameObject);
    
    public abstract void render(Graphics g, Camera camera);
}
