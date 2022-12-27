package com.lolitee.teesvelocitywhitelist.configuration.providers;

import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.yaml.YAMLConfigurationLoader;

import java.io.IOException;
import java.nio.file.Path;

import static com.lolitee.teesvelocitywhitelist.TeesVelocityWhitelist.logger;

public class Yaml {
    YAMLConfigurationLoader conf;
    public ConfigurationNode root;
    public Yaml(Path path){
        conf = YAMLConfigurationLoader.builder()
                .setDefaultOptions(configurationOptions -> configurationOptions.withShouldCopyDefaults(true))
                .setPath(path.toAbsolutePath())
                .build();
    }

    public void load() {
        try {
            root = conf.load();
        } catch (IOException e) {
            logger.error("Unable to read YAML configuration: " + e.getMessage());
            if (e.getCause() != null) {
                e.getCause().printStackTrace();
            }

            System.exit(1);
        }
    }
    public void save() {
        try {
            conf.save(root);
        } catch (IOException e) {
            logger.error("Unable to save YAML configuration: " + e.getMessage());
            if (e.getCause() != null) {
                e.getCause().printStackTrace();
            }
        }
    }
}
