package main;


import pokemon.*;
import ru.ifmo.se.pokemon.Battle;

public class Main {

    public static void main(String[] args) {
        Battle b = new Battle();

        Volcanion volcanion = new Volcanion("Волчок", 50);
        Cubchoo cubchoo = new Cubchoo("Кубик", 50);
        Beartic beartic = new Beartic("Артик", 50);
        Oddish oddish = new Oddish("Один", 50);
        Gloom gloom = new Gloom("Груд", 50);
        Vileplume vileplume = new Vileplume("Валентин", 50);

        b.addAlly(gloom);
        b.addAlly(beartic);
        b.addAlly(cubchoo);

        b.addFoe(oddish);
        b.addFoe(volcanion);
        b.addFoe(vileplume);

        b.go();
    }
}


