package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class throwzombie extends zombie
{
    public TextureRegion[] throwFrames;
    public Animation throwAnim;
    public int throwTimer, frameNum;
    public boolean throwing;
    public float spitFrameTime;

    public throwzombie(float x, float y, int hp)
    {
        super(x, y, hp);
        speed = 2;
        throwTimer = 0;
        throwing = false;
        height = zombietexture.getHeight()/2;

    }

    @Override
    public void initTexture()
    {
        zombietexture = Resources.throwtexture;
    }

    @Override
    public void initAnimations()
    {
        numrow = 2;
        numcol = 4;
        frameNum = 0;
        //temporary 2D array to map a spritesheet onto
        TextureRegion[][] temp = TextureRegion.split(zombietexture, zombietexture.getWidth()/numcol, zombietexture.getHeight()/numrow);

        walkframes = new TextureRegion[4];
        throwFrames = new TextureRegion[4];

        int walkFrameIndex = 0;
        int throwFrameIndex = 0;

        for (int i = 0; i < numrow; i++)
        {
            if (i == 0)
            {
                for (int j = 0; j < numcol; j++)
                {
                    walkframes[walkFrameIndex++] = temp[i][j];
                }
            }
            if (i == 1)
            {
                for (int j = 0; j < numcol; j++)
                {
                    throwFrames[throwFrameIndex++] = temp[i][j];
                }
            }
        }
        walkanim = new Animation(0.2f, walkframes);
        throwAnim = new Animation(0.2f, throwFrames);
        throwAnim.setPlayMode(Animation.PlayMode.NORMAL);
    }

    public void draw(SpriteBatch batch)
    {
        if (throwing == false)
        {
            frametime += Gdx.graphics.getDeltaTime();
            currentframe = (TextureRegion) walkanim.getKeyFrame(frametime, true);
            batch.draw(currentframe, xpos - width / 2, ypos - height / 2);
        }
        if (throwing)
        {
            spitFrameTime += Gdx.graphics.getDeltaTime();
            currentframe = (TextureRegion) throwAnim.getKeyFrame(spitFrameTime, true);
            batch.draw(currentframe, xpos - width / 2, ypos - height / 2);

            if (throwAnim.isAnimationFinished(spitFrameTime))
            {
                spit();
                throwing = false;
                throwTimer = 0;
                spitFrameTime = 0;
                speed = 2;
            }
        }
    }

    @Override
    public void update() {
        if (xpos < -20) {
            active = false;
            ZombieTD.lives--;
        }

            if (throwTimer++ >= 300 && throwing == false && !ZombieTD.cannonlist.isEmpty()) {
                throwing = true;
                speed = 0;
            }


            if (ZombieTD.level == 1) {
            xpos -= speed;

            }

            else if (ZombieTD.level == 2) {
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

    public void spit()
    {
        if (!ZombieTD.cannonlist.isEmpty())
        {
            ZombieTD.bloblist.add(new blob(xpos,ypos));
        }
    }

    public Rectangle getRectangle()
    {
        return new Rectangle(xpos - width/2, ypos - height/2, width, height/2);
    }
}