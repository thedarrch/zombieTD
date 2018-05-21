package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class zombie
{
    public Texture zombietexture;
    public float xpos, ypos, width, height, speed;
    public boolean active;
    public int hp;


    //variables for animations
    public int numcol, numrow;
    public Animation walkanim;
    public TextureRegion[] walkframes;
    public TextureRegion currentframe;
    public float frametime;

    public zombie(float x, float y, int hp)
    {
        initTexture();
        xpos = x;
        ypos = y;
        width = zombietexture.getWidth()/4;
        height = zombietexture.getHeight();
        initAnimations();
        speed = 1;
        active = true;
        this.hp = hp;
    }

    public void initAnimations()
    {
    numrow = 1;
    numcol = 4;

    TextureRegion[][] temp = TextureRegion.split(zombietexture, zombietexture.getWidth()/numcol, zombietexture.getHeight()/numrow);
    walkframes = new TextureRegion[numrow*numcol];
        int frameIndex = 0;
        for (int i = 0; i < numrow; i++)
        {
            for (int j = 0; j<numcol; j++)
            {
                walkframes[frameIndex++] = temp[i][j];

            }
        }

        walkanim = new Animation(0.2f, walkframes);
    }
public void initTexture(){
    zombietexture = Resources.zombietexture;
}

    public void draw(SpriteBatch batch)
    {
        frametime += Gdx.graphics.getDeltaTime();
        currentframe =(TextureRegion)walkanim.getKeyFrame(frametime, true);
        batch.draw(currentframe, xpos - width/2, ypos - height/2);
    }

    public void update() {
        if (xpos < -20) {
            active = false;
            ZombieTD.lives--;
        }

if (ZombieTD.level ==1) {
    xpos -= speed;
}
        if (ZombieTD.level ==2) {


            if (xpos > 775) {
                xpos -= speed;
            } else if (xpos > 500 && xpos <= 775 && ypos < 430) {
                ypos += speed;
            } else if (480 < xpos && xpos <= 775) {
                xpos -= speed;
            } else if (ypos > 175) {
                ypos -= speed;
            } else if (ypos <= 175) {
                xpos -= speed;
            }
        }
    }

public void takedmg(){
        if (hp --<0) {
            active = false;
            ui.money += 5;
        }
    }
    public Rectangle getRectangle()
    {
        return new Rectangle(xpos - width/2, ypos - height/2, width, height);
    }
}
