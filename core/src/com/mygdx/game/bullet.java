package com.mygdx.game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.*;


public class bullet
{
 public Texture bullettexture;
    public float xpos, ypos, width, height, speed, angle;
    public boolean active;
    public int range;

    public bullet(float x, float y)
    {
        inittexture();
        xpos = x;
        ypos = y;
        width = bullettexture.getWidth();
        height = bullettexture.getHeight();
        speed = 5;
        active = true;
        range = 100;
        getAngle();
    }

    public void draw(SpriteBatch batch)
    {
        batch.draw(bullettexture, xpos-width/2, ypos - height/2);
    }

    public void update()
    {
    xpos += (float)Math.cos(angle) * speed;
        ypos += (float)Math.sin(angle) * speed;
if (range--<0)
{
    active = false;
}
    }

    public void inittexture(){
        bullettexture = Resources.bullettexture;

    }
    public void getAngle()
    {
        if (!ZombieTD.zombielist.isEmpty())
        {
            float xC, yC, xZ, yZ, angle;
            xC = xpos;
            yC = ypos;
            xZ = ZombieTD.zombielist.get(0).xpos;
            yZ = ZombieTD.zombielist.get(0).ypos;
            angle = (float) Math.atan((yC - yZ)/(xC - xZ));
            if (xC >= xZ)
            {
                angle += Math.PI;
            }
            this.angle = angle;
        }
    }

    public Circle getCircle()
    {
        return new Circle (xpos, ypos, width/2);
    }
}
