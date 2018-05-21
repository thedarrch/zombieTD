package com.mygdx.game;
import static com.badlogic.gdx.math.MathUtils.random;



public class blob extends bullet {
    public blob(float x, float y)
    {

    super(x, y);
        speed = 4;
        range = 300;
        getAngle();
}

    public void inittexture(){
        bullettexture = Resources.blobtexture;

    }

    public void getAngle()
    {
        if (!ZombieTD.zombielist.isEmpty())
        {
            float xC, yC, xZ, yZ, angle;
            int rand = random (0, ZombieTD.cannonlist.size()-1);
            xC = ZombieTD.cannonlist.get(rand).xpos + ZombieTD.cannonlist.get(rand).width/2;
            yC = ZombieTD.cannonlist.get(rand).ypos + ZombieTD.cannonlist.get(rand).height/2;
            xZ = xpos;
            yZ = ypos;
            angle = (float) Math.atan((yZ - yC)/(xZ - xC));
            if (xC <= xZ)
            {
                angle += Math.PI;
            }
            this.angle = angle;
        }
    }
}
