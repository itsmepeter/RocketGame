package be.khleuven.mobile.rocketgame.model;

import java.util.ArrayList;

public class RocketGame {
	private Rocket rocket;
	private int money;
	private String username;
	private ArrayList<Powerup> powerups;
	private double height;
	private int birds_hit;
	private boolean touching;
	private boolean playing;
	

	public RocketGame(){
		setRocket(new Rocket());
		setMoney(0);
		setUsername(null);
	}
	
	public RocketGame(Rocket rocket, int money, String username){
		setRocket(rocket);
		setMoney(money);
		setUsername(username);
	}

	public Rocket getRocket() {
		return rocket;
	}

	public void setRocket(Rocket rocket) {
		this.rocket = rocket;
	}	

	public boolean getTouching(){
		return touching;
	}
	
	public void setTouching(boolean touching){
		this.touching = touching;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public void addPowerup(Powerup powerup){
		powerups.add(powerup);
	}
	
	public ArrayList<Powerup> getPowerups(){
		return powerups;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double d) {
		this.height = d;
	}

	public int getBirds_hit() {
		return birds_hit;
	}

	public void setBirds_hit(int birds_hit) {
		this.birds_hit = birds_hit;
	}

	public void setPlaying(boolean b) {
		this.playing = true;
	}
	
	public boolean getPlaying(){
		return this.playing;
	}

}
