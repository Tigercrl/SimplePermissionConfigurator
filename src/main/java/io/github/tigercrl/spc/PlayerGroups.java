package io.github.tigercrl.spc;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class PlayerGroups {
    private final SimplePermissionConfigurator plugin;
    public int count;
    public HashMap<String, List<String>> playerGroups;
    private File playerGroupsConfigFile;
    private YamlConfiguration playerGroupsConfig;

    public PlayerGroups(SimplePermissionConfigurator plugin) {
        this.plugin = plugin;
    }

    public void saveDefaultConfig() { // Save player_groups.yml
        if (!new File(plugin.getDataFolder(), "player_groups.yml").exists())
            plugin.saveResource("player_groups.yml", false);
    }

    public void loadConfig() { // Load/Reload player_groups.yml
        playerGroupsConfigFile = new File(plugin.getDataFolder(), "player_groups.yml");
        playerGroupsConfig = new YamlConfiguration();
        try {
            playerGroupsConfig.load(playerGroupsConfigFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Set<String> keySet = playerGroupsConfig.getKeys(false);
        count = keySet.size();
        for (String str : keySet) {
            playerGroups.put(str, playerGroupsConfig.getStringList(str));
        }
    }

    public List<String> getPlayers(String group) {
        if (playerGroups == null) {
            plugin.getLogger().warning("Cannot find player group: " + group);
            return null;
        }
        List<String> players = playerGroups.get(group);
        if (players == null)
            plugin.getLogger().warning("Cannot find player group: " + group);
        return players;
    }
}
