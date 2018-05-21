package com.mygdx.game;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;


/**
 * Created by RealProgramming4Kids on 2017-08-08.
 */

public class cannon
{
    public Sprite cannonSprite;
    public float xpos, ypos, width, height, angle;
    public int firedelay, counter;
    public Sound firesfx;

    public cannon(float x, float y)
    {

        initTexture();
        width = Resources.cannonTexture.getWidth();
        height = Resources.cannonTexture.getHeight();

        xpos = lockToGrid(x-width/2);
        ypos = lockToGrid(y-height/2);

        firedelay = 30;
        counter = 0;

        cannonSprite.setPosition(lockToGrid(xpos), lockToGrid(ypos));
        firesfx = Gdx.audio.newSound(Gdx.files.internal("Bullet.mp3"));
    }
    public float lockToGrid(float pos)
    {
        return ((int)(pos+25)/50)*50;
    }


public void initTexture(){
    cannonSprite = new Sprite(Resources.cannonTexture);
}

    public void draw(SpriteBatch batch)
    {
        cannonSprite.draw(batch);
    }

    public void fire()
    {
        if (!ZombieTD.zombielist.isEmpty())
        {
            ZombieTD.bulletlist.add(new bullet(xpos +width/2, ypos+height/2));
            firesfx.play();
        }
    }
    public void update() {
        getAngle();
        cannonSprite.setRotation(angle);
        if (counter++ >= firedelay) {
            for (int i = 0; i < ZombieTD.zombielist.size(); i++) {
                if (ZombieTD.zombielist.get(i).xpos <= 1024) {

                    {
                        fire();
                        counter = 0;
                    }
                }

            }
        }
    }
    public float getAngle()
    {
        if (!ZombieTD.zombielist.isEmpty()) {
            float xC, yC, xZ, yZ;
            xC = xpos;
            yC = ypos;
            xZ = ZombieTD.zombielist.get(0).xpos;
            yZ = ZombieTD.zombielist.get(0).ypos;
            angle = (float) Math.atan((yC - yZ) / (xC - xZ));
            if (xC >= xZ) {
                this.angle += Math.PI;
            }
        }
        return this.angle = (float) Math.toDegrees(angle);
    }
    public Rectangle getRectangle()
    {
        return new Rectangle(xpos, ypos, width, height);
    }
}
