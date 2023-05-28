package io.github.tigercrl.spc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Permissions extends Config {
    public HashMap<String, List<String>> permissionMap = new HashMap<>();
    public List<String> permission4AllPlayers = new ArrayList<>();

    public Permissions(SimplePermissionConfigurator plugin) {
        super(plugin, "permissions.yml");
    }

    public void loadConfig() { // Load/Reload permissions.yml
        super.loadConfig();
        for (String str : keySet) {
            List<String> players = config.getStringList(str + ".players");
            List<String> playerGroups = config.getStringList(str + ".player-groups");
            for (String playerGroup : playerGroups) {
                List<String> playerInGroups = plugin.playerGroups.getPlayers(playerGroup);
                if (playerInGroups != null)
                    players.addAll(playerInGroups);
            }
            List<String> permissions = config.getStringList(str + ".permissions");
            List<String> permissionGroups = config.getStringList(str + ".permission-groups");
            for (String permissionGroup : permissionGroups) {
                List<String> permissionsInGroup = plugin.permissionGroups.getPermissions(permissionGroup);
                if (permissionsInGroup != null)
                    permissions.addAll(permissionsInGroup);
            }
            if (players.size() == 0) {
                permission4AllPlayers.addAll(permissions);
            } else {
                for (String player : players) {
                    if (player != null && permissions.size() != 0)
                        permissionMap.put(player, permissions);
                }
            }
        }
    }
}
