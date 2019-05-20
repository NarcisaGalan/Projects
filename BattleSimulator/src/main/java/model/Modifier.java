package model;

public class Modifier {

    private int heroAttackModifier;
    private int heroHealthModifier;
    private int villainAttackModifier;
    private int villainHealthModifier;


    public Modifier(int heroAttackModifier, int heroHealthModifier, int villainAttackModifier, int villainHealthModifier) {
        this.heroAttackModifier = heroAttackModifier;
        this.heroHealthModifier = heroHealthModifier;
        this.villainAttackModifier = villainAttackModifier;
        this.villainHealthModifier = villainHealthModifier;
    }

    public int getHeroAttackModifier() {
        return heroAttackModifier;
    }

    public void setHeroAttackModifier(int heroAttackModifier) {
        this.heroAttackModifier = heroAttackModifier;
    }

    public int getHeroHealthModifier() {
        return heroHealthModifier;
    }

    public void setHeroHealthModifier(int heroHealthModifier) {
        this.heroHealthModifier = heroHealthModifier;
    }

    public int getVillainAttackModifier() {
        return villainAttackModifier;
    }

    public void setVillainAttackModifier(int villainAttackModifier) {
        this.villainAttackModifier = villainAttackModifier;
    }

    public int getVillainHealthModifier() {
        return villainHealthModifier;
    }

    public void setVillainHealthModifier(int villainHealthModifier) {
        this.villainHealthModifier = villainHealthModifier;
    }
}
