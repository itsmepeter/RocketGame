package be.khleuven.mobile.rocketgame.model;

import java.util.ArrayList;

public class Rocket {
	private int x;
	private int y;
	private double speed;
	private float speedx;
	private double rotation;
	private float rotationspeed;
	private int health;
	private int fuel;
	private ArrayList<Upgrade> upgrades;
	
	public Rocket(){
		setHealth(100);
		rotation = 0;
		setFuel(1000);
		setSpeed(0);
	}
	
	public int getFuel(){
		return fuel;
	}
	
	public void setFuel(int fuel){
		this.fuel = fuel;
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
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double d) {
		this.speed = d;
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
	
	public void setRotation(double rotation){
		this.rotation = rotation;
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
