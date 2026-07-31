package app.server;

import core.dao.UserDao;
import core.dao.LabWorkDao;
import app.managers.CommandManager;
import core.protocol.CommandRequest;
import core.protocol.CommandResponse;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Logger;

public class Session implements Runnable {
    private static final Logger log = Logger.getLogger("Session");

    private final Socket socket;
    private final CommandManager cmdMgr;

    public Session(Socket socket, UserDao udao, LabWorkDao lwDao) {
        this.socket = socket;
        this.cmdMgr = new CommandManager(udao, lwDao);
    }

    @Override
    public void run() {
        try (
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream  ois = new ObjectInputStream(socket.getInputStream())
        ) {
            // 1) авторизация
            CommandRequest authReq = (CommandRequest) ois.readObject();
            CommandResponse authResp = cmdMgr.dispatch(authReq);
            oos.writeObject(authResp);
            if (!authResp.isSuccess()) return;
            cmdMgr.setCurrentLogin(((String[])authReq.getPayload())[0]);

            // 2) цикл приёма
            while (true) {
                CommandRequest req = (CommandRequest) ois.readObject();
                if ("exit".equalsIgnoreCase(req.getCommandName())) {
                    // послать выход в клиент
                    new Thread(() -> {
                        try { oos.writeObject(new CommandResponse(true, "BYE", null)); }
                        catch (Exception ignore){}
                    }).start();
                    break;
                }

                // Обработка и отправка — в своих потоках
                new Thread(() -> {
                    // 2.1) Обрабатываем команду
                    CommandResponse resp = cmdMgr.dispatch(req);
                    // 2.2) Отправляем ответ тоже в отдельном потоке
                    new Thread(() -> {
                        try {
                            oos.writeObject(resp);
                        } catch (Exception e) {
                            log.warning("Не удалось отправить ответ: " + e.getMessage());
                        }
                    }).start();
                }).start();
            }

        } catch (Exception ex) {
            log.warning("Session error: " + ex.getMessage());
        }
    }
}
