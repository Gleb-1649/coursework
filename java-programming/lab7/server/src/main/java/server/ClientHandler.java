package server;

import common.network.CommandRequest;
import common.network.CommandResponse;
import manager.CommandHandler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

public class ClientHandler implements Runnable {
    private final Socket socket;
    private final CommandHandler handler;
    private final ExecutorService workPool;
    private final ExecutorService writePool;

    public ClientHandler(Socket socket,
                         CommandHandler handler,
                         ExecutorService workPool,
                         ExecutorService writePool) {
        this.socket = socket;
        this.handler = handler;
        this.workPool = workPool;
        this.writePool = writePool;
    }

    @Override
    public void run() {
        try (Socket ignored = socket;

             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream()))
        {
            oos.flush();

            while (true) {
                CommandRequest req = (CommandRequest) ois.readObject();

                CompletableFuture
                        .supplyAsync(() -> handler.handle(req), workPool)
                        .thenAcceptAsync(resp -> {
                            try {
                                oos.writeObject(resp);
                                oos.flush();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }, writePool)
                        .join();

                if ("exit".equals(req.getCommandName())) {
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

