import creature.enemy.Enemy;
import creature.hero.Hero;
import exceptions.HutBurnedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import place.arena.ClashArena;
import place.palace.ImperiumHominis;
import place.wood_house.Hut;
import place.enumeration.WorldTypeEnum;
import object.average_boxes.Box;


public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        // Создание героя
        Hero hero = new Hero("Артур", true, "Древний Амулет");

        // Создание мест
        Hut hut = new Hut("Хижина Из Ящиков", WorldTypeEnum.EARTH, "Легендарная", false);
        try {
            hut.visit();
            hut.fire();
            hut.visit();
        } catch (HutBurnedException e) {
            System.err.println(e.getMessage());
        }


        // Создание ящиков
        Box macaroniBox = new Box();
        Box tobaccoBox = new Box();
        Box soapBox = new Box();
        Box toothPowderBox = new Box();

        // Начало истории
        logger.info("Постепенно он начал, однако, припоминать, что уже где-то видел такую надпись, что она, в общем-то, ему очень и очень знакома.");
        logger.info("Присмотревшись, он убедился, что надпись на самом деле была сделана на длинном фанерном ящике из-под макарон и что вся хижина была сооружена из подобного рода ящиков.");
        logger.info("Здесь были ящики и из-под табака, с изображением коротышки с трубкой в зубах, и из-под мыла, с изображением намыленной головы, и из-под зубного порошка, с изображением зубов, сверкающих белизной.");

        // Исследование хижины
        hut.exist();
        hut.visit();
        logger.info("Артур обошел хижину, внимательно рассматривая надписи на ящиках.");

        // Открытие ящиков
        logger.info("Он подошел к ящику из-под макарон.");
        macaroniBox.openBox();
        logger.info("Внутри он нашел старую карту.");

        logger.info("Затем он осмотрел ящик из-под табака.");
        tobaccoBox.openBox();
        logger.info("В нем лежала труба, все еще пахнущая табаком.");

        logger.info("Далее он заглянул в ящик из-под мыла.");
        soapBox.openBox();
        logger.info("Там был небольшой мешочек с мыльной стружкой.");

        logger.info("Наконец, он открыл ящик из-под зубного порошка.");
        toothPowderBox.openBox();
        logger.info("Внутри оказался маленький флакон с неизвестной жидкостью.");

        // Продолжение путешествия
        logger.info("Собрав все находки, Артур решил следовать указаниям карты из ящика макарон.");
        logger.info("Его путь привел его к затерянному дворцу, скрытому в джунглях.");

        // Исследование дворца
        ImperiumHominis palace = new ImperiumHominis("Затерянный Дворец", WorldTypeEnum.EARTH, "Мистическая", true);
        palace.exist();
        palace.visit();
        palace.secure();

        // Битва
        ClashArena arena = new ClashArena("Арена Великой Битвы", WorldTypeEnum.CLASH_ROYAL, "Эпическая", true, "Боевая");
        Enemy goblin = new Enemy("Гоблин", 30, true);
        Enemy orc = new Enemy("Орк", 50, true);

        logger.info("Артур отправился на арену, чтобы сразиться с врагами.");
        arena.exist();
        arena.visit();
        arena.startBattle();

        logger.info("На арене героя встретил Гоблин!");
        goblin.fight();
        hero.fight();
        goblin.die();
        logger.info("Артур одолел Гоблина.");

        logger.info("Следующим врагом был Орк!");
        orc.fight();
        hero.fight();
        orc.die();
        logger.info("Артур одолел Орка и одержал победу на арене.");

        // Завершение истории
        logger.info("Внутри дворца Артур нашел древний артефакт, который должен был стать ключом к спасению его мира.");
        logger.info("Его путешествие только начиналось...");
    }
}
