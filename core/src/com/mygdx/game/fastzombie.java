package com.mygdx.game;



public class fastzombie extends zombie{

    public fastzombie(float x, float y, int hp){

        super (x, y, hp);
        speed = 3;

}

    public void initTexture(){
        zombietexture = Resources.fastzombietexture;
    }


}
