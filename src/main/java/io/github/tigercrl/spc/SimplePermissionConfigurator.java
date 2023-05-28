package io.github.tigercrl.spc;

import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class SimplePermissionConfigurator extends JavaPlugin {
    public PlayerGroups playerGroups;
    public Permissions permissions;
    public PermissionGroups permissionGroups;
    public Map<Player, PermissionAttachment> attachmentMap = new HashMap<>();

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
        // Command executors
        getCommand("spc").setExecutor(new SPCCommand(this));
        // Done
        getLogger().info("Plugin enabled.");
    }

    @Override
    public void onDisable() {
        removePermissions();
        getLogger().info("Plugin disabled.");
    }

    public void grantPermissions(Player player) {
        List<String> permissionList = new ArrayList<>();
        if (permissions.permissionMap.containsKey(player.getName())) {
            for (String permission : permissions.permissionMap.get(player.getName())) {
                permissionList.add(permission);
            }
        }
        for (String permission : permissions.permission4AllPlayers) {
            permissionList.add(permission);
        }
        PermissionAttachment attachment = player.addAttachment(this);
        for (String permission : permissionList) {
            attachment.setPermission(permission, true);
        }
        attachmentMap.put(player, attachment);
    }

    public void removePermissions() {
        for (Map.Entry<Player, PermissionAttachment> entry : attachmentMap.entrySet()) {
            entry.getKey().removeAttachment(entry.getValue());
            attachmentMap.remove(entry.getKey());
        }
    }
}
