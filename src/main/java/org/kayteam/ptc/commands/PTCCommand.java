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
                    break;
                }
                case "setspawn":{
                    break;
                }
                case "tpspawn":{

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
