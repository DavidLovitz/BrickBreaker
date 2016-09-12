import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JComponent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.PointerInfo;
import java.awt.MouseInfo;
import java.awt.Point;
/**
 * the game class extends JComponent and allows you to create a game object that will run gameplay for brickbreaker
 * the game will continue to run until it has been closed, you will start with 4 (0,1,2,3) lives and if the lives go below 0 the game will reset itself and run again
 * if you clear all of the bricks you will recieve one more life and a new board will be drawn, your score will keep being counted as long as you dont lose all your lives
 * 1 round = 3200 points or one clear of the entire board
 * in order to control the paddle you use the mouse, if the paddle does not appear to be centered with the x coordinate of the mouse pointer then restart the game and 
 * make sure your mouse is over the game window while it begins
 * There is also code that is commented out in the paddleMovement method which will make the computer play so you can watch and try and find bugs
 * GLITCHES: the collisions with the bricks is doesnt quite work when the ball collides with the corners of the 3 bricks all next to eachother, 
 * this causes the ball to keep moving forward, i suspect this is becasue so many different conditions need to be checked at one time
 */
public class Game extends JComponent
{
    private int score;
    private int lives;
    private int roundNumber;

    private int dy = -1;
    private int dx = -1;

    private Paddle myPaddle;
    private Ball myBall;
    private Brick[][] brickList = new Brick[8][4];
    /**
     * this is the main constructor for the game class which takes no arguments and initializes the paddle and ball and starts a new game automatically
     */
    public Game()
    {
        myPaddle = new Paddle();
        myBall = new Ball(400,500,19);
        newGame();
    }
    /**
     * the newGame method allows you to create a new game with score=0 and lives=3, it will also reset the board and round number
     */
    public void newGame()
    {
        roundNumber= 0;
        newBoard();
        score = 0;
        lives = 3;
    }
    /**
     * the newBoard method redraws the board it will also increment the round number and lives by 1
     */
    public void newBoard()
    {
        for (int ii=0; ii<8; ii++)
        {
            for (int jj=0; jj<4; jj++)
            {
                int xCoordinate = 35+(ii*80);
                int yCoordinate = 50+(jj*30);
                Brick myBrick = new Brick(xCoordinate,yCoordinate,70,20);
                brickList[ii][jj]= myBrick;
            }
        }
        roundNumber+=1;
        lives+=1;
    }
    /**
     * the paintComponent method draws all of the graphics for the game including the board, score, and calls the draw methods for the bricks, ball, and paddle
     * @param g takes a Graphics object
     */
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.BLUE);
        g2.fillRect(0,0,700,700);

        g2.setColor(Color.BLACK);
        g2.fillRect(30,50,640,600);

        for (int ii=0; ii<8; ii++)
        {
            for (int jj=0; jj<4; jj++)
            {
                if (brickList[ii][jj].isAlive())
                {brickList[ii][jj].paintComponent(g);}
            }
        }

        g2.setColor(Color.BLACK);
        String currentScore = score +"";
        String currentLives = lives +"";
        String currentRound = roundNumber +"";
        g2.setFont( new Font("TimesRoman", Font.BOLD, 20));
        g2.drawString("SCORE:",10,20);
        g2.drawString(currentScore, 85,20);
        g2.drawString("LIVES: ",10,35);
        g2.drawString(currentLives, 80,35);
        g2.drawString("ROUND: ",120,35);
        g2.drawString(currentRound,200,35);

        myBall.paintComponent(g);
        myPaddle.paintComponent(g);

    }
    /**
     * the paddleMovement method moves the paddle in line with the mouse so that the center of the paddle is in line with the mouses x coordinate
     * to make the computer play for you uncomment the line that is commented out and the comment out the line "myPaddle.movePaddleTo(dx-45,625);"
     */
    public void paddleMovement()
    {
        PointerInfo pointer = MouseInfo.getPointerInfo();
        Point b = pointer.getLocation();
        int dx = (int) b.getX();
        myPaddle.movePaddleTo(dx-45,625);
        //myPaddle.movePaddleTo(myBall.get_X_Coordinate()-40,625);
        repaint();
    }
    /**
     * the playGame method runs the main gameplay, it controls the balls movement and all collisions with a brick, the walls or the paddle
     * it also calls the balls move method and checks to see of the players lives have run out yet or if the board has been cleared, if the board has been cleared it will call newBoard()
     */
    public void playGame()
    {
        for (int ii=0; ii<8; ii++)
        {
            for (int jj=0; jj<4; jj++)
            {

                if (brickList[ii][jj].isAlive())
                {
                    int brickX = brickList[ii][jj].getX();
                    int brickY = brickList[ii][jj].getY();
                    int brickW = brickList[ii][jj].getWidth();
                    int brickH = brickList[ii][jj].getHeight();
                    int ballX = myBall.get_X_Coordinate();
                    int ballY = myBall.get_Y_Coordinate();

                    //vertical collisions
                    if((ballX+9>brickX && ballX+9<brickX+brickW) 
                    && 
                    (brickList[ii][jj].isInside(ballX,ballY) 
                        || brickList[ii][jj].isInside(ballX+19,ballY) 
                        || brickList[ii][jj].isInside(ballX,ballY+19) 
                        || brickList[ii][jj].isInside(ballX+19,ballY+19)))
                    {
                        brickList[ii][jj].kill();
                        dy=-dy;
                        score+=100;
                        return;
                    }
                    //horizontal collisions
                    if((ballY+9<brickY+brickH && ballY+9>brickY)
                    &&
                    (brickList[ii][jj].isInside(ballX,ballY) 
                        || brickList[ii][jj].isInside(ballX+19,ballY) 
                        || brickList[ii][jj].isInside(ballX,ballY+19) 
                        || brickList[ii][jj].isInside(ballX+19,ballY+19)))
                    {
                        brickList[ii][jj].kill();
                        dx=-dx;
                        score+=100;
                        return;
                    }

                }
            }
        }

        if (myBall.get_X_Coordinate() >= 650 && dx>0)
        {
            dx=-dx;
        }
        if (myBall.get_X_Coordinate() <= 30 && dx<0)
        {
            dx=-dx;
        }
        if (myBall.get_Y_Coordinate() <= 50 && dy<0)
        {
            dy=-dy;
        }
        if (myBall.get_Y_Coordinate() >= 630)
        {
            lives -=1;
            myBall.moveBallTo(100,250);
        }
        //paddle collision
        //moveBallTo is called to fix a glitch where the ball would get stuck on the paddle
        if (myPaddle.isInside(myBall.get_X_Coordinate(),myBall.get_Y_Coordinate()+19) 
        || myPaddle.isInside(myBall.get_X_Coordinate()+19,myBall.get_Y_Coordinate()+19))
        {
            dy=-dy;
            myBall.moveBallTo(myBall.get_X_Coordinate(),myBall.get_Y_Coordinate()-1);
        }
        //move ball
        myBall.moveBallBy(dx,dy);
        //lives left checker
        if (lives == -1)
        {
            newGame();
        }
        //completed round checker
        if (score == 3200*roundNumber)
        {
            newBoard();
        }
    }
}