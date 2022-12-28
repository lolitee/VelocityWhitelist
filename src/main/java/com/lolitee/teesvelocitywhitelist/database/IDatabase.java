package com.lolitee.teesvelocitywhitelist.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLData;
import java.sql.SQLException;

import static com.lolitee.teesvelocitywhitelist.TeesVelocityWhitelist.logger;

public interface IDatabase {
    Connection createConnection();
    default SQLData execute(ICommand cmd){
        return null;
    }

    default boolean executeNonQuery(ICommand cmd){

        Connection conn = createConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(cmd.sql());

            // parameters later on against injections

            return stmt.execute();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return false;
        }
    }
}
