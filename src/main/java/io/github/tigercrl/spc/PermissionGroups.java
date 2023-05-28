package io.github.tigercrl.spc;

import java.util.HashMap;
import java.util.List;

public class PermissionGroups extends Config {
    public HashMap<String, List<String>> permissionGroups;

    public PermissionGroups(SimplePermissionConfigurator plugin) {
        super(plugin, "permission_groups.yml");
    }

    public void loadConfig() { // Load/Reload permission_groups.yml
        super.loadConfig();
        for (String str : keySet) {
            permissionGroups.put(str, config.getStringList(str));
        }
    }

    public List<String> getPermissions(String group) {
        if (permissionGroups == null) {
            plugin.getLogger().warning("Cannot find player group: " + group);
            return null;
        }
        List<String> permissions = permissionGroups.get(group);
        if (permissions == null)
            plugin.getLogger().warning("Cannot find permission group: " + group);
        return permissions;
    }
}
