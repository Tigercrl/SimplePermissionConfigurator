package io.github.tigercrl.spc;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Permissions {
    private final SimplePermissionConfigurator plugin;

    private File permissionsConfigFile;
    private YamlConfiguration permissionsConfig;

    public int count;
    public HashMap<String, List<String>> permissionMap = new HashMap<>();

    public Permissions(SimplePermissionConfigurator plugin) {
        this.plugin = plugin;
    }

    public void saveDefaultConfig() { // Save permissions.yml
        if (!new File(plugin.getDataFolder(), "permissions.yml").exists())
            plugin.saveResource("permissions.yml", false);
    }

    public void loadConfig() { // Load/Reload permissions.yml
        permissionsConfigFile = new File(plugin.getDataFolder(), "permissions.yml");
        permissionsConfig = new YamlConfiguration();
        try {
            permissionsConfig.load(permissionsConfigFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Set<String> keySet = permissionsConfig.getKeys(false);
        count = keySet.size();
        for (String str : keySet) {
            List<String> players = permissionsConfig.getStringList(str + ".players");
            List<String> playerGroups = permissionsConfig.getStringList(str + ".player-groups");
            for (String playerGroup : playerGroups) {
                List<String> playerInGroups = plugin.playerGroups.getPlayers(playerGroup);
                if (playerInGroups != null)
                    players.addAll(playerInGroups);
            }
            List<String> permissions = permissionsConfig.getStringList(str + ".permissions");
            List<String> permissionGroups = permissionsConfig.getStringList(str + ".permission-groups");
            for (String permissionGroup : permissionGroups) {
                List<String> permissionsInGroup = plugin.playerGroups.getPlayers(permissionGroup);
                if (permissionsInGroup != null)
                    permissions.addAll(permissionsInGroup);
            }
            for (String player : players) {
                if (player != null && permissions.size() != 0)
                    permissionMap.put(player, permissions);
            }
        }
    }
}
