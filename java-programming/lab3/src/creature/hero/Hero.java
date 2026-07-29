package creature.hero;

import creature.character.AbstractHero;

public class Hero extends AbstractHero {
    private String power;

    public Hero(String name, boolean alive, String power) {
        super(name, alive);
        this.power = power;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    @Override
    public void defend() {
        System.out.println(getName() + " защаищается...");
    }

    @Override
    public void fight() {
        System.out.println(getName() + " борется...");
    }

    @Override
    public void kill() {
        System.out.println(getName() + " убивает...");
    }

    @Override
    public void die() {
        System.out.println(getName() + " умер");
    }

    @Override
    public void eat() {
        System.out.println(getName() + " ест...");
    }

    @Override
    public void drink() {
        System.out.println(getName() + " пьет...");
    }
}
