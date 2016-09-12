import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Color;
import javax.swing.JComponent;
/**
 * The brick class allows you to create individual brick objects for brickbreaker
 * 
 * @author David Lovitz
 * @version December 18, 2013
 */
public class Brick
{
    private int brickX;
    private int brickY;
    private int brick_width;
    private int brick_height;
    private boolean isAlive;
    private Rectangle myBrick;
    /**
     * this is the main constructor for the brick class and creates a new and alive brick given an x and a y coordinate
     * @param x takes the x coordinate for the upper left corner of the brick
     * @param y takes the y coordinate for the upper left corner of the brick
     * @param w takes the width of the brick in number of pixels as an integer
     * @param h takes the height of the brick in number of pixels as an integer
     */
    public Brick(int x, int y, int w, int h)
    {
        brickX = x;
        brickY = y;
        brick_width = w;
        brick_height = h;
        isAlive = true;
        myBrick = new Rectangle(brickX,brickY,brick_width,brick_height);
    }
    /**
     * the paintComponent method draws the brick
     * @param g takes a Graphics object
     */
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.GREEN);
        g2.fillRect(brickX,brickY,brick_width,brick_height);
        g2.setColor(Color.BLACK);
        g2.fillRect(brickX+5,brickY+5,brick_width-10,brick_height-10);
        g2.draw(myBrick);
    }
    /**
     * the getWidth method allows you to get the bricks width
     * @return brick_width an integer containing the bricks width
     */
    public int getWidth()
    {
        return brick_width;
    }
    /**
     * the getHeight method allows you to get the bricks height
     * @return brick_height an integer containing the bricks height
     */
    public int getHeight()
    {
        return brick_height;
    }
    /**
     * the getX method allows you to get the x coordinate of the bricks upper left corner
     * @return brickX returns the x coordinate as an integer
     */
    public int getX()
    {
        return brickX;
    }
    /**
     * the getY method allows you to get the y coordinate of the bricks upper left corner
     * @return brickY returns the y coordinate as an integer
     */
    public int getY()
    {
        return brickY;
    }
    /**
     * the is alive function allows you to check whether the brick has been hit or not
     * @return isAlive a boolean returning true if it hasnt been hit false if it has
     */
    public boolean isAlive()
    {
        return isAlive;
    }
    /**
     * the kill method allows you to destroy the brick, it sets the isAlive boolean to false
     */
    public void kill()
    {
        isAlive = false;
    }
    /**
     * the isInside method allows you to check to see if a certain (x,y) point is inside the bricks bounds
     * the method returns a boolean true if the coordinates are inside the brick
     * @param x takes the x coordinate of the point you wish to check
     * @param y takes the y coordinate of the point you wish to check
     */
    public boolean isInside(int x, int y)
    {
        if (x>brickX && x<brickX+brick_width && y>brickY && y<brickY+brick_height)
        {
            return true;
        }
        else
        {
            return false;
        }
    }    
}