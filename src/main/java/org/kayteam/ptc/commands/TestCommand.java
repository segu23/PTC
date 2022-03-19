package org.kayteam.ptc.commands;

import me.neznamy.tab.api.TabPlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.kayteam.ptc.PTC;

public class TestCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            TabPlayer tabPlayer = PTC.getTabAPI().getPlayer(player.getUniqueId());
            if(args.length > 0){
                switch (args[0].toLowerCase()){
                    case "show":{
                        PTC.getGeneralConfigurations().bossBar.addPlayer(tabPlayer);
                        player.sendMessage("show");
                        break;
                    }
                    case "decrease":{
                        PTC.getGeneralConfigurations().bossBarProgress--;
                        PTC.getGeneralConfigurations().bossBar.setProgress(PTC.getGeneralConfigurations().bossBarProgress);
                        player.sendMessage("decrease");
                        break;
                    }
                    case "increase":{
                        PTC.getGeneralConfigurations().bossBarProgress++;
                        PTC.getGeneralConfigurations().bossBar.setProgress(PTC.getGeneralConfigurations().bossBarProgress);
                        player.sendMessage("increase");
                        break;
                    }
                }
            }
        }
        return false;
    }
}
