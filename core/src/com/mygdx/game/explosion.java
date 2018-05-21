package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.Texture;


public class explosion {

    public Texture explosiontexture;
    public float xpos, ypos, width, height, speed;
    public boolean active;
    //variables for animations
    public int numcol, numrow;
    public Animation explodeanim;
    public TextureRegion[] explodeframes;
    public TextureRegion currentframe;
    public float frametime;
    public int hp = 10;

    public explosion(float x, float y)
        {
            explosiontexture = Resources.explosiontexture;
            xpos = x;
            ypos = y;
            initAnimations();
            width = explosiontexture.getWidth() / 6;
            height = explosiontexture.getHeight();
            active = true;
            hp = 10;
        }


    public void initAnimations()
        {
        numrow = 1;
        numcol = 6;

        TextureRegion[][] temp = TextureRegion.split(explosiontexture, explosiontexture.getWidth()/numcol, explosiontexture.getHeight()/numrow);
        explodeframes = new TextureRegion[numrow*numcol];
        int frameIndex = 0;
        for (int i = 0; i < numrow; i++)
        {
            for (int j = 0; j<numcol; j++)
            {
                explodeframes[frameIndex++] = temp[i][j];

            }
        }

        explodeanim = new Animation(0.2f, explodeframes);
    }

    public void draw(SpriteBatch batch)
    {
        frametime += Gdx.graphics.getDeltaTime();
        currentframe =(TextureRegion)explodeanim.getKeyFrame(frametime, true);
        batch.draw(currentframe, xpos - width/2, ypos - height/2);
    }

    public void update(){
if (hp -- == 0)
{
    active = false;
}
    }
}
