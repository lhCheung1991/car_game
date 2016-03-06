
import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

/** Represents the entire game world.
 * (Designed to be instantiated just once for the whole game).
 */
public class World
{
    /** The world map (two dimensional grid of tiles).
     * The concept of tiles is a private implementation detail to World. All
     * public methods deal with pixels, not tiles.
     */
    private TiledMap map;

    /** The player's kart. */
    private Player player;

    private List<AIPlayer> aiPlayerList;
    
    private List<Item> itemList;
    
    private Panel panel;
    
    private List<Hazard> hazardList;
    
    /** Create a new World object. */
    public World()
    throws SlickException
    {
        this.map = new TiledMap(Game.ASSETS_PATH + "/map.tmx",
                                Game.ASSETS_PATH);
        this.player = new Player(1332, 13086, Angle.fromDegrees(0));
        
        aiPlayerList = new ArrayList<AIPlayer>();
        aiPlayerList.add(new Elephant(1260, 13086, Angle.fromDegrees(0)));
        aiPlayerList.add(new Dog(1404, 13086, Angle.fromDegrees(0)));
        aiPlayerList.add(new Octopus(1476, 13086, Angle.fromDegrees(0)));
        
        itemList = new ArrayList<Item>();
        itemList.add(new OilCan(1350, 12438));
        itemList.add(new OilCan(864,  7614));
        itemList.add(new OilCan(1962, 6498));
        itemList.add(new OilCan(1314, 3690));
        itemList.add(new Tomato(990,  11610));
        itemList.add(new Tomato(1206, 5130));
        itemList.add(new Tomato(1206, 3690));
        itemList.add(new Tomato(1422, 2322));
        itemList.add(new Boost(990,  10242));
        itemList.add(new Boost(1818, 6534));
        itemList.add(new Boost(990,  4302));
        itemList.add(new Boost(1926, 3510));
        
        panel = new Panel();
        
        hazardList = new ArrayList<Hazard>();
    }

    /** Get the width of the game world in pixels. */
    public int getWidth()
    {
        return this.map.getWidth() * this.map.getTileWidth();
    }

    /** Get the height of the game world in pixels. */
    public int getHeight()
    {
        return this.map.getHeight() * this.map.getTileHeight();
    }

    /** Update the game state for a frame.
     * @param rotate_dir The player's direction of rotation
     *      (-1 for anti-clockwise, 1 for clockwise, or 0).
     * @param move_dir The player's movement in the car's axis (-1, 0 or 1).
     * @param delta Time passed since last frame (milliseconds).
     */
    public void update(double rotate_dir, double move_dir, boolean use_item)
    throws SlickException    // update player and camera
    {
    	
    	if (use_item && this.player.getItem() != null)
    	{
    		this.player.getItem().use(player, this);    // call the Item.use() held by player once
    		this.player.setItem(null);
    	}
    	
    	for (int i = 0; i < itemList.size(); i++)
        {
        	if (!itemList.get(i).isPickedUp())
        		itemList.get(i).pickup(player);
        }
    	
        // Rotate and move the player by rotate_dir and move_dir
        this.player.move(rotate_dir, move_dir, this);
        
        for (int i = 0; i < aiPlayerList.size(); i++)
        {
        	aiPlayerList.get(i).followWayPoints(this);
        }
        
        for (int i = 0; i < hazardList.size(); i++)
        {
        	for (int j = 0; j < aiPlayerList.size(); j++)
        	{
        		hazardList.get(i).affect(aiPlayerList.get(j));
        	}
        	hazardList.get(i).affect(player);
        	hazardList.get(i).move();
        }
        
        
        
    }

    /** Render the entire screen, so it reflects the current game state.
     * @param g The Slick graphics object, used for drawing.
     */
    public void render(Graphics g)
    throws SlickException
    {
        // Calculate the top-left corner of the screen, in pixels, relative to
        // the top-left corner of the map -- center the camera around the
        // player
        int cam_x = (int) this.player.getX() - (Game.SCREENWIDTH / 2);
        int cam_y = (int) this.player.getY() - (Game.SCREENHEIGHT / 2);

        // Calculate the camera location (in tiles) and offset (in pixels)
        int cam_tile_x = cam_x / this.map.getTileWidth();
        int cam_tile_y = cam_y / this.map.getTileHeight();
        int cam_offset_x = cam_x % this.map.getTileWidth();
        int cam_offset_y = cam_y % this.map.getTileHeight();

        // Render 24x18 tiles of the map to the screen, starting from the
        // camera location in tiles (rounded down). Begin drawing at a
        // negative offset relative to the screen, to ensure smooth scrolling.
        int screen_tilew = Game.SCREENWIDTH / this.map.getTileWidth() + 2;
        int screen_tileh = Game.SCREENHEIGHT / this.map.getTileHeight() + 2;
        this.map.render(-cam_offset_x, -cam_offset_y, cam_tile_x, cam_tile_y,
            screen_tilew, screen_tileh);

        // Render the player
        Camera camera = new Camera(Game.SCREENWIDTH, Game.SCREENHEIGHT, player);
        for (int i = 0; i < itemList.size(); i++)
        {
        	itemList.get(i).render(g, camera);
        }
        this.player.render(g, camera);
        for (int i = 0; i < aiPlayerList.size(); i++)
        {
        	aiPlayerList.get(i).render(g, camera);
        }
        
        panel.render(g, sort(player, aiPlayerList), player.getItem());
        
        for (int i = 0; i < hazardList.size(); i++)
        {
        	hazardList.get(i).render(g, camera);
        }
    }

    /** Get the friction coefficient of a map location.
     * @param x Map tile x coordinate (in pixels).
     * @param y Map tile y coordinate (in pixels).
     * @return Friction coefficient at that location.
     */
    public double frictionAt(int x, int y)
    {
        int tile_x = x / this.map.getTileWidth();
        int tile_y = y / this.map.getTileHeight();
        int tileid = this.map.getTileId(tile_x, tile_y, 0);
        String friction = this.map.getTileProperty(tileid, "friction", null);
        return Double.parseDouble(friction);
    }

    /** Determines whether a particular map location blocks movement.
     * @param x Map tile x coordinate (in pixels).
     * @param y Map tile y coordinate (in pixels).
     * @return true if the tile at that location blocks movement.
     */
    public boolean blockingAt(int x, int y)
    {
        return frictionAt(x, y) >= 1;
    }

	public Player getPlayer() {
		return player;
	}

	public List<AIPlayer> getAiPlayerList() {
		return aiPlayerList;
	}

	public List<Hazard> getHazardList() {
		return hazardList;
	}
	
	private int sort(Player player, List<AIPlayer> aiPlayerList)
	{
		int ans = 1;
		for (int i = 0; i < aiPlayerList.size(); i++)
		{
			if (player.getY() > aiPlayerList.get(i).getY())
				ans++;
		}
		return ans;
	}
	
}
