package com.lolitee.teesvelocitywhitelist.database.commands;

import com.lolitee.teesvelocitywhitelist.database.AbstractCommand;

import static com.lolitee.teesvelocitywhitelist.configuration.ConfigurationManager.tablePrefix;

public class CreatePlayerCommand extends AbstractCommand {
    @Override
    public String sql() {
        return "INSERT INTO " + tablePrefix + "whitelist " +
                "(UUID, DiscordID)" +
                "Values (?,?)";
    }
}
