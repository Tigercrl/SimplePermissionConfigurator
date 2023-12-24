package io.github.tigercrl.spc;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerGroups extends Config {
    public Map<String, List<String>> playerGroups = new HashMap<>();

    public PlayerGroups() {
        super("player_groups.yml");
    }

    @Override
    public void loadConfig() {
        super.loadConfig();
        playerGroups.clear();
        for (String str : keySet) {
            playerGroups.put(str, config.getStringList(str));
        }
    }

    public List<String> getPlayers(String group) {
        List<String> players = playerGroups.get(group);
        if (players == null) {
            plugin.getLogger().warning("Cannot find player group: " + group);
            return Collections.emptyList();
        }
        return players;
    }
}
