package creature.enemy;

import creature.character.AbstractEnemy;

public class Enemy extends AbstractEnemy {
    public Enemy(String name, int strength, boolean hostile) {
        super(name, strength, hostile);
    }

    @Override
    public void fight() {
        System.out.println(getName() + " борется с силой " + getStrength());
    }

    @Override
    public void kill() {
        System.out.println(getName() + " убивает свою цель");
    }

    @Override
    public void die() {
        System.out.println(getName() + " был побежден");
    }

    @Override
    public void eat() {
        System.out.println(getName() + "ест для восстановления энергии");
    }

    @Override
    public void drink() {
        System.out.println(getName() + " пьет чтобы избежать обезвоживания");
    }

    @Override
    public void attack() {
        System.out.println(getName() + " начинает атаку!");
    }
}
