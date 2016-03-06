import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Input;

/** Main class for the Shadow Kart Game engine.
 * Handles initialisation, input and rendering.
 */
public class Game extends BasicGame
{
    /** Location of the "assets" directory. */
    public static final String ASSETS_PATH = "assets";

    /** The game state. */
    private World world;

    /** Screen width, in pixels. */
    public static final int SCREENWIDTH = 800;
    /** Screen height, in pixels. */
    public static final int SCREENHEIGHT = 600;

    /** Create a new Game object. */
    public Game()
    {
        super("Shadow Kart");
    }

    /** Initialise the game state.
     * @param gc The Slick game container object.
     */
    @Override
    public void init(GameContainer gc)
    throws SlickException
    {
        world = new World();
    }

    /** Update the game state for a frame.
     * @param gc The Slick game container object.
     * @param delta Time passed since last frame (milliseconds).
     */
    @Override
    public void update(GameContainer gc, int delta)    // called once per delta ms
    throws SlickException
    {
        // Get data about the current input (keyboard state).
        Input input = gc.getInput();

        // Update the player's rotation and position based on key presses.
        double rotate_dir = 0;
        double move_dir = 0;
        if (input.isKeyDown(Input.KEY_DOWN) || input.isKeyDown(Input.KEY_S))
            move_dir -= 1;
        if (input.isKeyDown(Input.KEY_UP) || input.isKeyDown(Input.KEY_W))
            move_dir += 1;
        if (input.isKeyDown(Input.KEY_LEFT) || input.isKeyDown(Input.KEY_A))
            rotate_dir -= 1;
        if (input.isKeyDown(Input.KEY_RIGHT) || input.isKeyDown(Input.KEY_D))
            rotate_dir += 1;
        boolean use_item = input.isKeyDown(Input.KEY_Z) ||
            input.isKeyDown(Input.KEY_Z);

        // Let World.update decide what to do with this data.
        for (int i=0; i< delta; i++)    
            world.update(rotate_dir, move_dir, use_item);
        
    }

    /** Render the entire screen, so it reflects the current game state.
     * @param gc The Slick game container object.
     * @param g The Slick graphics object, used for drawing.
     */
    @Override
    public void render(GameContainer gc, Graphics g)
    throws SlickException
    {
        // Let World.render handle the rendering.
        world.render(g);
    }

    /** Start-up method. Creates the game and runs it.
     * @param args Command-line arguments (ignored).
     */
    public static void main(String[] args)
    throws SlickException
    {
        AppGameContainer app = new AppGameContainer(new Game());
        // setShowFPS(true), to show frames-per-second.
        app.setShowFPS(true);
        app.setDisplayMode(SCREENWIDTH, SCREENHEIGHT, false);
        app.start();
    }
}
