import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/** The status panel.
 * Renders itself to a fixed position in the bottom-right corner of the
 * screen, displaying the player's race ranking and item. (See the render()
 * method.)
 */
public class Panel
{
    /** Image for the panel background. */
    private Image panel;

    /** Creates a new Panel. */
    public Panel() throws SlickException
    {
        panel = new Image(Game.ASSETS_PATH + "/panel.png");
    }

    /** Turn a cardinal number into an ordinal.
     * For example, takes a ranking such as 2, and returns a String "2nd".
     * Note: Won't work for numbers greater than 20.
     */
    public static String ordinal(int ranking)
    {
        String rank_string = Integer.toString(ranking);
        switch (ranking)
        {
            case 1:
                rank_string += "st";
                break;
            case 2:
                rank_string += "nd";
                break;
            case 3:
                rank_string += "rd";
                break;
            default:
                rank_string += "th";
        }
        return rank_string;
    }

    /** Renders the status panel for the player.
     * @param g The current Slick graphics context.
     * @param ranking The player's current position in the race
     *  (1 = 1st, 2 = 2nd, etc).
     * @param item The player's currently-held item, or null.
     */
    public void render(Graphics g, int ranking, Item item)
    {
        // Variables for layout
        int panel_left;             // Left x coordinate of panel
        int panel_top;              // Top y coordinate of panel

        panel_left = Game.SCREENWIDTH - 86;
        panel_top = Game.SCREENHEIGHT - 88;

        // Panel background image
        panel.draw(panel_left, panel_top);

        // Display the player's ranking
        g.drawString(ordinal(ranking), panel_left + 14, panel_top + 43);

        // Display the player's current item, if any
        if (item != null)
            item.drawImage(panel_left + 32, panel_top, null);
    }
}
