/** The camera, a rectangle positioned in the world.
 */
public class Camera
{
    // In pixels
	private final int width;
	private final int height;
    private int left;
    private int top;

    // Accessors
    /** The left x coordinate of the camera (pixels). */
    public int getLeft()
    {
        return left;
    }
    /** The right x coordinate of the camera (pixels). */
    public int getRight()
    {
        return left + width;
    }
    /** The top y coordinate of the camera (pixels). */
    public int getTop()
    {
        return top;
    }
    /** The bottom y coordinate of the camera (pixels). */
    public int getBottom()
    {
        return top + height;
    }
    /** The width of the camera viewport (pixels). */
    public int getWidth()
    {
    	return width;
    }
    /** The height of the camera viewport (pixels). */
    public int getHeight()
    {
    	return height;
    }

    /** Creates a new Camera centered around the player.
     * @param width The width of the camera viewport (pixels).
     * @param height The height of the camera viewport (pixels).
     * @param player The player, to get the player's location.
     */
    public Camera(int width, int height, Kart player)
    {
    	this.width = width;
    	this.height = height;
        follow(player);
    }

    /** Move the camera such that the given player is centered.
     * @param player The player, to get the player's location.
     */
    public void follow(Kart player)
    {
    	int left = (int) player.getX() - (width / 2);
    	int top = (int) player.getY() - (height / 2);
        moveTo(left, top);
    }

    /** Update the camera's x and y coordinates.
     * @param left New left x coordinate of the camera (pixels).
     * @param top New top y coordinate of the camera (pixels).
     */
    public void moveTo(int left, int top)
    {
        this.left = left;
        this.top = top;
    }
}
