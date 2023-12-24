package io.github.tigercrl.spc;

import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class SimplePermissionConfigurator extends JavaPlugin {
    public static SimplePermissionConfigurator instance;
    public PlayerGroups playerGroups;
    public Permissions permissions;
    public PermissionGroups permissionGroups;
    public Map<Player, PermissionAttachment> attachmentMap = new HashMap<>();

    @Override
    public void onEnable() {
        instance = this;
        // Configs
        playerGroups = new PlayerGroups();
        permissionGroups = new PermissionGroups();
        permissions = new Permissions();
        // Save config
        playerGroups.saveDefaultConfig();
        permissionGroups.saveDefaultConfig();
        permissions.saveDefaultConfig();
        // Load config
        playerGroups.loadConfig();
        permissionGroups.loadConfig();
        permissions.loadConfig();
        getLogger().info("Founded " + playerGroups.count + " player groups, " + permissionGroups.count + " permission groups and " + permissions.count + " permission settings.");
        // Event listeners
        getServer().getPluginManager().registerEvents(new PlayerJoinEventListener(), this);
        // Command executors
        getCommand("spc").setExecutor(new SPCCommand());
        // Grant permissions
        grantPermissions();
        // Done
        getLogger().info("Plugin enabled.");
    }

    @Override
    public void onDisable() {
        removePermissions();
        instance = null;
        getLogger().info("Plugin disabled.");
    }

    public void grantPermissions(Player player) {
        List<String> permissionList = new ArrayList<>();
        // Get permissions for the player and add it to the list
        if (permissions.permissionMap.containsKey(player.getName())) {
            permissionList.addAll(permissions.permissionMap.get(player.getName()));
        }
        // Get permissions for all players and add it to the list
        permissionList.addAll(permissions.permission4AllPlayers);
        // Grant permissions in the list to the player
        PermissionAttachment attachment = player.addAttachment(this);
        for (String permission : permissionList) {
            attachment.setPermission(permission, true);
        }
        attachmentMap.put(player, attachment);
    }

    public void grantPermissions() {
        for (Player player : getServer().getOnlinePlayers()) {
            grantPermissions(player);
        }
    }

    public void removePermissions(Player p) {
        p.removeAttachment(attachmentMap.get(p));
        attachmentMap.remove(p);
    }

    public void removePermissions() {
        for (Map.Entry<Player, PermissionAttachment> entry : attachmentMap.entrySet()) {
            removePermissions(entry.getKey());
        }
    }
}
