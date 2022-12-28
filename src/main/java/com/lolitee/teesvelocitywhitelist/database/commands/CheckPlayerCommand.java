package com.lolitee.teesvelocitywhitelist.database.commands;

import com.lolitee.teesvelocitywhitelist.database.AbstractCommand;

import static com.lolitee.teesvelocitywhitelist.configuration.ConfigurationManager.tablePrefix;

public class CheckPlayerCommand extends AbstractCommand {
    @Override
    public String sql() {
        return "SELECT * FROM " + tablePrefix + "whitelist " +
                "WHERE UUID = '?'";
    }
}
