package com.lolitee.teesvelocitywhitelist.configuration;

import com.lolitee.teesvelocitywhitelist.database.DatabaseType;
import com.lolitee.teesvelocitywhitelist.TeesVelocityWhitelist;
import com.lolitee.teesvelocitywhitelist.configuration.providers.Yaml;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import static com.lolitee.teesvelocitywhitelist.TeesVelocityWhitelist.*;

public class ConfigurationManager {
    public static DatabaseType provider;
    public static String address;
    public static String username;
    public static String password;
    public static String database;
    public static String tablePrefix;
    public static String token;
    private final Yaml yaml;
    public ConfigurationManager(@NotNull Path dataDirectory){

        Path confFile = dataDirectory.resolve("configuration.yml");

        if(!Files.exists(confFile)){
            InputStream input = TeesVelocityWhitelist.class.getResourceAsStream("/configuration.yml");
            try {
                assert input != null;
                Files.copy(input, confFile);
            } catch (IOException e) {
                logger.error(e.getMessage());
                System.exit(1);
            }
        }

        yaml = new Yaml(dataDirectory.resolve("configuration.yml"));
        yaml.load();
        provider = DatabaseType.valueOf(Objects.requireNonNull(yaml.root.getNode("database", "provider").getString()).toUpperCase().trim());
        address = yaml.root.getNode("database", "address").getString();
        username = yaml.root.getNode("database","username").getString();
        password = yaml.root.getNode("database","password").getString();
        database = yaml.root.getNode("database","database").getString();
        token = yaml.root.getNode("discord", "token").getString();
        tablePrefix = yaml.root.getNode("database","table_prefix").getString();

    }

    public void save(){
        yaml.save();
    }


}
