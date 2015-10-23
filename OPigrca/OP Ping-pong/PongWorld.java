import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PongWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PongWorld  extends World
{

//Ustvari spremenljivko za objekt scoreCounter.
    scoreCounter ScoreCounter = new scoreCounter("Lives: 3 Score: 0");
    Wall wally = new Wall();
    
    EndGame endgame = new EndGame();
    Ball ball = new Ball(ScoreCounter, wally, endgame);
    //Ustvari spremenljivko tipa integer za pričetek igre, ki je na začetku enaka nič.
    private int gameStart = 0;
    /**
     * Constructor for objects of class PongWorld.
     * 
     */
    public PongWorld()
    {    
        //Ustvari nov svet z 600x400 celicami velikosti 1x1 pixel.
        super(600, 400, 1);
        
       
        addObject(ScoreCounter, 200, 40); //Določi pozicijo objekta scoreCounter.
        
        addObject(wally, 300, 300); //Določi pozicijo objekta wally (lopar).
        
       
        addObject(ball,300, 280); //Določi pozicijo objekta ball (žogica ).
        
       backWall BackWall = new backWall(); //Določi pozicijo in ustvari objekt BackWall (ognjena past).
       addObject(BackWall, 300, 380);
       
      
       
       addObject(new Background(), 300, 200); 
       
       setPaintOrder(EndGame.class, Wall.class, Ball.class, backWall.class, scoreCounter.class, Background.class);
    }
    public void act()
    {
       //Ko pritisnemo tipko space se igra prične.
        if (Greenfoot.isKeyDown("space"))
       {
           gameStart = 1;
        }
       
       //Ob dogodku gameOver se preneha izvajanje greenfoot-a, doda now objekt endGame.
        if(ball.gameOver())
        {
            Greenfoot.stop();
            addObject(new EndGame(), 300, 200);
        }
    }
    public boolean startGame()
    {
    //Ko se igra prične, vrednost spremenljivke gameStart postane 1, zato boolean funkcija vrne rezultat true.
        if (gameStart == 1) {
            return true;
    }
    return false;
    }
}

