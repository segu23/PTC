package org.kayteam.ptc.inventories;

import org.kayteam.api.inventory.InventoryBuilder;
import org.kayteam.api.simple.yaml.SimpleYaml;
import org.kayteam.ptc.PTC;
import org.kayteam.ptc.arena.Arena;

import java.util.ArrayList;
import java.util.List;

public class ArenaListMenu extends InventoryBuilder {

    public ArenaListMenu(int page) {
        super(PTC.inventories.getString("arenaList.title"), PTC.inventories.getInt("arenaList.rows"));
        SimpleYaml inventories = PTC.inventories;
        // Close
        addItem(49, () -> inventories.getItemStack("arenaSelector.items.close"));
        addLeftAction(49, (player, slot) -> {
            player.closeInventory();
        });
        // Arenas
        List<Arena> arenas = new ArrayList<>(PTC.getArenaManager().arenas.values());
        for (int i = 9; i < 45; i++) {
            int index = ((page * (4 * 9)) - (4 * 9)) + (i - 9);
            if (index < arenas.size()) {
                addItem(i, () -> {
                    Arena arena = arenas.get(index);
                    return inventories.getItemStack("arenaList.items.arena", new String[][]{
                            {"%arenaName%", arena.getName()},
                            {"%maxTeamPlayers%", String.valueOf(arena.getMaxTeamPlayers())}
                            // todo finish this
                    });
                });
            }
        }

    }
}
