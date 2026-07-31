package client;

import common.network.CommandRequest;
import common.network.CommandResponse;
import common.model.Person;
import common.utils.UserInputUtils;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientMain {
    // Для локального теста можно указать "localhost"
    private static final String SERVER_HOST = "helios.cs.ifmo.ru"; // или нужный адрес
    private static final int SERVER_PORT = 5000;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Создаем экземпляр UserInputUtils. Если его конструктор требует CollectionManager, можно передать null (при условии, что он не используется)
        UserInputUtils userInputUtils = new UserInputUtils(null);

        while (true) {
            System.out.print("Введите команду (или 'exit' для выхода): ");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Завершение работы клиента.");
                break;
            }

            String commandName;
            Object payload = null;
            String commandArgs = "";
            if (input.equalsIgnoreCase("add")) {
                commandName = "add";

                Person person = userInputUtils.readPerson();
                payload = person;
            } else {
                String[] parts = input.split(" ", 2);
                commandName = parts[0];
                if (parts.length > 1) {
                    commandArgs = parts[1];
                }
            }


            if (commandName.equalsIgnoreCase("save")) {
                System.out.println("Команда 'save' недоступна в клиентском приложении.");
                continue;
            }

            CommandRequest request = new CommandRequest(commandName,
                    (payload != null) ? payload : commandArgs);

            try (Socket socket = new Socket(SERVER_HOST, SERVER_PORT)) {
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                oos.writeObject(request);
                oos.flush();

                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                CommandResponse response = (CommandResponse) ois.readObject();
                System.out.println("Ответ сервера: " + response.getResponse());
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Ошибка при соединении с сервером: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}


