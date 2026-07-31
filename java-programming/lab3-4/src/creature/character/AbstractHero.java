package creature.character;

import creature.Creature;

import java.util.Objects;

public abstract class AbstractHero implements Creature {
    private String name;
    private boolean alive;

    public AbstractHero(String name, boolean alive) {
        this.name = name;
        this.alive = alive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public abstract void defend();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractHero that = (AbstractHero) o;
        return alive == that.alive && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, alive);
    }

    @Override
    public String toString() {
        return "AbstractHero{" +
                "name='" + name + '\'' +
                ", alive=" + alive +
                '}';
    }
}