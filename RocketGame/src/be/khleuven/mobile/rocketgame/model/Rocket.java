package be.khleuven.mobile.rocketgame.model;

import java.util.ArrayList;

public class Rocket {
	private int x;
	private int y;
	private int speed;
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
}
