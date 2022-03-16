package org.kayteam.ptc.inventories;

import org.kayteam.api.inventory.InventoryBuilder;
import org.kayteam.api.yaml.Yaml;
import org.kayteam.ptc.PTC;
import org.kayteam.ptc.arena.Arena;

import java.util.ArrayList;
import java.util.List;

public class ArenaSelectorMenu extends InventoryBuilder {

    public ArenaSelectorMenu() {
        super(PTC.inventories.getString("arenaSelector.title"), PTC.inventories.getInt("arenaSelector.rows"));
        Yaml inventories = PTC.inventories;
        // Close
        addItem(49, () -> inventories.getItemStack("arenaSelector.items.close"));
        addLeftAction(49, (player, slot) -> {
            player.closeInventory();
        });
        List<Arena> availableArenas = new ArrayList<>();
        for(Arena arena : availableArenas){
            //if(PTC.getGameManager().getWaitingGames()){

            //}
        }
    }
}
