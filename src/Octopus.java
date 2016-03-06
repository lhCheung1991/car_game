import java.util.List;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Octopus extends AIPlayer{

	public Octopus(double x, double y, Angle angle)
			throws SlickException {
		super(x, y, new Image(Game.ASSETS_PATH + "/karts/octopus.png"), angle);
		// TODO Auto-generated constructor stub
		wayPoints = new WayPoints();
	}

	@Override
	public void followWayPoints(World world) {
		// TODO Auto-generated method stub
		
		Player player = world.getPlayer();
		
		double deltaX = wayPoints.getCurrentX() - x;
		double deltaY = -(wayPoints.getCurrentY() - y);
		if (!wayPoints.isFinished() && deltaX * deltaX + deltaY * deltaY <= 62500)
		{
			wayPoints.chargeToNext();
		}
		else
		{
			if (y < 1026 && !isFinished)
			{
				ranking += (++startRanking);
				isFinished = true;
			}
		}
			
		
//		When he’s between 100 and 250 pixels away from Donkey, 
//		he drives straight towards the player!
		
		double targetY;
		if (100 < this.distTo(player) &&  this.distTo(player) < 250)
		{
			targetY = player.getY();
			deltaX = player.getX() - x;
			deltaY = -(player.getY() - y);
		}
		else
		{
			targetY = wayPoints.getCurrentY();
			deltaX = wayPoints.getCurrentX() - x;
			deltaY = -(wayPoints.getCurrentY() - y);
		}
		
		
		Angle bigAngle = Angle.fromRadians(Math.atan(deltaX / deltaY));
		
		/****************judge the angle, to the next waypoint************************/
		if (y >= targetY)
		{
			if (0 < bigAngle.getRadians() && bigAngle.getRadians() < Math.PI / 2)
			{
				if (-Math.PI + bigAngle.getRadians() < angle.getRadians() && angle.getRadians() < bigAngle.getRadians())
					move(1, 1, world);
				else
					move(-1, 1, world);
			}
			else if (-Math.PI / 2 < bigAngle.getRadians() && bigAngle.getRadians() < 0)
			{
				if (bigAngle.getRadians() < angle.getRadians() && angle.getRadians() < Math.PI + bigAngle.getRadians())
					move(-1, 1, world);
				else
					move(1, 1, world);
			}
		}
		else
		{
			bigAngle = Angle.fromRadians(bigAngle.getRadians() + Math.PI);
			if (Math.PI / 2 < bigAngle.getRadians() && bigAngle.getRadians() < Math.PI)
			{
				if (bigAngle.getRadians() - Math.PI < angle.getRadians() && angle.getRadians() < bigAngle.getRadians())
					move(1, 1, world);
				else
					move(-1, 1, world);
			}
			else if (-Math.PI < bigAngle.getRadians() && bigAngle.getRadians() < -Math.PI / 2)
			{
				if (bigAngle.getRadians() < angle.getRadians() && angle.getRadians() < bigAngle.getRadians() + Math.PI)
					move(-1, 1, world);
				else
					move(1, 1, world);
			}
		}
		/****************judge the angle, to the next waypoint************************/
	}

	@Override
	public void move(double rotate_dir, double move_dir, World world) {
		// TODO Auto-generated method stub
		if (isSpinning())
        {
        	velocity = 0;
        	rotate_dir = 1;
        	move_dir = 0;
        }
		int delta = 1;
        // Modify the player's angle
        Angle rotateamount = new Angle(ROTATE_SPEED * rotate_dir * delta * spinRatateTimes);
        angle = angle.add(rotateamount);
        // Determine the friction of the current location
        double friction = world.frictionAt((int) x, (int) y);
        // Modify the player's velocity. First, increase due to acceleration.
        velocity += ACCELERATION * move_dir * delta * accelerationTimes;
        // Then, reduce due to friction (this has the effect of creating a
        // maximum velocity for a given coefficient of friction and
        // acceleration)
        velocity *= Math.pow(1 - friction, delta);

        // Modify the position, based on velocity
        // Calculate the amount to move in each direction
        double amount = velocity * delta;
        // Compute the next position, but don't move there yet
        double next_x = x + angle.getXComponent(amount);
        double next_y = y + angle.getYComponent(amount);
        // If the intended destination is a blocking tile, do not move there
        // (crash) -- instead, set velocity to 0
        if (world.blockingAt((int) next_x, (int) next_y))
        {
            velocity = 0;
        }
        else
        {
            // Actually move to the intended destination
            x = next_x;
            y = next_y;
            List<AIPlayer> aiPlayerList = world.getAiPlayerList();
            for (int i = 0; i < aiPlayerList.size(); i++)
            {
            	if (this != aiPlayerList.get(i) && this.collides(aiPlayerList.get(i)))
            		velocity *= 0.25;
            }
            if (this.collides(world.getPlayer()))
            	velocity *= 0.25;
        }
	}

	@Override
	public void render(Graphics g, Camera camera) {
		// TODO Auto-generated method stub
		// Calculate the player's on-screen location from the camera
        int screen_x = (int) (x - camera.getLeft());
        int screen_y = (int) (y - camera.getTop());
        img.setRotation((float) angle.getDegrees());
        img.drawCentered(screen_x, screen_y);
        g.drawLine(screen_x, screen_y, wayPoints.getCurrentX() - camera.getLeft(), wayPoints.getCurrentY() - camera.getTop());
        if (isFinished)
        {
        	g.drawString("Octopus Ranking : " + ranking, 50, 70);
        }
	}

}
