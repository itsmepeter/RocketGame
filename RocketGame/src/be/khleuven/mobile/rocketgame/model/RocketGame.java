package be.khleuven.mobile.rocketgame.model;

import java.util.ArrayList;

public class RocketGame {
	private Rocket rocket;
	private int money;
	private String username;
	private ArrayList<Powerup> powerups;
	private int height;
	

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

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
