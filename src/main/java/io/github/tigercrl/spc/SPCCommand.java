package io.github.tigercrl.spc;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SPCCommand implements CommandExecutor {
    private final SimplePermissionConfigurator plugin;

    public SPCCommand(SimplePermissionConfigurator plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("spc")) {
            if (args.length == 1 && args[0].equals("reload") && sender.isOp()) {
                sender.sendMessage("[SPC] Reloading");
                // Remove Permissions
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
                plugin.getServer().getConsoleSender().sendMessage("[SPC] Permissions reloaded, all players need to rejoin to gain permissions.");
                return true;
            }
        }
        return false;
    }
}
