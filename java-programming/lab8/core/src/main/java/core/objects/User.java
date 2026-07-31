package core.objects;

public class User {
    private final String login;
    private final String passwordHash; // MD5

    public User(String login, String passwordHash) {
        this.login = login;
        this.passwordHash = passwordHash;
    }
    public String getLogin() { return login; }
    public String getPasswordHash() { return passwordHash; }
}
