package server;

import manager.CollectionManager;
import manager.CommandHandler;
import common.utils.XmlUtils;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerMain {
    private static final int PORT = 5000;
    private static final Logger LOGGER = Logger.getLogger(ServerMain.class.getName());

    private final CollectionManager collectionManager;
    private final CommandHandler commandHandler;
    private final XmlUtils xmlUtils;

    public ServerMain(String fileName) {
        xmlUtils = new XmlUtils();
        collectionManager = new CollectionManager(fileName, xmlUtils);
        commandHandler = new CommandHandler(collectionManager);
    }

    public void start() throws IOException {
        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(PORT));
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        LOGGER.info("Сервер запущен на порту " + PORT);

        while (true) {
            selector.select();
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> it = selectedKeys.iterator();

            while (it.hasNext()) {
                SelectionKey key = it.next();

                if (key.isAcceptable()) {
                    handleAccept(key, selector);
                }

                it.remove();
            }
        }
    }

    private void handleAccept(SelectionKey key, Selector selector) throws IOException {
        ServerSocketChannel serverSocket = (ServerSocketChannel) key.channel();
        SocketChannel clientChannel = serverSocket.accept();
        clientChannel.configureBlocking(false);
        LOGGER.info("Новое подключение: " + clientChannel.getRemoteAddress());

        // Создаем новый поток для обработки клиента и передаем ему collectionManager
        new Thread(new ClientHandler(clientChannel, commandHandler, collectionManager)).start();
    }

    public static void main(String[] args) {
        // Настройка логгера для вывода в консоль
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);
        LOGGER.addHandler(consoleHandler);
        LOGGER.setLevel(Level.ALL);
        LOGGER.setUseParentHandlers(false);

        try {
            new ServerMain("collection.xml").start();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Ошибка запуска сервера: " + e.getMessage(), e);
            e.printStackTrace();
        }
    }
}




