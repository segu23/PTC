package org.kayteam.ptc.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CMD_PTC implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(args.length > 0){
            switch(args[0].toLowerCase()){
                case "reload":{

                }
                case "spawn":{
                    if(args.length > 1){
                        switch(args[1].toLowerCase()){
                            case "set":{

                            }
                            case "tp":{

                            }
                        }
                    }else{

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
