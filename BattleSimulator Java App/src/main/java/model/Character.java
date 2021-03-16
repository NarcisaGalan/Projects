package model;

import java.util.Random;

public class Character {
	
	    private int id;
	    private String name;
	    private String description;
	    private int attack;
	    private int health;
	    private boolean isVillain;

	    public Character(){

		}

	    public Character(int id,String name, String description, int attack, int health, boolean isVillain) {
	        this.id = id;
	        this.name=name;
	        this.description = description;
	        this.attack = attack;
	        this.health = health;
	        this.isVillain = isVillain;
	    }

	    public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }

		public String getName() {
		return name;
		}

		public void setName(String name) {
		this.name = name;
		}

		public String getDescription() {
	        return description;
	    }

		public int getRandomAttack(){
			Random r = new Random();
			int low=(60/100) * this.attack;
			return r.nextInt(this.attack-low) + low;
		}

		public void setDescription(String description) {

	    	this.description = description;
	    }

	    public int getAttack() {
	        return attack;
	    }

	    public void setAttack(int attack) {
	        this.attack = attack;
	    }

	    public int getHealth() {
	        return health;
	    }

	    public void setHealth(int health) {
	        this.health = health;
	    }

	    public boolean isVillain() {
	        return isVillain;
	    }

	    public void setVillain(boolean villain) {
	        isVillain = villain;
	    }

	    public String toString(){

	    	return this.getName()+" h:"+this.getHealth()+" a:"+this.getAttack();
		}
	
}
