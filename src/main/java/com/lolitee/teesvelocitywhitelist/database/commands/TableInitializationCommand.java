package com.lolitee.teesvelocitywhitelist.database.commands;

import com.lolitee.teesvelocitywhitelist.database.ICommand;

import static com.lolitee.teesvelocitywhitelist.configuration.ConfigurationManager.tablePrefix;

public class TableInitializationCommand implements ICommand {
    @Override
    public String sql() {
        return "CREATE TABLE IF NOT EXISTS " + tablePrefix + "whitelist (" +
                "UUID VARCHAR(36) NOT NULL," +
                "DiscordID BIGINT UNSIGNED NOT NULL," +
                "Created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP)";
    }

    @Override
    public String[] columns() {
        return new String[0];
    }

    @Override
    public Object[] values() {
        return new Object[0];
    }
}
