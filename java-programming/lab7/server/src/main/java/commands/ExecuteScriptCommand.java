package commands;

import common.network.CommandRequest;
import manager.CommandHandler;

import java.security.MessageDigest;
import java.util.*;

public class ExecuteScriptCommand implements Command {
    private static final long serialVersionUID = 1L;
    private static final int MAX_BYTES = 100_000;
    private static final int MAX_LINES = 500;

    private final CommandHandler handler;
    private final Set<String> running = new HashSet<>();

    public ExecuteScriptCommand(CommandHandler handler) {
        this.handler = handler;
    }

    @Override
    public String execute(String args) {
        return "Error: use execute(script, login, pwdHash)";
    }

    public String execute(String scriptText, String login, String pwdHash) {
        byte[] bytes = scriptText.getBytes();
        if (bytes.length > MAX_BYTES) return "Error: script too big";

        String hash = hash(scriptText);
        if (!running.add(hash)) return "Error: recursive script";

        StringBuilder out = new StringBuilder();
        Scanner sc = new Scanner(scriptText);
        int lines = 0;

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;
            if (++lines > MAX_LINES) {
                out.append("Error: too many lines\n");
                break;
            }
            out.append("> ").append(line).append("\n");


            CommandRequest req = new CommandRequest(line, line, login, pwdHash);
            String res = handler.handle(req).getResponse();
            out.append(res).append("\n");
        }

        running.remove(hash);
        return out.toString();
    }

    private static String hash(String t) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] d = md.digest(t.getBytes());
            return Base64.getEncoder().encodeToString(d);
        } catch (Exception e) {
            return "h" + t.length();
        }
    }
}

