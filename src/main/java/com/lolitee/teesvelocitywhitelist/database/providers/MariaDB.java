package com.lolitee.teesvelocitywhitelist.database.providers;

import com.lolitee.teesvelocitywhitelist.database.IDatabase;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.lolitee.teesvelocitywhitelist.TeesVelocityWhitelist.logger;

public class MariaDB implements IDatabase {

    final private String host;
    final private String username;
    final private String password;
    final private String database;

    public MariaDB(String host, String username, String password, String database){
        this.host = host;
        this.username = username;
        this.password = password;
        this.database = database;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private String getJdbc(){
        return String.format("jdbc:mysql://%s:%s@%s/%s",
                URLEncoder.encode(username, StandardCharsets.UTF_8),
                URLEncoder.encode(password, StandardCharsets.UTF_8),
                host,
                database);
    }

    public Connection createConnection(){
        Connection conn = null;

        try{
            conn = DriverManager.getConnection(getJdbc());
            return conn;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            logger.info(getJdbc());

            if (e.getCause() != null) {
                e.getCause().printStackTrace();
            }
            System.exit(1);
        } finally {
//            try {
//                if (conn != null){
//                    conn.close();
//                }
//            } catch (SQLException e) {
//                if (e.getCause() != null) {
//                    e.getCause().printStackTrace();
//                }
//                System.exit(1);
//            }
        }
        return conn;
    }
}
