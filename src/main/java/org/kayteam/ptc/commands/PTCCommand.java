package org.kayteam.ptc.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.kayteam.ptc.GeneralConfigurations;
import org.kayteam.ptc.PTC;

import java.util.ArrayList;
import java.util.List;

public class PTCCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(args.length > 0){
            switch(args[0].toLowerCase()){
                case "reload":{

                }
                case "spawn":{
                    if(sender instanceof Player){
                        Player player = (Player) sender;
                        if(args.length > 1){
                            switch(args[1].toLowerCase()){
                                case "set":{
                                    PTC.getGeneralConfigurations().settings.setLocation("lobbyLocation", player.getLocation());
                                    // todo lobby location setted
                                    break;
                                }
                                case "tp":{
                                    if(PTC.getGeneralConfigurations().lobbyLocation != null){
                                        player.teleport(PTC.getGeneralConfigurations().lobbyLocation);
                                        // todo teleported succesfully
                                    }else{
                                        // todo no location
                                    }
                                    break;
                                }
                                default:{
                                    // todo help
                                }
                            }
                        }else{
                            // todo general help
                        }
                    }else{
                        // todo only console command
                    }
                }
            }
        }else{

        }
        return false;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> tabs = new ArrayList<>();
        return null;
    }
}
