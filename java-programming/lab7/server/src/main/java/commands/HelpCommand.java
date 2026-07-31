package commands;

public class HelpCommand implements Command {
    private static final long serialVersionUID = 1L;

    @Override public String execute(String args) {

        return "help, info, show, add, update, remove_by_id, clear, shuffle, sort, remove_lower, count_less_than_location, print_ascending, print_unique_eye_color, execute_script, exit";
    }
}
