package io.github.tigercrl.spc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Permissions extends Config {
    public Map<String, List<String>> permissionMap = new HashMap<>();
    public List<String> permission4AllPlayers = new ArrayList<>();

    public Permissions() {
        super("permissions.yml");
    }

    @Override
    public void loadConfig() {
        super.loadConfig();
        permissionMap.clear();
        permission4AllPlayers.clear();
        for (String str : keySet) {
            // Get players and player groups
            List<String> players = config.getStringList(str + ".players");
            List<String> playerGroups = config.getStringList(str + ".player-groups");
            // Add players from player groups to players
            for (String playerGroup : playerGroups) {
                List<String> playerInGroups = plugin.playerGroups.getPlayers(playerGroup);
                players.addAll(playerInGroups);
            }
            // Get permissions and permission groups
            List<String> permissions = config.getStringList(str + ".permissions");
            List<String> permissionGroups = config.getStringList(str + ".permission-groups");
            // Add permissions from permission groups to permissions
            for (String permissionGroup : permissionGroups) {
                List<String> permissionsInGroup = plugin.permissionGroups.getPermissions(permissionGroup);
                permissions.addAll(permissionsInGroup);
            }
            // If players is empty, add permissions to all players
            if (players.isEmpty()) {
                permission4AllPlayers.addAll(permissions);
            } else { // If players is not empty, add permissions to players
                for (String player : players) {
                    if (!permissions.isEmpty()) permissionMap.put(player, permissions);
                }
            }
        }
    }
}
