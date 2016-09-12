import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * The GuiFrame class extends the jframe class, it initializes a game object to start the game as well and also contains the action listener class for animating the ball
 */
public class GuiFrame extends JFrame
{
    private static final int FRAME_WIDTH = 700;
    private static final int FRAME_HEIGHT = 700;
    private Game myGame = new Game();

    /**
     * The animationlistener class is used to run a timer to run the ball animation and paddle animation
     * AnimationListener implements ActionListener
     */
    class AnimationListener implements ActionListener
    {
        Timer timer;
        /**
         * this is the main constructor for the AnimationListener class
         */
        public AnimationListener()
        {
            timer = new Timer(3, this);
            timer.start();
        }

        /**
         * the actionPerformed method is called every time the timer runs out
         * @param e is the action event
         */
        public void actionPerformed(ActionEvent e)
        {
            myGame.playGame();
            myGame.paddleMovement();
            myGame.repaint();
            timer.restart();
        }
    }
    /**
     * this is the main constructor for the GuiFrame class, it adds the game to frame as well as initializing the action listener and adding it to the game
     */
    public GuiFrame()
    {
        add(myGame);
        AnimationListener listener1 = new AnimationListener();
        setSize(FRAME_WIDTH,FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}
