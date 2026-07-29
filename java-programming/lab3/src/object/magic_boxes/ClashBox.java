package object.magic_boxes;

import object.box.MagicBoxes;

class ClashBox extends MagicBoxes {
    public ClashBox(String type, double weight, String material, boolean flicker) {
        super(type, weight, material, flicker);
    }

    @Override
    public void teleport() {
        System.out.println("The ClashBox телепортирует героя на арену...");
    }

    @Override
    public void explode() {
        System.out.println("The ClashBox взрывается с конфетти!");
    }

    @Override
    public void openBox() {
        System.out.println("Открытие the ClashBox для нахождения легендарных предметов...");
    }
}
