package org.kayteam.ptc.util;

import org.bukkit.entity.Player;
import org.kayteam.ptc.PTC;

public class PermissionChecker {

    public static boolean check(Player player, String permission) {
        if (player.hasPermission(permission)) {
            return true;
        }
        PTC.messages.sendMessage(player, "noPermissions");
        return false;
    }
}
