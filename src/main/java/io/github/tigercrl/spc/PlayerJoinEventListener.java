package io.github.tigercrl.spc;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.permissions.PermissionAttachment;

import java.util.List;
import java.util.Map;

public class PlayerJoinEventListener implements Listener {
    SimplePermissionConfigurator plugin;

    public PlayerJoinEventListener(SimplePermissionConfigurator plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        plugin.grantPermissions(e.getPlayer());
    }
}
