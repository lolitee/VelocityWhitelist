package com.lolitee.teesvelocitywhitelist.database.providers;

import com.lolitee.teesvelocitywhitelist.database.IDatabase;

import java.sql.Connection;

public class H2 implements IDatabase {
    public H2(){

    }

    @Override
    public Connection createConnection() {
        return null;
    }
}
