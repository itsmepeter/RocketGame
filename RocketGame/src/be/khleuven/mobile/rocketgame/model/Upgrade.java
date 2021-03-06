package be.khleuven.mobile.rocketgame.model;

import android.graphics.Bitmap;

public class Upgrade {
	private int price;
	private Bitmap image;
	private int speedmodifier;
	private int rotationmodifier;
	
	private Upgrade(int price, Bitmap image, int speedmodifier, int rotationmodifier){
		setPrice(price);
		setImage(image);
		setSpeedmodifier(speedmodifier);
		setRotationmodifier(rotationmodifier);
	}
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Bitmap getImage() {
		return image;
	}
	public void setImage(Bitmap image) {
		this.image = image;
	}
	public int getSpeedmodifier() {
		return speedmodifier;
	}
	public void setSpeedmodifier(int speedmodifier) {
		this.speedmodifier = speedmodifier;
	}
	public int getRotationmodifier() {
		return rotationmodifier;
	}
	public void setRotationmodifier(int rotationmodifier) {
		this.rotationmodifier = rotationmodifier;
	}
}
