import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public abstract class Kart extends GameObject{
	
    protected static final double ROTATE_SPEED = 0.004;
    protected static final double ACCELERATION = 0.0005;
    
    protected static int startRanking;
    protected int ranking;
    protected boolean isFinished;

    /** The angle the player is currently facing.
     * Note: This is in neither degrees nor radians -- the Angle class allows
     * angles to be manipulated without worrying about units, and the angle
     * value can be extracted in either degrees or radians.
     */
    protected Angle angle;
    /** The player's current velocity (px/ms). */
    protected double velocity;
    
    protected double accelerationTimes;
    
    protected double spinRatateTimes;
    
    protected boolean isSpinning;

    /** Creates a new Player.
     * @param x The player's initial X location (pixels).
     * @param y The monster's initial Y location (pixels).
     * @param angle The player's initial angle.
     */
    public Kart(double x, double y, Image image, Angle angle)
    throws SlickException
    {
    	super(x, y, image);
        this.angle = angle;
        this.velocity = 0;
        this.accelerationTimes = 1;
        this.spinRatateTimes = 1;
        this.isSpinning = false;
        startRanking = 0;
        ranking = 0;
        isFinished = false;
    }

    /** The angle the player is facing. */
    public Angle getAngle()
    {
        return angle;
    }

    /** The player's current velocity, in the direction the player is facing
     * (px/ms).
     */
    public double getVelocity()
    {
        return velocity;
    }

    public abstract void move(double rotate_dir, double move_dir, World world);

	public double getAccelerationTimes() {
		return accelerationTimes;
	}

	public void setAccelerationTimes(double accelerationTimes) {
		this.accelerationTimes = accelerationTimes;
	}
	
	

	public void setSpinRatateTimes(double spinRatateTimes) {
		this.spinRatateTimes = spinRatateTimes;
	}

	@Override
	public boolean collides(GameObject gameObject) {
		// TODO Auto-generated method stub
		
		if (this.distTo(gameObject) <= 40 )
			return true;
		else
			return false;
	}

	public boolean isSpinning() {
		return isSpinning;
	}

	public void setSpinning(boolean isSpinning) {
		this.isSpinning = isSpinning;
	}

	public int getRanking() {
		return ranking;
	}

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}
	
	
	
}
