package org.kayteam.ptc.listeners;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.kayteam.ptc.PTC;
import org.kayteam.ptc.task.BlockRegenerationTask;

import java.util.Objects;

public class BlockBreakListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if(event.getPlayer().getGameMode().equals(GameMode.CREATIVE)){
            return;
        }
        Material blockMaterial = event.getBlock().getType();
        if(PTC.getGeneralConfigurations().cooldownMaterials.containsKey(blockMaterial)){
            Player player = event.getPlayer();
            event.setCancelled(true);
            if(player.getInventory().firstEmpty() != -1){
                for(ItemStack drop : event.getBlock().getDrops(Objects.requireNonNull(player.getItemInHand()))){
                    player.getInventory().addItem(drop);
                }
            }else{
                for(ItemStack drop : event.getBlock().getDrops(Objects.requireNonNull(player.getItemInHand()))){
                    Location blockLocation = event.getBlock().getLocation();
                    blockLocation.setY(event.getBlock().getLocation().getBlockY()+1);
                    event.getBlock().getWorld().dropItem(blockLocation, drop);
                }
            }
            System.out.println("PEX "+player.getExp());
            System.out.println("EXPT "+event.getExpToDrop());
            player.setExp(player.getExp()+event.getExpToDrop());
            new BlockRegenerationTask(event.getBlock()).startScheduler();
        }
    }
}
