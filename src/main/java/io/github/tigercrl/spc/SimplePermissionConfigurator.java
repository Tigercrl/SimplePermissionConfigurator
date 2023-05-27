package io.github.tigercrl.spc;

import org.bukkit.plugin.java.JavaPlugin;

public final class SimplePermissionConfigurator extends JavaPlugin {
    public PlayerGroups playerGroups;
    public Permissions permissions;
    public PermissionGroups permissionGroups;

    @Override
    public void onEnable() {
        // Config Class
        playerGroups = new PlayerGroups(this);
        permissionGroups = new PermissionGroups(this);
        permissions = new Permissions(this);
        // Save config
        playerGroups.saveDefaultConfig();
        permissionGroups.saveDefaultConfig();
        permissions.saveDefaultConfig();
        // Load config
        playerGroups.loadConfig();
        permissionGroups.loadConfig();
        permissions.loadConfig();
        getLogger().info("Founded " + playerGroups.count + " player groups, " + permissionGroups.count + " permission groups and " + permissions.count + " permission settings.");
        // Event Listeners
        getServer().getPluginManager().registerEvents(new PlayerJoinEventListener(this), this);
        // Done
        getLogger().info("Plugin enabled.");
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin disabled.");
    }
}
