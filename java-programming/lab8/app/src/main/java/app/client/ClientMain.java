package app.client;

import app.managers.InputManager;
import core.objects.LabWork;
import core.protocol.CommandRequest;
import core.protocol.CommandResponse;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * Клиент: авторизация, потом интерактивные команды.
 * При потере связи — авто-переподключение и повторный login.
 */
public class ClientMain {
    private static final String HOST = "localhost";
    private static final int    PORT = 12345;

    public static void main(String[] args) {
        new ClientMain().start();
    }

    public void start() {
        Scanner sc      = new Scanner(System.in);
        String  login   = null;
        String  password= null;
        SocketChannel chan = null;
        ObjectOutputStream oos = null;
        ObjectInputStream  ois = null;

        // 1) REGISTER / LOGIN
        while (true) {
            System.out.print("Введите команду (register/login): ");
            String[] parts = sc.nextLine().trim().split("\\s+");
            if (parts.length != 3 ||
                    !(parts[0].equalsIgnoreCase("register") || parts[0].equalsIgnoreCase("login"))) {
                System.out.println("Неверно. Формат: register <login> <password> ИЛИ login <login> <password>");
                continue;
            }
            login    = parts[1];
            password = parts[2];
            try {
                // устанавливаем соединение
                chan = connect();
                oos  = new ObjectOutputStream(chan.socket().getOutputStream());
                ois  = new ObjectInputStream(chan.socket().getInputStream());

                // отправляем регистрацию/логин
                oos.writeObject(new CommandRequest(parts[0],
                        new String[]{login, password},
                        ""));
                CommandResponse authResp = (CommandResponse) ois.readObject();
                System.out.println(authResp.getMessage());
                if (authResp.isSuccess()) {
                    break;
                } else {
                    chan.close();
                }
            } catch (Exception ex) {
                System.out.println("Ошибка связи, retry через 2 сек: " + ex.getMessage());
                sleep(2000);
            }
        }

        // 2) Интерактивный цикл команд с авто-переподключением
        mainLoop:
        while (true) {
            System.out.print("> ");
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;

            String cmd = line.split("\\s+")[0].toLowerCase();
            Object arg;

            // сбор аргументов
            switch (cmd) {
                case "add":
                    arg = InputManager.readLabWork(new Scanner(System.in), login);
                    break;

                case "update":
                    System.out.print("Введите id редактируемого элемента: ");
                    long updId = Long.parseLong(sc.nextLine().trim());
                    LabWork newData = InputManager.readLabWork(new Scanner(System.in), login);
                    arg = new Object[]{ updId, newData };
                    break;


                default:
                    String[] p = line.split("\\s+", 2);
                    arg = p.length > 1 ? p[1] : "";
            }

            // попытка отправить и получить ответ
            try {
                oos.writeObject(new CommandRequest(cmd, arg, login));
                CommandResponse resp = (CommandResponse) ois.readObject();
                System.out.println(resp.getMessage());
                if (cmd.equals("exit")) {
                    break;
                }
            } catch (Exception ex) {
                System.out.println("Связь потеряна, ожидаю восстановления сервера...");
                // цикл переподключения
                while (true) {
                    sleep(2000);
                    try {
                        chan = connect();
                        oos  = new ObjectOutputStream(chan.socket().getOutputStream());
                        ois  = new ObjectInputStream(chan.socket().getInputStream());
                        // повторный login
                        oos.writeObject(new CommandRequest("login",
                                new String[]{login, password},
                                ""));
                        CommandResponse reAuth = (CommandResponse) ois.readObject();
                        if (reAuth.isSuccess()) {
                            System.out.println("Переподключение успешно.");
                            break;
                        } else {
                            System.out.println("Не удалось переподключиться: " + reAuth.getMessage());
                        }
                    } catch (Exception rex) {
                        System.out.println("Сервер ещё не доступен...");
                    }
                }
                // после того как вернулись в строй — продолжаем основное меню
                continue mainLoop;
            }
        }
    }

    /** Создаёт и возвращает TCP-канал к серверу */
    private SocketChannel connect() throws Exception {
        SocketChannel ch = SocketChannel.open();
        ch.configureBlocking(false);
        ch.connect(new InetSocketAddress(HOST, PORT));
        while (!ch.finishConnect()) {
            Thread.sleep(50);
        }
        ch.configureBlocking(true);
        return ch;
    }

    private void sleep(long ms) {
        try { Thread.sleep(ms); }
        catch (InterruptedException ignored) {}
    }
}
