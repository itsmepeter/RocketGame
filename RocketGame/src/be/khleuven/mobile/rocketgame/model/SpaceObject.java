package be.khleuven.mobile.rocketgame.model;

import android.graphics.Bitmap;

public abstract class SpaceObject {
	private int x;
	private double y;
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
	public double getY() {
		return y;
	}
	public void setY(double d) {
		this.y = d;
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
