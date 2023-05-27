package io.github.tigercrl.spc;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.*;

public class PermissionGroups {
    private final SimplePermissionConfigurator plugin;

    private File permissionGroupsConfigFile;
    private YamlConfiguration permissionGroupsConfig;

    public int count;
    public HashMap<String, List<String>> permissionGroups;

    public PermissionGroups(SimplePermissionConfigurator plugin) {
        this.plugin = plugin;
    }

    public void saveDefaultConfig() { // Save permission_groups.yml
        if (!new File(plugin.getDataFolder(), "permission_groups.yml").exists())
            plugin.saveResource("permission_groups.yml", false);
    }

    public void loadConfig() { // Load/Reload permission_groups.yml
        permissionGroupsConfigFile = new File(plugin.getDataFolder(), "permission_groups.yml");
        permissionGroupsConfig = new YamlConfiguration();
        try {
            permissionGroupsConfig.load(permissionGroupsConfigFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Set<String> keySet = permissionGroupsConfig.getKeys(false);
        count = keySet.size();
        for (String str : keySet) {
            permissionGroups.put(str, permissionGroupsConfig.getStringList(str));
        }
    }

    public List<String> getPermissions(String group) {
        if (permissionGroups == null) {
            plugin.getLogger().warning("Cannot find player group: " + group);
            return null;
        }
        List<String> permissions = permissionGroups.get(group);
        if (permissions == null)
            plugin.getLogger().warning("Cannot find permission group: " + group);
        return permissions;
    }
}
