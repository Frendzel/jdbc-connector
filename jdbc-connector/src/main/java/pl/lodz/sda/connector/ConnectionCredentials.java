package pl.lodz.sda.connector;

/**
 * Wprapper na parametry dotyczące połączenia przydatny w sytuacji kiedy w jednym obiekcie chcemy przechowywać
 * wszystkie dane niezbędne do nawiązania połączenia.
 */
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
