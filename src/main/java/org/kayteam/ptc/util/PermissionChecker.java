package org.kayteam.ptc.util;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.kayteam.ptc.PTC;

public class PermissionChecker {

    public static boolean check(CommandSender sender, String permission) {
        if (sender.hasPermission(permission)) {
            return true;
        }
        PTC.messages.sendMessage(sender, "noPermissions");
        return false;
    }
}
