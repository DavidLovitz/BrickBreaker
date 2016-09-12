import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Color;
import javax.swing.JComponent;
/**
 * The paddle class allows you to create paddle objects for the brickbreaker game
 * 
 * @author David Lovitz 
 * @version December 18, 2013
 */
public class Paddle
{
    private int paddleX = 350;
    private int paddleY = 625;
    private int paddle_width = 90;
    private int paddle_height = 23;
    private int currentPaddleX;
    private int currentPaddleY;

    private Rectangle myPaddle;

    /**
     * this is the main constructor for the paddle class
     */
    public Paddle()
    {
        myPaddle = new Rectangle(paddleX,paddleY,paddle_width,paddle_height);
        currentPaddleX = paddleX;
        currentPaddleY = paddleY;
    }

    /**
     * the paintComponent method draws the paddle at the current location 
     */
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.WHITE);
        g2.fillRect(currentPaddleX,currentPaddleY,paddle_width,paddle_height);
        g2.draw(myPaddle);
    }

    /**
     * the movepaddleTo method allows you to move the paddle to a certain x and y coordinate and makes sure that the paddle stays within the game board
     * @param x takes the new x coordinate for the upper left corner of the paddle
     * @param y takes the new y coordinate for the upper left corner of the paddle
     */
    public void movePaddleTo(int x, int y)
    {
        if (x>30 && x<580)
        {
            myPaddle.setLocation(x,y);
            currentPaddleX = x;
            currentPaddleY = y;
        }
    }
    /**
     * the isInside method allows you to check whether a certain x,y point is inside the paddle
     * @param x takes the x coordinate you want to check as an integer
     * @param y takes the y coordinate you want to check as an integer
     */
    public boolean isInside(int x, int y)
    {
        if(x>currentPaddleX && x<currentPaddleX+paddle_width && y>currentPaddleY && y<currentPaddleY+paddle_height)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
