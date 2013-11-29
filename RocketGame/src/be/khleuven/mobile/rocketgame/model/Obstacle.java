package be.khleuven.mobile.rocketgame.model;

import android.graphics.Bitmap;

public class Obstacle extends SpaceObject{
	private int speedmodifier;
	private int healthmodifier;

	public Obstacle(int x, int y, Bitmap image, int width, int speedmodifier, int healthmodifier) {
		super(x, y, image, width);
		setSpeedmodifier(speedmodifier);
		setHealthmodifier(healthmodifier);
	}

	public int getSpeedmodifier() {
		return speedmodifier;
	}

	public void setSpeedmodifier(int speedmodifier) {
		this.speedmodifier = speedmodifier;
	}

	public int getHealthmodifier() {
		return healthmodifier;
	}

	public void setHealthmodifier(int healthmodifier) {
		this.healthmodifier = healthmodifier;
	}

}
