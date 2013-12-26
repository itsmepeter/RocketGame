package be.khleuven.mobile.rocketgame.model;

import android.graphics.Bitmap;


public class Money extends SpaceObject {
	private int dmg;
	public Money(int x, int y, Bitmap image, int width) {
		super(x, y, image, width);
	}
	
	public void setDmg(int dmg){
		this.dmg = dmg;
	}
	
	public int getDmg(){
		return dmg;
	}

}
