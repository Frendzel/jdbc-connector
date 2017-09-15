package pl.lodz.sda.connector;

import static pl.lodz.sda.connector.DB.H2;
import static pl.lodz.sda.connector.DB.MYSQL;

public class DBFactory {

    private static final String H2_CONNECTION = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static final String H2_USER = "sa";
    private static final String H2_PASSWORD = "";

    public ConnectionCredentials chooseDb(DB db) {
        if (db == H2) {
            return new ConnectionCredentials(H2_CONNECTION, H2_USER, H2_PASSWORD);
        } else if (db == MYSQL) {
            return new ConnectionCredentials("", "", "");
        } else {
            throw new RuntimeException("WRONG DB");
        }
    }

}
