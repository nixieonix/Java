import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;

public class scoreCounter  extends Actor
{
    public scoreCounter(String text)
    {
        //Ustvari sliko z textom

        GreenfootImage img = new GreenfootImage (text.length()*20, 30);
        img.setFont(new Font(null, Font.PLAIN, 20));
        img.drawString (text, 2, 20);
        setImage (img);
    }
   public void setText(String text)
   {
       //Spreminjanje slike ob spreminjanju rezultata.
       GreenfootImage img = getImage();
       img.setColor(new Color(0x2f26ff));
       img.clear();
       img.drawString (text, 2, 20);
    }
}
