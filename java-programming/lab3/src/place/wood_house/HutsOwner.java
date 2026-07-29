package place.wood_house;

public record HutsOwner(String name, int age, String profession) {
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getProfession() {
        return profession;
    }
}
