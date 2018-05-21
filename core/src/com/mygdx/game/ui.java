package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;



public class ui {
    public static int money = 30;
    public static BitmapFont font = new BitmapFont();

    public static void draw(SpriteBatch batch) {
        font.setColor(Color.YELLOW);
        font.draw(batch, "Money: " + money, 100, 550);

        font.setColor(Color.BLUE);
        font.draw(batch, "Level: " + ZombieTD.level, 200, 550);

        font.setColor(Color.GREEN);
        font.draw(batch, "Wave: " + ZombieTD.wave, 300, 550);

        font.setColor(Color.RED);
        font.draw(batch, "Lives: " + ZombieTD.lives, 700, 550);















        if (ZombieTD.lives <= 0) {
            font.getData().setScale(2);
            font.draw(batch, "GAME OVER", 430, 300);
            font.getData().setScale(1);
        }

        if (ZombieTD.win == true) {
            font.getData().setScale(2);
            font.draw(batch, "YOU WIN!", 430, 300);
            font.getData().setScale(1);
        }
        }
    }

