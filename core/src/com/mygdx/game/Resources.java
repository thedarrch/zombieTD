package com.mygdx.game;

/**
 * Created by RealProgramming4Kids on 2017-08-08.
 */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Resources {
    public static Texture backgroundTexture = new Texture(Gdx.files.internal("Grassybackground.png"));
    public static Texture cannonTexture = new Texture(Gdx.files.internal("Cannon.png"));
    public static Texture zombietexture = new Texture(Gdx.files.internal("Zombies.png"));
    public static Texture bullettexture = new Texture(Gdx.files.internal("Bullet.png"));
    public static Texture explosiontexture = new Texture(Gdx.files.internal("Explosion.png"));
    public static Texture fastzombietexture = new Texture(Gdx.files.internal ("Fastzombies.png"));
    public static Texture firecannontexture = new Texture(Gdx.files.internal ("Firecannon.png"));
    public static Texture throwtexture = new Texture (Gdx.files.internal ("throwsheet.png"));
    public static Texture blobtexture = new Texture (Gdx.files.internal ("blob.png"));
    public static Texture blobbedtexture = new Texture (Gdx.files.internal ("blobbed.png"));
    public static Texture level2texture = new Texture (Gdx.files.internal ("lvl2.png"));
    public static Texture menutexture = new Texture (Gdx.files.internal ("menu.png"));
    public static Texture guidedtexture = new Texture (Gdx.files.internal ("targetedgun.png"));
    public static Texture crosshairtexture = new Texture (Gdx.files.internal ("crosshairs.png"));
}