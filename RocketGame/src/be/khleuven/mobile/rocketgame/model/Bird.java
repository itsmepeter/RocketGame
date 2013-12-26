package be.khleuven.mobile.rocketgame.model;

import android.graphics.Bitmap;


public class Bird extends SpaceObject {
	private int dmg;
	public Bird(int x, int y, Bitmap image, int width) {
		super(x, y, image, width);
		setDmg(10);
	}
	
	public void setDmg(int dmg){
		this.dmg = dmg;
	}
	
	public int getDmg(){
		return dmg;
	}

}
