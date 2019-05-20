package model;

public class Planet {

	   private int id;
	   private String name;
	   private String description;
	   private Modifier modifiers;

	   public Planet(){

	   }

	public Planet(int id, String name, String description, Modifier modifiers) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.modifiers = modifiers;
	}

	public String toString(){

		return this.getName();
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

	public void setDescription(String description) {
		this.description = description;
	}

	public Modifier getModifiers() {
		return modifiers;
	}

	public void setModifiers(Modifier modifiers) {
		this.modifiers = modifiers;
	}
}
