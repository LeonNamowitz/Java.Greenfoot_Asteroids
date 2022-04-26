import javax.lang.model.util.ElementScanner6;
import javax.swing.plaf.TreeUI;

import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)

/**
 * Space. Something for rockets to fly in...
 * 
 * @author Michael Kölling
 * @version 2.1
 */
public class Space extends World
{
    /**
     * Create the space world with black background and stars.
     */
    public Space() 
    {
        super(800, 600, 1);
        GreenfootImage background = getBackground();
        background.setColor(Color.BLACK);
        background.fill();
        createStars(300);
        // spawnAsteroid(4);
        spawnShip(400, 300);
        Explosion.initialiseImages();
    }
    
    /**
     * Create some random stars in the world
     */
    private void createStars(int number) 
    {
        GreenfootImage background = getBackground();             
        for (int i=0; i < number; i++) {            
             int x = Greenfoot.getRandomNumber( getWidth() );
             int y = Greenfoot.getRandomNumber( getHeight() );
             int color = 150 - Greenfoot.getRandomNumber(120);
             background.setColorAt(x, y, new Color(color,color,color));
        }
    }

    public void spawnAsteroid(int asteroidAmount)
    {
        int amount = asteroidAmount;
        for (int i = 0; i < amount; i++) {
            addObject(new Asteroid(64), generator(0), generator(1));
        }
    }

    private void spawnShip(int xCoord, int yCoord)
    {
        addObject(new Rocket(), xCoord, yCoord);
    }

    public void asteroidCounter()
    {
        // if (Rocket.checkCollision() == true)  {
        //     // increase COunter
        // }
        // else    {

        // }
        
    }



    private int generator(int type)
    {
        if (type == 0)  {
            return(Greenfoot.getRandomNumber(800)+1);
        }
        else if (type == 1) {
            return(Greenfoot.getRandomNumber(600)+1);
        }
        else    {
            return(0);
        }
    }
}