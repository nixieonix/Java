import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Wall  extends Actor
{
    public int movingSpeed = 5; //Hitrost premikanja je enaka 5.
    public void act() //metoda act se pokliče kadar v okolju pritisnemo gumb act ali run 
    {
        if (Greenfoot.isKeyDown("left")) //preverja če smo pritisnili levo tipko.
        {
            moveLeft();  //kliče funkcijo za premik v levo
        }
        if (Greenfoot.isKeyDown("right")) //preverja če smo pritisnili desno tipko.
        {
            moveRight();  //kliče funkcijo za premik v desno
        }
    }  
    public void moveLeft()
    {
        //Lopar (wall) se premakne v levo
        setLocation( getX()-movingSpeed, getY());
    }
    public void moveRight()
    {
        //Lopar (wall) se premakne v desno
        setLocation( getX()+movingSpeed, getY());
    }
}

