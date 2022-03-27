package org.kayteam.ptc.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.kayteam.ptc.GeneralConfigurations;
import org.kayteam.ptc.PTC;
import org.kayteam.ptc.util.PermissionChecker;

import java.util.ArrayList;
import java.util.List;

public class PTCCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(args.length > 0){
            switch(args[0].toLowerCase()){
                case "reload":{
                    if(PermissionChecker.check(sender, "ptc.cmd.ptc.reload")) {
                        PTC.messages.reloadYamlFile();
                        PTC.inventories.reloadYamlFile();
                        PTC.getGeneralConfigurations().load();
                        PTC.getArenaManager().reloadArenas();
                        PTC.getPlayerManager().reloadGamePlayers();
                        PTC.messages.sendMessage(sender, "pluginReloaded");
                    }
                    break;
                }
                case "setmainlobby":{
                    if(PermissionChecker.check(sender, "ptc.cmd.ptc.setmainlobby")) {
                        if (sender instanceof Player) {
                            Player player = (Player) sender;
                            Location mainLobbyLocation = player.getLocation();
                            PTC.getGeneralConfigurations().mainLobby = mainLobbyLocation;
                            PTC.getGeneralConfigurations().settings.set("mainLobby", mainLobbyLocation);
                            PTC.getGeneralConfigurations().settings.saveYamlFile();
                            PTC.messages.sendMessage(player, "mainLobbySetted");
                        }else{
                            PTC.messages.sendMessage(sender, "onlyPlayerCommand");
                        }
                    }
                    break;
                }
                case "tpspawn":{
                    if(PermissionChecker.check(sender, "ptc.cmd.ptc.tpspawn")) {
                        if (sender instanceof Player) {
                            Player player = (Player) sender;
                            player.teleport(PTC.getGeneralConfigurations().mainLobby);
                            PTC.messages.sendMessage(player, "mainLobbyTeleported");
                        }else{
                            PTC.messages.sendMessage(sender, "onlyPlayerCommand");
                        }
                    }
                    break;
                }
                default:{
                    // todo send help
                }
            }
        }else{
            // todo send help
        }
        return false;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> tabs = new ArrayList<>();
        if(args.length == 0){
            if(sender.hasPermission("ptc.cmd.ptc.setmainlobby")){
                tabs.add("setmainlobby");
            }
            if(sender.hasPermission("ptc.cmd.ptc.tpspawn")){
                tabs.add("tpspawn");
            }
            if(sender.hasPermission("ptc.cmd.ptc.reload")){
                tabs.add("reload");
            }
            return tabs;
        }
        return null;
    }
}
