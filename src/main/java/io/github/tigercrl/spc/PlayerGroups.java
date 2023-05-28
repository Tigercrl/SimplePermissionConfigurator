package io.github.tigercrl.spc;

import java.util.HashMap;
import java.util.List;

public class PlayerGroups extends Config {
    public HashMap<String, List<String>> playerGroups;

    public PlayerGroups(SimplePermissionConfigurator plugin) {
        super(plugin, "player_groups.yml");
    }

    public void loadConfig() { // Load/Reload player_groups.yml
        super.loadConfig();
        for (String str : keySet) {
            playerGroups.put(str, config.getStringList(str));
        }
    }

    public List<String> getPlayers(String group) {
        if (playerGroups == null) {
            plugin.getLogger().warning("Cannot find player group: " + group);
            return null;
        }
        List<String> players = playerGroups.get(group);
        if (players == null)
            plugin.getLogger().warning("Cannot find player group: " + group);
        return players;
    }
}
