package io.github.tigercrl.spc;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.Set;

public abstract class Config {
    protected final SimplePermissionConfigurator plugin = SimplePermissionConfigurator.instance;
    public int count;
    protected File configFile;
    protected YamlConfiguration config;
    protected String fileName;
    protected Set<String> keySet;

    protected Config(String fileName) {
        this.fileName = fileName;
    }

    public void saveDefaultConfig() { // Save config file if it doesn't exist
        if (!new File(plugin.getDataFolder(), fileName).exists()) plugin.saveResource(fileName, false);
    }

    public void loadConfig() { // Load(Reload) config
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
