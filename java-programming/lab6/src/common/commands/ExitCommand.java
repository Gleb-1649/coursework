package common.commands;

public class ExitCommand implements Command {
    private static final long serialVersionUID = 1L;
    @Override
    public String execute(String args) {
        return "Выход из программы.";
    }
}


