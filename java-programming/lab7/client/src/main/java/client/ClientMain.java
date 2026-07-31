package client;

import common.network.CommandRequest;
import common.network.CommandResponse;
import common.utils.PasswordUtils;
import common.utils.UserInputUtils;
import common.model.Person;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class ClientMain {
    private static final String SERVER = "localhost";
    private static final int PORT = 5000;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Login: ");
        String login = sc.nextLine().trim();
        System.out.print("Password: ");
        String hash  = PasswordUtils.sha256Hex(sc.nextLine());

        try (Socket socket = new Socket(SERVER, PORT);

             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

             ObjectInputStream  ois = new ObjectInputStream(socket.getInputStream()))
        {
            socket.setTcpNoDelay(true);
            oos.flush();


            boolean authOK = false;
            while (!authOK) {
                System.out.print("Войти (l) или зарегистрироваться (r)? ");
                String choice = sc.nextLine().trim().toLowerCase();

                if ("l".equals(choice)) {
                    oos.writeObject(new CommandRequest("help", "", login, hash));
                    oos.flush();
                    CommandResponse resp = (CommandResponse) ois.readObject();
                    if ("Unauthorized".equals(resp.getResponse())) {
                        System.out.println("Неверный логин или пароль.");
                        System.out.print("Login: ");
                        login = sc.nextLine().trim();
                        System.out.print("Password: ");
                        hash = PasswordUtils.sha256Hex(sc.nextLine());
                    } else {
                        System.out.println("Успешный вход.");
                        authOK = true;
                    }
                }
                else if ("r".equals(choice)) {
                    oos.writeObject(new CommandRequest("register", "", login, hash));
                    oos.flush();
                    CommandResponse resp = (CommandResponse) ois.readObject();
                    System.out.println(resp.getResponse());
                    if ("Registered".equals(resp.getResponse())) {
                        authOK = true;
                    } else {
                        System.out.print("Попробуйте другой логин: ");
                        login = sc.nextLine().trim();
                        System.out.print("Password: ");
                        hash = PasswordUtils.sha256Hex(sc.nextLine());
                    }
                }
            }


            UserInputUtils ui = new UserInputUtils();
            while (true) {
                System.out.print("> ");
                String line = sc.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split("\\s+", 2);
                String cmd = parts[0];
                Object payload;

                switch (cmd) {
                    case "add" -> payload = ui.readPerson();
                    case "update" -> {
                        if (parts.length < 2 || parts[1].isBlank()) {
                            System.out.println("Error: id needed");
                            continue;
                        }
                        Person p = ui.readPerson();
                        p.setId(parts[1].trim());
                        payload = p;
                    }
                    case "execute_script" -> {
                        if (parts.length < 2) {
                            System.out.println("Error: path needed");
                            continue;
                        }
                        try {
                            payload = Files.readString(Path.of(parts[1].trim()));
                        } catch (Exception e) {
                            System.out.println("Error reading script: " + e.getMessage());
                            continue;
                        }
                    }
                    default -> payload = parts.length > 1 ? parts[1] : "";
                }

                oos.writeObject(new CommandRequest(cmd, payload, login, hash));
                oos.flush();
                oos.reset();

                CommandResponse resp = (CommandResponse) ois.readObject();
                System.out.println(resp.getResponse());
                if ("exit".equals(resp.getResponse())) break;
            }

        } catch (Exception e) {
            System.out.println("Network error: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}
