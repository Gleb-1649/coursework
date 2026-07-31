package common.commands;

public class HelpCommand implements Command {
    private static final long serialVersionUID = 1L;
    @Override
    public String execute(String args) {
        return "Доступные команды:\n" +
                "help : вывести справку по командам\n" +
                "info : вывести информацию о коллекции\n" +
                "show : вывести все элементы коллекции\n" +
                "add {element} : добавить новый элемент\n" +
                "update id {element} : обновить элемент по id\n" +
                "remove_by_id id : удалить элемент по id\n" +
                "clear : очистить коллекцию\n" +
                "save : сохранить коллекцию в файл (серверная команда)\n" +
                "execute_script file_name : выполнить скрипт из файла\n" +
                "exit : завершить программу\n" +
                "shuffle : перемешать элементы\n" +
                "remove_lower {element} : удалить элементы меньше заданного\n" +
                "sort : отсортировать коллекцию\n" +
                "count_less_than_location location : вывести количество элементов, у которых location меньше заданного\n" +
                "print_ascending : вывести элементы в порядке возрастания\n" +
                "print_unique_eye_color : вывести уникальные значения поля eyeColor";
    }
}

