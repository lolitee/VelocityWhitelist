package Database.providers;

import Database.IDatabase;

import java.sql.Connection;

public class H2 implements IDatabase {
    public H2(){

    }

    @Override
    public Connection createConnection() {
        return null;
    }
}
