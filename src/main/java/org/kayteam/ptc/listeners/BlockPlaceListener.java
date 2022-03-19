package org.kayteam.ptc.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.kayteam.ptc.PTC;

public class BlockPlaceListener implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onBlockPlace(BlockPlaceEvent event) {
        Material blockMaterial = event.getBlock().getType();
        if(PTC.getGeneralConfigurations().cooldownMaterials.containsKey(blockMaterial)) {
            Player player = event.getPlayer();
            event.setCancelled(true);
            PTC.messages.sendMessage(player, "cantPlaceBlock");
        }
    }
}
