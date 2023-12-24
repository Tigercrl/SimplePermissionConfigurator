package io.github.tigercrl.spc;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SPCCommand implements CommandExecutor {
    private final SimplePermissionConfigurator plugin = SimplePermissionConfigurator.instance;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("spc") && (args.length == 1 && args[0].equals("reload") && sender.isOp())) {
            sender.sendMessage("[SPC] Reloading...");
            // Remove permissions
            plugin.removePermissions();
            // Save config
            plugin.playerGroups.saveDefaultConfig();
            plugin.permissionGroups.saveDefaultConfig();
            plugin.permissions.saveDefaultConfig();
            // Load config
            plugin.playerGroups.loadConfig();
            plugin.permissionGroups.loadConfig();
            plugin.permissions.loadConfig();
            sender.sendMessage("[SPC] Founded " + plugin.playerGroups.count + " player groups, " + plugin.permissionGroups.count + " permission groups and " + plugin.permissions.count + " permission settings.");
            // Grant permissions
            plugin.grantPermissions();
            sender.sendMessage("[SPC] Plugin reloaded.");
            return true;
        }
        return false;
    }
}
