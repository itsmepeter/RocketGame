package be.khleuven.mobile.rocketgame.model;

import android.graphics.Bitmap;

public class Powerup extends SpaceObject{
	private int speedmodifier;
	private int rotationmodifier;
	private int duration;

	public Powerup(int x, int y, Bitmap image, int width, int speedmodifier, int rotationmodifier) {
		super(x, y, image, width);
		setSpeedmodifier(speedmodifier);
		setRotationmodifier(rotationmodifier);
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

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) throws Exception {
		if(duration < 0){
			throw new Exception("duration needs to be positive");
		}else{
			this.duration = duration;
		}
	}
}
