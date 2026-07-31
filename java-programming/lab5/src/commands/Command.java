package commands;

/**
 * Интерфейс Command определяет метод execute для выполнения команды.
 */
public interface Command {
    String execute(String args);
}

