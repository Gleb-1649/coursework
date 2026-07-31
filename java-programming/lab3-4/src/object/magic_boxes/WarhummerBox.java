package object.magic_boxes;

import object.box.MagicBoxes;



class WarhummerBox extends MagicBoxes {
    public WarhummerBox(String type, double weight, String material, boolean flicker) {
        super(type, weight, material, flicker);
    }

    @Override
    public void teleport() {
        System.out.println("The WarhummerBox телепортирует вас в зону войны...");
    }

    @Override
    public void explode() {
        System.out.println("The WarhummerBox взрывается с громким взрывом!");
    }

    @Override
    public void openBox() {
        System.out.println("Открытие the WarhummerBox для обнаружения античных оружий...");
    }
}