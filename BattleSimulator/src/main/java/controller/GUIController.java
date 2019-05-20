package controller;

import model.Character;
import model.Planet;
import model.ReadJSON;
import view.View;

import java.util.List;

public class GUIController {

    View view=new View();
    private List<Character> characters;
    private List<Planet> planets;
    private List<Thread> threads;
    private ReadJSON json=new ReadJSON();
    private Battle battle=new Battle();
    Character hero=null;
    Character hero2=new Character();
    Character hero3=new Character();
    Character villain=null;
    Planet planet=null;


    public GUIController()  {
        try {
            view.getPanel_1().setVisible(false);
            view.getPanel_2().setVisible(false);
            view.getFrame().setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
            view.ActionComboHero(a->{
                view.getTextFieldHeroLife1().setText(""+view.getComboBoxHero().getHealth());
                view.getTextHero1Name().setText(view.getComboBoxHero().getName());
                view.getTextArea().append("hero desc.:"+view.getComboBoxHero().getDescription()+"\n");
                view.getTextArea().append("hero attack:"+view.getComboBoxHero().getAttack()+"\n"
                        +"**********************************\n");
                this.hero=view.getComboBoxHero();
            });
        view.ActionComboVillain(a->{
            view.getTextFieldVillainLife().setText(""+view.getComboBoxVillain().getHealth());
            view.getTextVillianName().setText(view.getComboBoxVillain().getName());
            view.getTextArea().append("villain desc.:"+view.getComboBoxVillain().getDescription()+"\n");
            view.getTextArea().append("villain attack:"+view.getComboBoxVillain().getAttack()+"\n"
                    +"**********************************\n");
            this.villain=view.getComboBoxVillain();
        });
        view.ActionComboPlanet(a->{
            view.getTextArea().append(view.getComboBoxPlanet().getName()+"\n"+
                    view.getComboBoxPlanet().getDescription()+"\n"
            +"hero attack modifier;"+view.getComboBoxPlanet().getModifiers().getHeroAttackModifier()+"\n"
            +"hero health modifier:"+view.getComboBoxPlanet().getModifiers().getHeroHealthModifier()+"\n"
                    +"villain attack modifier;"+view.getComboBoxPlanet().getModifiers().getVillainAttackModifier()+"\n"
                    +"villain health modifier:"+view.getComboBoxPlanet().getModifiers().getVillainHealthModifier()+"\n"
                    +"**********************************\n");
            this.planet=view.getComboBoxPlanet();
        });
        view.ActionStartBattle(a -> {
            try {
                this.battle.modify(this.hero, this.villain, this.planet);
                while (hero.getHealth() != 0 && villain.getHealth() != 0) {
                    startBattle();
                }
                view.getTextArea().append(battle.winner(villain));
            }catch (NullPointerException e){
                e.getStackTrace();
                view.getTextArea().setText("\nPlease check if you choose the characters and the planet!\nThank you!");
            }
            view.revalidate();
            view.repaint();
        });
        view.ActionCreateTeam(a->{
            view.getPanel_1().setVisible(true);
            view.getPanel_2().setVisible(true);
            view.getTextArea().append("We are still working on this update:D\nPlease press NewBattle button!:)\nThank you!\n");
            view.revalidate();
            view.repaint();
        });
        view.ActionNewBattle(a-> {
            view.getTextFieldHeroLife1().setText("");
            view.getTextFieldVillainLife().setText("");
            view.getTextVillianName().setText("");
            view.getTextHero1Name().setText("");
            view.getTextArea().setText("");
            view.getPanel_1().setVisible(false);
            view.getPanel_2().setVisible(false);
            hero=null;
            villain=null;
            planet=null;
            view.revalidate();
            view.repaint();
        });
    }
    public void startBattle(){
        this.battle.fight(this.hero, this.villain);
        view.getTextFieldHeroLife1().setText("" + view.getComboBoxHero().getHealth());
        view.getTextFieldVillainLife().setText("" + view.getComboBoxVillain().getHealth());
        view.getTextArea().append("Hero:health " + this.hero.getHealth() + "\nattack: " + this.hero.getRandomAttack() + "\n");
        view.getTextArea().append("Villain:health " + this.villain.getHealth() + "\nattack: " + this.villain.getRandomAttack() + "\n");
    }
}
