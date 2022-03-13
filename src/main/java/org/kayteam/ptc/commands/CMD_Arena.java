package org.kayteam.ptc.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.kayteam.ptc.PTC;
import org.kayteam.ptc.util.PermissionChecker;

import java.util.ArrayList;
import java.util.List;

public class CMD_Arena implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(args.length > 0){
            switch(args[0].toLowerCase()){
                case "create":{
                    if(args.length > 1){

                    }
                }
                case "delete":{
                    if(args.length > 1){

                    }
                }
                case "defaultkit":{
                    if(sender instanceof Player){
                        Player player = (Player) sender;
                        if(PermissionChecker.check(player, "ptc.cmd.arena.defaultkit")){
                            if(args.length > 1){
                                String arenaName = args[1];
                                if(args.length > 2){
                                    switch (args[2].toLowerCase()){
                                        case "set":{

                                        }
                                        case "get":{

                                        }

                                    }
                                }
                            }
                        }
                    }else{
                        PTC.messages.sendMessage(sender, "onlyPlayerCommand");
                        return;
                    }
                }
                case "join":{

                }
            }
        }
        return false;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> tabs = new ArrayList<>();
        if(args.length == 1){
            if(sender.hasPermission("ptc.cmd.arena.create")){
                tabs.add("create");
            }
            if(sender.hasPermission("ptc.cmd.arena.delete")){
                tabs.add("delete");
            }
            if(sender.hasPermission("ptc.cmd.arena.defaultkit")){
                tabs.add("defaultkit");
            }
        }
        if(args.length == 2){
            if(args[0].equalsIgnoreCase("delete")){
                if(sender.hasPermission("ptc.cmd.arena.delete")){
                    tabs.addAll(PTC.getArenaManager().arenas.keySet());
                }
            }else if(args[0].equalsIgnoreCase("defaultkit")){
                if(sender.hasPermission("ptc.cmd.arena.defaultkit")){
                    tabs.add("set");
                    tabs.add("get");
                }
            }
        }
        return null;
    }
}
