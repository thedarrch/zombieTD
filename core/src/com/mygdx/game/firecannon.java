package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by RealProgramming4Kids on 2017-08-09.
 */

public class firecannon extends cannon {
    public firecannon(float x, float y){
        super (x, y);
        firedelay = 15;

    }
    public void initTexture(){
        cannonSprite = new Sprite(Resources.firecannontexture);
    }



}
