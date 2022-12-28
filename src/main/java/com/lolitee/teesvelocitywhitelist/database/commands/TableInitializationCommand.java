package com.lolitee.teesvelocitywhitelist.database.commands;

import com.lolitee.teesvelocitywhitelist.database.AbstractCommand;

import static com.lolitee.teesvelocitywhitelist.configuration.ConfigurationManager.tablePrefix;

public class TableInitializationCommand extends AbstractCommand {
    @Override
    public String sql() {
        return "CREATE TABLE IF NOT EXISTS " + tablePrefix + "whitelist (" +
                "UUID VARCHAR(36) NOT NULL," +
                "DiscordID BIGINT UNSIGNED NOT NULL," +
                "Created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP)";
    }
}
