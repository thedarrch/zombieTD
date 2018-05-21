package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import com.badlogic.gdx.math.*;


public class ZombieTD extends ApplicationAdapter {

	public enum TowerType {CANNON, FIRECANNON, GUIDED}

	public static TowerType selectedTower;
	public OrthographicCamera camera;

	SpriteBatch batch;
	public static ArrayList<cannon> cannonlist = new ArrayList<cannon>();
	public static ArrayList<zombie> zombielist = new ArrayList<zombie>();
	public static ArrayList<bullet> bulletlist = new ArrayList<bullet>();
	public static ArrayList<explosion> explosionlist = new ArrayList<explosion>();
	public static ArrayList<blob> bloblist = new ArrayList<blob>();
	public static ArrayList<button> blobbedlist = new ArrayList();
	public static button cannonbutton;
	public static button firecannonbutton;
	public static button guidedcannonbutton;
	public static button crosshairbutton;
	public static int wave = 0;
	public static int lives = 20;
	public static int level = 1;
	public enum gamestate{STARTSCREEN, GAME}
	public enum tapmode {BUILD, SETTARGET}
	public static tapmode currentmode;
	public static boolean win;

	public static gamestate currentgamestate = gamestate.STARTSCREEN;


	public int spawndelay = 0;

	@Override
	public void create() {
		batch = new SpriteBatch();
		//spawnzombies();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1024, 600);
		cannonbutton = new button(400, 575, Resources.cannonTexture);
		firecannonbutton = new button(465, 575, Resources.firecannontexture);
		guidedcannonbutton = new button(525, 575, Resources.guidedtexture);
		crosshairbutton = new button (-100, -100, Resources.crosshairtexture);
		currentmode = tapmode.BUILD;
		win = false;


	}

	@Override
	public void render() {

		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		update();
		camera.update();
		batch.setProjectionMatrix(camera.combined);

		batch.begin();

		if (currentgamestate == gamestate.STARTSCREEN){
			batch.draw(Resources.menutexture, 0, 0);
		}

		if (currentgamestate == gamestate.GAME) {

			if (level == 1) {
				batch.draw(Resources.backgroundTexture, 0, 0);
			}
			if (level == 2) {
				batch.draw(Resources.level2texture, 0, 0);
			}
			for (int i = 0; i < cannonlist.size(); i++) {
				cannonlist.get(i).draw(batch);
			}
			for (int i = 0; i < zombielist.size(); i++) {
				zombielist.get(i).draw(batch);
			}
			for (int i = 0; i < bulletlist.size(); i++) {
				bulletlist.get(i).draw(batch);
			}
			for (int i = 0; i < explosionlist.size(); i++) {
				explosionlist.get(i).draw(batch);
			}
			for (int i = 0; i < bloblist.size(); i++) {
				bloblist.get(i).draw(batch);
			}
			for (int i = 0; i < blobbedlist.size(); i++) {
				blobbedlist.get(i).draw(batch);
			}


			firecannonbutton.draw(batch);
			cannonbutton.draw(batch);
			guidedcannonbutton.draw(batch);
			crosshairbutton.draw(batch);
			ui.draw(batch);

		}
		batch.end();
	}

	public void controls() {
		if (Gdx.input.justTouched()) {

			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);

			if (currentgamestate == gamestate.STARTSCREEN) {
				if (touchPos.x >= 45 && touchPos.x <= 345 && touchPos.y >= 45 && touchPos.y <= 165){
					currentgamestate = gamestate.GAME;
				}
			}

			else if (currentgamestate == gamestate.GAME) {

				if (crosshairbutton.isclicked((int) touchPos.x, (int) touchPos.y)) {
					currentmode = tapmode.SETTARGET;
				}

				else if (currentmode == tapmode.SETTARGET) {
					if (touchPos.y < 500) {
						crosshairbutton.xpos = touchPos.x - Resources.crosshairtexture.getWidth() / 2;
						crosshairbutton.ypos = touchPos.y + Resources.crosshairtexture.getHeight() / 2;
						crosshairbutton.rect.setX(touchPos.x - 25);
						crosshairbutton.rect.setY(touchPos.y - Resources.crosshairtexture.getHeight() / 2);
						//currentmode = tapmode.BUILD;
					}
				}
				else if (isbuildable((int) touchPos.x, (int) touchPos.y) && ui.money >= 10 && currentmode == tapmode.BUILD) {
					if (selectedTower == TowerType.CANNON) {
						cannonlist.add(new cannon((int) touchPos.x, (int) touchPos.y));
						ui.money -= 10;
						removetowerstack();
					}
					if (selectedTower == TowerType.FIRECANNON && ui.money >= 25) {
						cannonlist.add(new firecannon((int) touchPos.x, (int) touchPos.y));
						ui.money -= 25;
						removetowerstack();
					}
					if (selectedTower == TowerType.GUIDED && ui.money >= 50) {
						cannonlist.add(new guidedcannon((int) touchPos.x, (int) touchPos.y));
						ui.money -= 50;
						removetowerstack();
						currentmode = tapmode.SETTARGET;
					}

				}
				if (cannonbutton.isclicked((int) touchPos.x, (int) touchPos.y)) {
					selectedTower = TowerType.CANNON;
					currentmode = tapmode.BUILD;
				}
				if (firecannonbutton.isclicked((int) touchPos.x, (int) touchPos.y)) {
					selectedTower = TowerType.FIRECANNON;
					currentmode = tapmode.BUILD;
				}
				if (guidedcannonbutton.isclicked((int) touchPos.x, (int) touchPos.y)) {
					selectedTower = TowerType.GUIDED;
					currentmode = tapmode.BUILD;
				}

				if (!blobbedlist.isEmpty()) {
					for (int i = 0; i < blobbedlist.size(); i++) {
						for (int j = 0; j < cannonlist.size(); j++) {
							if (blobbedlist.get(i).isclicked((int) touchPos.x, (int) touchPos.y) && cannonlist.get(j).getRectangle().contains(touchPos.x, touchPos.y)) {
								blobbedlist.remove(i);
								cannonlist.get(j).firedelay -= 5;
								i = blobbedlist.size() + 1;
								j = cannonlist.size() + 1;
							}
						}
					}
				}
			}
		}

	}


	public void spawnzombies() {
		if (zombielist.isEmpty() && win == false){
			wave++;
			if (level == 1 && wave == 10) {
				level = 2;
				cannonlist.clear();
				zombielist.clear();
				bulletlist.clear();
				explosionlist.clear();
				blobbedlist.clear();
				bloblist.clear();
				crosshairbutton.xpos = -100;
				crosshairbutton.ypos = -100;
				wave = 1;
			}
if (wave > 2 && win == false) {
	for (int i = 0; i < 2 + wave; i++) {
		zombielist.add(new fastzombie(1280 + i * 50, 285, wave));
	}
}
	if (level == 2 && win == false) {
		for (int i = 0; i < 2 + wave; i++) {
			zombielist.add(new throwzombie(1280 + (i * 50), 285, 1 + wave));
		}
	}
			for(int i = 0; i < 5 + wave;i++)
			{
				zombielist.add(new zombie(1280 + i * 50, 285, 4 + 2*wave));
			}
			if (level == 2 && wave ==66){
				win = true;
				zombielist.clear();
				bulletlist.clear();
				explosionlist.clear();
			}
		}


}


	public boolean isbuildable(float x, float y) {

			if (level == 1) {
				return (y < 500 && y > 300 || y < 200);
			}

			else if (level == 2) {
				if (y > 500) {
					return false;
				}
				if ((!(x <= 565 && x >= 0 && y <= 200 && y >= 100))
						&& (!(x <= 565 && x >= 400 && y <= 500 && y >= 180))
						&& (!(x <= 875 && x >= 550 && y <= 500 && y >= 395))
						&& (!(x <= 875 && x >= 700 && y <= 395 && y >= 185))
						&& (!(x <= 1024 && x >= 825 && y <= 365 && y >= 185)) && (!(x > 990))) {
					return true;
				}
			}
			return false;
	}



	public void removetowerstack() {
		if (cannonlist.size() > 1)
			for (int i = 0; i < cannonlist.size() - 1; i++) {
				if (cannonlist.get(i).xpos == cannonlist.get(cannonlist.size()-1).xpos &&
						(cannonlist.get(i).ypos == cannonlist.get(cannonlist.size()-1).ypos))
				{
					cannonlist.remove(cannonlist.size()-1);
					if (selectedTower == TowerType.CANNON){
						ui.money += 10;
					}
					if (selectedTower == TowerType.FIRECANNON){
						ui.money += 25;
					}
					if (selectedTower == TowerType.GUIDED){
						ui.money += 50;
					}
				}
			}
	}
	public void checkcollisions()
	{
		if (!zombielist.isEmpty())
		{
			for (int i = 0; i < bulletlist.size(); i++)
			{
				for (int j = 0; j < zombielist.size(); j++)
				{
					if (Intersector.overlaps(bulletlist.get(i).getCircle(), zombielist.get(j).getRectangle()))
					{
						zombielist.get(j).takedmg();
						explosionlist.add(new explosion(bulletlist.get(i).xpos, bulletlist.get(i).ypos));
						bulletlist.get(i).active = false;
					}
				}
			}
		}

		if (!cannonlist.isEmpty())
		{
			for (int i = 0; i < bloblist.size(); i++)
			{
				for (int j = 0; j < cannonlist.size(); j++)
				{
					if (Intersector.overlaps(bloblist.get(i).getCircle(), cannonlist.get(j).getRectangle())) {
						if (cannonlist.get(j).firedelay < 85)
						{
							bloblist.get(i).active = false;
							cannonlist.get(j).firedelay += 5;
							blobbedlist.add(new button(cannonlist.get(j).xpos, cannonlist.get(j).ypos + cannonlist.get(j).height, Resources.blobbedtexture));
						}
					}
				}
			}
		}
	}
	public void removesprites()
	{
		for (int i = 0; i < zombielist.size(); i ++)
		{
			if (!zombielist.get(i).active)
			{
				zombielist.remove(i);
			}

		}
		for (int i = 0; i < bulletlist.size(); i ++)
		{
			if (!bulletlist.get(i).active)
			{
				bulletlist.remove(i);
			}

		}
		for (int i = 0; i < explosionlist.size(); i ++) {
			if (!explosionlist.get(i).active) {
				explosionlist.remove(i);
			}
		}
		for (int i = 0; i < bloblist.size(); i ++) {
			if (!bloblist.get(i).active) {
				bloblist.remove(i);
			}
		}
	}
	public void update()
	{
		controls();

		if (currentgamestate == gamestate.GAME) {
			checkcollisions();
			spawnzombies();

			if (lives > 0) {

				for (int i = 0; i < zombielist.size(); i++) {
					zombielist.get(i).update();
				}
				for (int i = 0; i < bulletlist.size(); i++) {
					bulletlist.get(i).update();
				}
				for (int i = 0; i < cannonlist.size(); i++) {
					cannonlist.get(i).update();
				}
				for (int i = 0; i < explosionlist.size(); i++) {
					explosionlist.get(i).update();
				}
				for (int i = 0; i < bloblist.size(); i++) {
					bloblist.get(i).update();
				}
			}
		}
		removesprites();

	}
	@Override
	public void dispose () {

	}
}
