package commands;

public class ExitCommand implements Command {
    @Override
    public String execute(String args) {
        return "Выход из программы.";
    }
}

