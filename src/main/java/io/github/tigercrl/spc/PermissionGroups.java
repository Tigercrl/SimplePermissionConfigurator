package io.github.tigercrl.spc;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PermissionGroups extends Config {
    public Map<String, List<String>> permissionGroups = new HashMap<>();

    public PermissionGroups() {
        super("permission_groups.yml");
    }

    @Override
    public void loadConfig() {
        super.loadConfig();
        permissionGroups.clear();
        for (String str : keySet) {
            permissionGroups.put(str, config.getStringList(str));
        }
    }

    public List<String> getPermissions(String group) {
        List<String> permissions = permissionGroups.get(group);
        if (permissions == null) {
            plugin.getLogger().warning("Cannot find permission group: " + group);
            return Collections.emptyList();
        }
        return permissions;
    }
}
