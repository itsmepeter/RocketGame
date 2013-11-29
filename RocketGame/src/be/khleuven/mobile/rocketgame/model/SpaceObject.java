package be.khleuven.mobile.rocketgame.model;

import android.graphics.Bitmap;

public abstract class SpaceObject {
	private int x;
	private int y;
	private int width;
	private Bitmap image;
	
	public SpaceObject(int x, int y, Bitmap image, int width){
		setX(x);
		setY(y);
		setImage(image);
		setWidth(width);
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public Bitmap getImage() {
		return image;
	}
	public void setImage(Bitmap image) {
		this.image = image;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
}
