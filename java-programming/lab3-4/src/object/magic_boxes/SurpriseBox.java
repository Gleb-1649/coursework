package object.magic_boxes;

import object.box.MagicBoxes;

// Concrete Class SurpriseBox
class SurpriseBox extends MagicBoxes {
    private String specialReward;

    public SurpriseBox(String type, double weight, String material, boolean flicker, String specialReward) {
        super(type, weight, material, flicker);
        this.specialReward = specialReward;
    }

    public String getSpecialReward() {
        return specialReward;
    }

    public void setSpecialReward(String specialReward) {
        this.specialReward = specialReward;
    }

    @Override
    public void teleport() {
        System.out.println("The SurpriseBox телепортирует вас на неизвестную локацию...");
    }

    @Override
    public void explode() {
        System.out.println("The SurpriseBox взрывается, открывая неожиданную награду: " + specialReward);
    }

    @Override
    public void openBox() {
        System.out.println("Открытие  the SurpriseBox... Что это может быть?");
    }
}