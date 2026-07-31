package commands;

import manager.CollectionManager;
import utils.XmlUtils;

public class SaveCommand implements Command {
    private final CollectionManager cm;
    private final XmlUtils xu;
    public SaveCommand(CollectionManager cm, XmlUtils xu) {
        this.cm = cm;
        this.xu = xu;
    }
    @Override
    public String execute(String args) {
        cm.save(xu);
        return "Коллекция сохранена.";
    }
}

