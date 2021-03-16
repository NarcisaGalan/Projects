package controller;

import model.Character;
import model.Planet;

public class Battle{

    private  boolean isTurn = true;//the hero starts

    public String modify(Character hero, Character villain, Planet planet){
        try {

            hero.setHealth(hero.getHealth() + planet.getModifiers().getHeroHealthModifier());
            villain.setHealth(villain.getHealth() + planet.getModifiers().getVillainHealthModifier());
            hero.setAttack(hero.getAttack() + planet.getModifiers().getHeroAttackModifier());
            villain.setAttack(villain.getAttack() + planet.getModifiers().getVillainAttackModifier());
        } catch (Exception e) {
            e.getStackTrace();
        }
        return "Planet modiifiers aplied\n";
    }
    public void fight(Character hero, Character villain) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            System.out.println("INTERUPTED DELAY");
        }
        if (isTurn) {
            System.out.println("Hero's turn");
            villain.setHealth(villain.getHealth() - hero.getRandomAttack());

            if (villain.getHealth() < 0) {
                villain.setHealth(0);
            }
            isTurn = false;
        } else {
            System.out.println("Villian's turn");
            hero.setHealth(hero.getHealth() - villain.getRandomAttack());

            if (hero.getHealth() < 0) {
                hero.setHealth(0);
            }
            isTurn = true;
        }
    }
    public String winner(Character villain){
        if (villain.getHealth()== 0 || villain.getHealth() < 0) {
            return "Victoryyyyyyyyyy!!!!!!!\n";
        }else{
            return "You're dead!\n";
        }
    }
}
