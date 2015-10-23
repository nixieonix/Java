import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Bouncer  extends Actor
{
public double speed = 5; //ustvarimo spremenljivko tipa double, ki je priredimo vrednost 5 (se premakne za 5 pixlov na frame)
public Bouncer()
{
    setRotation(Greenfoot.getRandomNumber(360));
}
    /**
     * Act - do whatever the Bouncer wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // žogica poskakuje
        move(speed); //premik glede na hitrost
        ifOnEdgeBounce(); //če pride žogica v stik z robom se od njega odbije
        bounceOff(Wall.class); //odboj od loparja (wall)
    }   
    
    /**
      *   if on edge, bounce
      *   Greenfoot-Example: ifOnEdgeBounce();
      **/
    public void ifOnEdgeBounce() 
    {   // rob minus 1 pika v vse smeri
        int x = getX();
        int y = getY();
        if(x >= (getWorld().getWidth() - 1)) {
            setRotation (180 - getRotation());
        }
        if(x < 1) {
            setRotation (180-getRotation());
        }
        if(y >= getWorld().getHeight()) {
            setRotation (360-getRotation());
        }
        if(y < 1) {
            setRotation (360-getRotation());
        }
    }
    
    //premik za določeno število korakov v določeni smeri
    public void move(double steps) 
    {
         double angle = Math.toRadians( getRotation() );
         int x = (int) Math.round(getX() + Math.cos(angle) * steps);
         int y = (int) Math.round(getY() + Math.sin(angle) * steps);      
         setLocation(x, y );        
    }
    
    //odboj od objekta določenega class tipa
    public void bounceOff(Class cls)
    {
        Actor collided = getOneIntersectingObject(cls);  //preveri če je prišlo do trka med objekti
        if (collided != null) //če je do trka prišlo
        {                                 
            setRotation(360-getRotation()); //odboj     
            move(speed);    //premik glede na hitrost                                  
        }   
    }
}
