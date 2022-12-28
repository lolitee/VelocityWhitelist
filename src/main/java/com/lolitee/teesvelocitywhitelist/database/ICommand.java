package com.lolitee.teesvelocitywhitelist.database;

public interface ICommand {
    String sql();
    String[] columns();
    Object[] values();
}
