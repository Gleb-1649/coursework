package creature.character;

import creature.Creature;

import java.util.Objects;

public abstract class AbstractEnemy implements Creature {
    private String name;
    private int strength;
    private boolean hostile;

    public AbstractEnemy(String name, int strength, boolean hostile) {
        this.name = name;
        this.strength = strength;
        this.hostile = hostile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public boolean isHostile() {
        return hostile;
    }

    public void setHostile(boolean hostile) {
        this.hostile = hostile;
    }

    @Override
    public abstract void fight();

    @Override
    public abstract void kill();

    @Override
    public abstract void die();

    @Override
    public abstract void eat();

    @Override
    public abstract void drink();

    public abstract void attack();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEnemy that = (AbstractEnemy) o;
        return strength == that.strength && hostile == that.hostile && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, strength, hostile);
    }

    @Override
    public String toString() {
        return "AbstractEnemy{" +
                "name='" + name + '\'' +
                ", strength=" + strength +
                ", hostile=" + hostile +
                '}';
    }
}