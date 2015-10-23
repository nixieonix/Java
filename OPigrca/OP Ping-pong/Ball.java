import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Ball here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ball  extends Bouncer
{
//ustvarimo spremenljivko za scoreCounter.    
private scoreCounter myScoreCounter;
private Wall myWally;
//ustvarimo spremenljivko za scoreCounter,ki je na začetku enaka nič.
    private int count = 0;
    
//ustvarimo spremenljivko za življenja (lives),ki je na začetku enaka 3 in se nato zmanjšuje.
    private int lives = 3;
    
//ustvarimo spremenljivko za dodatna življenja (extralives),ki je na začetku enaka nič in se lahko nato poveča.
    private int extraLives = 0;
    
    public Ball(scoreCounter ScoreCounter, Wall wally, EndGame endgame) //kličemo funkcijo za začetno rotacijo
    {
        myScoreCounter = ScoreCounter;
        myWally = wally;
        initialRotation();
    }
    
    public void act() 
    {
       if (((PongWorld)getWorld()).startGame()) {
        //če se je igra pričela žogica poskakuje po svetu.
        move(speed); //premik glede na hitrost
        ifOnEdgeBounce(); //klic funkcije za odboj od roba
        checkBounce(); //klic funkcije za odboj (bounce())
        checkBounce2(); //klic funkcije za odboj (bounce2()) od ognjene pasti (backWall)
    }
    }   

    
    public void ifOnEdgeBounce() 
    {   // Rob minus 1 pika v vse smeri
        int x = getX(); //pridobi x pozicijo žogice
        int y = getY(); //pridobi y pozicijo žogice
        if(x >= (getWorld().getWidth() - 1)) { //če je pozicija x večja ali enaka od širine sveta minus 1
            setRotation (180 - getRotation()); // spremeni rotacijo žogice
        }
        if(x < 1) { //če je pozicija x manjša od 1
            setRotation (180-getRotation());// spremeni rotacijo žogice
        }
        if(y >= getWorld().getHeight() - 1) {
            setRotation (360-getRotation());// spremeni rotacijo žogice
        }
        if(y < 1) {
            setRotation (360-getRotation());// spremeni rotacijo žogice
        }
        setLocation(x, y); //nastavi novo lokacijo žogice
    }
    
    /**
    * se premakne za podano število korakov v trenutni smeri
    **/
    public void move(double steps) 
    {
        //matematično določanje števila korakov in premikov
         double angle = Math.toRadians( getRotation() );
         int x = (int) Math.round(getX() + Math.cos(angle) * steps);
         int y = (int) Math.round(getY() + Math.sin(angle) * steps);      
         setLocation(x, y);  //nastavi novo lokacijo žogice      
    }
    public boolean onWall()
    {
        //Pove če se žogica dotika loparja(wall).
        Actor under = getOneObjectAtOffset (0, getImage(). getHeight()/16, Wall.class); //loparju zmanjšamo višino tako da jo delimo.
        return under != null; //vrne če ni nič
    }
    public void checkBounce() //funkcija za preverjanje odboja od loparja
    {
        //Če žogica prileti v lopar(wall) se od njega odbije.
        if(onWall()) {
            bounce();
        }
    }
    public void bounce()
    {
        //Odbije žogico od loparja(wall). Žogica pospeši, rezultat se poveča, če smo žogico odbili že 10 krat dobimo dodatno življenje.
        setRotation(0-getRotation());  
            speed = speed + 0.3; //poveča hitrost, ki smo jo določili na začetku
            move(speed); //naredi premik glede na novo hitrost
            if(getY()>300){
                count--; // prišlo je do odboja, ki je pod loparjem (žogica je padla na ognjeno polje), točke se zmanjšajo
            }
            else
            {
                count++; // prišlo je do odboja, ki je v višini loparja, točke se povečajo
                extraLives++; //povečanje vrednosti spremenljivke extralives
                if(extraLives==10){ //ko je vrednost vseh pridobljenih extralives točk enaka 10 pridobimo novo življenje
                        extraLives = 0;
                        lives++;
                        myWally.movingSpeed++; //hitrost se poveča
                        myWally.movingSpeed++;
                }
            }
            myScoreCounter.setText("Lives: " + lives + " Score: " + count);
    }

    public void checkBounce2()
    {
        //Če je žogica na zadnjem zidu(backWall) se bo od njega odbila. Rezultat in število življenj se zmanjšata, žogica upočasni.
        if(getY()>398) { //če se je žogica dotaknica področja na kateremse nahaja ognjeno polje
            count--; //rezultat se zmanjša
            lives--; //Število življenj se zmanjša
            speed = speed - 0.5; // hitrost premikanja žogice se zmanjša
            move(speed); //premik glede na novo hitrost
            myScoreCounter.setText("Lives: " + lives + " Score: " + count);
        }
    }
   
    public void initialRotation()
    {
        //Postavi žogico v začetno smer med 0 in 60 stopinjami.
        setRotation(Greenfoot.getRandomNumber(50)+10);
    }
    
    public boolean gameOver()
    {
        
        //Če je število življenj(lives) enako nič bo funkcija vrnila true, igra se konča.
       if (lives == 0) {
       return true;
    }
    return false;
}

}


