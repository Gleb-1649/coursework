package server;

import storage.DataStorage;
import storage.StorageFactory;
import storage.StorageException;
import manager.UserManager;
import manager.CollectionManager;
import manager.CommandHandler;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerMain {
    private static final int PORT = 5000;

    public static void main(String[] args) throws Exception {

        System.setProperty("org.slf4j.simpleLogger.log.org.mongodb.driver", "error");


        DataStorage storage = StorageFactory.create();
        UserManager  userMgr  = new UserManager(storage);
        CollectionManager collMgr = new CollectionManager(storage);
        CommandHandler handler = new CommandHandler(userMgr, collMgr);


        ExecutorService workPool = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors()
        );
        ExecutorService writePool = Executors.newCachedThreadPool();

        try (ServerSocket server = new ServerSocket(PORT)) {
            System.out.println("Server listening on port " + PORT);

            while (true) {

                Socket client = server.accept();
                System.out.println("Client connected: " + client.getRemoteSocketAddress());


                new Thread(() -> {

                    new ClientHandler(client, handler, workPool, writePool).run();
                }).start();
            }
        }
    }
}