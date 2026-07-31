package common.commands;

import manager.CollectionManager;
import common.utils.XmlUtils;

public class SaveCommand implements Command {
    private static final long serialVersionUID = 1L;
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
