package io.github.tigercrl.spc;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.Set;

public abstract class Config {
    protected final SimplePermissionConfigurator plugin;
    public int count;
    protected File configFile;
    protected YamlConfiguration config;
    protected String fileName;
    protected Set<String> keySet;

    public Config(SimplePermissionConfigurator plugin, String fileName) {
        this.plugin = plugin;
        this.fileName = fileName;
    }

    public void saveDefaultConfig() { // Save file
        if (!new File(plugin.getDataFolder(), fileName).exists())
            plugin.saveResource(fileName, false);
    }

    public void loadConfig() { // Load/Reload file
        configFile = new File(plugin.getDataFolder(), fileName);
        config = new YamlConfiguration();
        try {
            config.load(configFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        keySet = config.getKeys(false);
        count = keySet.size();
    }

}
