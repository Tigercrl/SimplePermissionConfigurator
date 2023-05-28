package io.github.tigercrl.spc;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinEventListener implements Listener {
    SimplePermissionConfigurator plugin;

    public PlayerJoinEventListener(SimplePermissionConfigurator plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        plugin.grantPermissions(e.getPlayer());
    }
}
