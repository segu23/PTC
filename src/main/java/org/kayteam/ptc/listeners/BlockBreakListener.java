package org.kayteam.ptc.listeners;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.kayteam.ptc.PTC;
import org.kayteam.ptc.task.BlockRegenerationTask;
import org.kayteam.ptc.util.BlockUtil;

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
            try{
                ItemStack usedItem = player.getItemInHand();
                if(usedItem.containsEnchantment(Enchantment.SILK_TOUCH) || usedItem.containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS)){
                    player.getInventory().addItem(BlockUtil.getBlockDrop(event.getBlock(), player.getItemInHand()));
                }else{
                    for(ItemStack drop : event.getBlock().getDrops()){
                        player.getInventory().addItem(drop);
                    }
                }

            }catch (IllegalArgumentException e){
                ItemStack usedItem = player.getItemInHand();
                if(usedItem.containsEnchantment(Enchantment.SILK_TOUCH) || usedItem.containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS)){
                    Location blockLocation = event.getBlock().getLocation();
                    blockLocation.setY(event.getBlock().getLocation().getBlockY()+1);
                    event.getBlock().getWorld().dropItem(blockLocation, BlockUtil.getBlockDrop(event.getBlock(), player.getItemInHand()));
                }else{
                    for(ItemStack drop : event.getBlock().getDrops(player.getItemInHand())){
                        Location blockLocation = event.getBlock().getLocation();
                        blockLocation.setY(event.getBlock().getLocation().getBlockY()+1);
                        event.getBlock().getWorld().dropItem(blockLocation, drop);
                    }
                }
            }
            System.out.println("PEX "+player.getExp());
            System.out.println("EXPT "+event.getExpToDrop());
            player.setTotalExperience((int) player.getExp()+event.getExpToDrop());
            new BlockRegenerationTask(event.getBlock()).startScheduler();
        }
    }
}
