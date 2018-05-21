package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


public class button {
    public Texture buttontexture;
    public float xpos, ypos, height, width;
    public Rectangle rect;

    public button(float x, float y, Texture tex){
        buttontexture = tex;
        xpos = x;
        ypos = y;
        width = buttontexture.getWidth();
        height = buttontexture.getHeight();
        rect = new Rectangle(xpos, ypos - height, width, height);
    }

    public void draw(SpriteBatch batch){
        batch.draw(buttontexture, xpos, ypos - height);
    }

    public boolean isclicked(float x, float y)
    {
     return rect.contains(x, y);
    }
}
