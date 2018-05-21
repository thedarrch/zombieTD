package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by RealProgramming4Kids on 2017-08-11.
 */

public class guidedcannon extends cannon {
    public guidedcannon(float x, float y) {
        super(x, y);
        firedelay = 15;

    }

    public void initTexture() {
        cannonSprite = new Sprite(Resources.guidedtexture);

    }

    public float getAngle()
    {
            float xC, yC, xZ, yZ, angle;
            xC = xpos;
            yC = ypos;
            xZ = ZombieTD.crosshairbutton.xpos;
            yZ = ZombieTD.crosshairbutton.ypos - 50;
            angle = (float) Math.atan((yC - yZ) / (xC - xZ));
            if (xC >= xZ)
                angle += Math.PI;
            return this.angle = angle;
    }

    public float getcannonAngle() {
        {
            float xC, yC, xZ, yZ, angle;
            xC = xpos;
            yC = ypos;
            xZ = ZombieTD.crosshairbutton.xpos;
            yZ = ZombieTD.crosshairbutton.ypos - 50;
            angle = (float) Math.atan((yC - yZ) / (xC - xZ));
            if (xC >= xZ) {
                angle += Math.PI;
            }
           return this.angle = (float) Math.toDegrees(angle);
        }
    }

    public void update() {
        cannonSprite.setRotation(this.getcannonAngle());
        if (counter++ >= firedelay) {
            fire();
            counter = 0;
        }

    }
public void fire() {{

        ZombieTD.bulletlist.add(new bullet(xpos +width/2, ypos+height/2));
    ZombieTD.bulletlist.get(ZombieTD.bulletlist.size()-1).angle = this.getAngle();
        firesfx.play();
    }
}
}
