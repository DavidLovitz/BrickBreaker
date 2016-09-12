import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Color;
import javax.swing.JComponent;
/**
 * The ball class allows you to create ball objects for the game brickbreaker
 * 
 * @author David Lovitz 
 * @version December 18, 2013
 */
public class Ball
{
    private int diameter;
    private int currentBallX;
    private int currentBallY;
    /**
     * this is the main constructor for the ball class
     * @param x takes the starting x coordinate as an integer for the upper left corner of the box that encases the circle
     * @param y takes the starting y coordinate as an integer for the upper left corner of the box that encases the circle
     * @param diameter takes the balls diameter as an integer
     */
    public Ball(int x, int y, int diameter)
    {
        currentBallX = x;
        currentBallY = y;
        this.diameter = diameter;
    }
    /**
     * the moveBallTo method allows you to transport the ball to a certain x,y coordinate
     * @param x new x coordinate as an integer
     * @param y new y coordinate as an integer
     */
    public void moveBallTo(int x, int y)
    {
        currentBallX = x;
        currentBallY = y;
    }
    /**
     * the moveBallBy method allows you to move the ball by a specified amount in the x and y directions
     * @param dx takes the change in x as an integer (can be positive or negative, positive for right, negative for left)
     * @param dy takes the change in y as an integer (can be positive or negative, positive for down, negative for up)
     */
    public void moveBallBy(int dx, int dy)
    {
        currentBallX+= dx;
        currentBallY+= dy;
    }
    /**
     * the paintComponent method draws the ball object
     * @param g takes a Graphics object
     */
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.ORANGE);
        g2.fillOval(currentBallX,currentBallY,diameter,diameter);
    }
    /**
     * the get_X_Coordinate() method allows you to access the x coordinate of the balls upper left corner
     * @return currentBallX returns the current x coordinate for the upper left corner of the balls bounds
     */
    public int get_X_Coordinate()
    {
        return currentBallX;
    }
    /**
     * the get_Y_Coordinate() method allows you to access the y coordinate of the balls upper left corner
     * @return currentBallY returns the current y coordinate for the upper left corner of the balls bounds
     */
    public int get_Y_Coordinate()
    {
        return currentBallY;
    }
}
