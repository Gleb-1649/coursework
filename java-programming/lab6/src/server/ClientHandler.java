package server;

import common.network.CommandRequest;
import common.network.CommandResponse;
import common.model.Person;
import manager.CommandHandler;
import manager.CollectionManager;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientHandler implements Runnable {
    private final SocketChannel clientChannel;
    private final CommandHandler commandHandler;
    private final CollectionManager collectionManager;
    private static final Logger LOGGER = Logger.getLogger(ClientHandler.class.getName());

    public ClientHandler(SocketChannel clientChannel, CommandHandler commandHandler, CollectionManager collectionManager) {
        this.clientChannel = clientChannel;
        this.commandHandler = commandHandler;
        this.collectionManager = collectionManager;
    }

    @Override
    public void run() {
        try {
            clientChannel.configureBlocking(true);
            ObjectInputStream ois = new ObjectInputStream(clientChannel.socket().getInputStream());
            CommandRequest request = (CommandRequest) ois.readObject();
            LOGGER.info("Получена команда: " + request.getCommandName());

            String commandName = request.getCommandName();
            String result;
            if (commandName.equalsIgnoreCase("add")) {
                // При команде add ожидаем, что payload содержит объект Person
                Person person = (Person) request.getPayload();
                collectionManager.add(person);
                result = "Элемент добавлен (id = " + person.getId() + ")";
            } else {
                String fullCommand = commandName + " " + request.getPayload();
                result = commandHandler.handleCommand(fullCommand);
            }

            CommandResponse response = new CommandResponse(result);
            ObjectOutputStream oos = new ObjectOutputStream(clientChannel.socket().getOutputStream());
            oos.writeObject(response);
            oos.flush();

            clientChannel.close();
        } catch (IOException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Ошибка обработки клиента: " + e.getMessage(), e);
            try {
                clientChannel.close();
            } catch (IOException ex) {
                LOGGER.log(Level.WARNING, "Ошибка закрытия канала: " + ex.getMessage(), ex);
            }
        }
    }
}

