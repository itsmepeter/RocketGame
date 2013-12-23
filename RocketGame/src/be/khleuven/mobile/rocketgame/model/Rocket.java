package be.khleuven.mobile.rocketgame.model;

import java.util.ArrayList;

import android.graphics.Matrix;
import android.util.Log;

public class Rocket {
	private int x;
	private int y;
	private int speed;
	private float speedx;
	private double rotation;
	private float rotationspeed;
	private int health;
	private ArrayList<Upgrade> upgrades;
	public Matrix rotator;
	
	public Rocket(){
		rotation = 0;
		rotator = new Matrix();
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
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public float getSpeedX(){
		return speedx;
	}
	
	public void setSpeedX(float speedx){
		this.speedx = speedx;
	}
	public void addUpgrade(Upgrade upgrade){
		upgrades.add(upgrade);
	}

	public double getRotation() {
		return rotation;
	}
	
	public Matrix getRotator(){
		return rotator;
	}

	public void setRotation(int rocketwidth, int rocketheight){
		double r =  -getRotationspeed();
		rotator.postRotate(0);
		/*if(r > 0 && r < 0.5){
			if(rotation + 0.5 <= 45 && rotation + 0.5 >= -45){
				rotator.postRotate((float) 0.5);
				rotation += 0.5;
			}
		} else if (r < 0 && r > -0.5) {
			if(rotation - 0.5 <= 45 && rotation -0.5 >= -45){
				rotation -= 0.5;
				rotator.postRotate((float) -0.5);
			}
		} else if (r < -0.5) {
				if(rotation - 3 <= 45 && rotation -3 >= -45){
					rotation -= 3;
					rotator.postRotate((float) -3);
				}
		} else if (r > 0.5) {
			if(rotation + 3 <= 45 && rotation  +3 >= -45){
				rotation += 3;
				rotator.postRotate((float) 3);
			}
		}*/

		if(r > 0 ){
			if(rotation + r*10 <= 45 && rotation + r*10 >= -45){
				rotator.setTranslate(x, y);
				rotator.postRotate((float) r*10, getX() + rocketwidth/2 ,  getY() + rocketheight/2);
				rotation += r*10;
			}
		} else if (r < 0 ) {
			if(rotation + r*10 <= 45 && rotation +r*10 >= -45){
				rotator.setTranslate(x, y);
				rotation += r*10;			
				rotator.postRotate((float) r*10 , getX() + rocketwidth/2 ,  getY() + rocketheight/2);
			}
		} 
	}

	public ArrayList<Upgrade> getUpgrades() {
		return upgrades;
	}

	public void setUpgrades(ArrayList<Upgrade> upgrades) {
		this.upgrades = upgrades;
	}

	public float getRotationspeed() {
		return rotationspeed;
	}

	public void setRotationspeed(float rotationspeed) {
		this.rotationspeed = rotationspeed;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	
	public void updatePosition(float sx, float sy, float sz, long timestamp){
		float seconden = (System.nanoTime() - timestamp) / 1000000000.0f;
		rotationspeed = -sx * seconden * 4;
		
		//x += speedx * seconden;
		rotation += rotation + rotationspeed * seconden;
	}
}
