package pl.lodz.sda.connector;

public class ConnectionCredentials {

    String connection;

    String user;

    String password;

    public ConnectionCredentials(String connection, String user, String password) {
        this.connection = connection;
        this.user = user;
        this.password = password;
    }
}
