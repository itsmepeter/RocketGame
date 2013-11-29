package be.khleuven.mobile.rocketgame.model;

import java.util.ArrayList;

public class Rocket {
	private int x;
	private int y;
	private int speed;
	private int rotation;
	private int rotationspeed;
	private int health;
	private ArrayList<Upgrade> upgrades;
	
	public Rocket(){
		
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
	
	public void addUpgrade(Upgrade upgrade){
		upgrades.add(upgrade);
	}

	public int getRotation() {
		return rotation;
	}

	public void setRotation(int rotation) throws Exception {
		if(rotation > 60 || rotation < -60){
			throw new Exception("Rocket needs a rotation beween -60 and 60 degrees");
		}else{
			this.rotation = rotation;
		}
	}

	public ArrayList<Upgrade> getUpgrades() {
		return upgrades;
	}

	public void setUpgrades(ArrayList<Upgrade> upgrades) {
		this.upgrades = upgrades;
	}

	public int getRotationspeed() {
		return rotationspeed;
	}

	public void setRotationspeed(int rotationspeed) {
		this.rotationspeed = rotationspeed;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
}
