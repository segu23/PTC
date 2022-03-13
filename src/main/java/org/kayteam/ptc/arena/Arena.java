package org.kayteam.ptc.arena;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;
import org.kayteam.api.yaml.Yaml;

import java.util.HashMap;
import java.util.List;

public class Arena {

    private final String name;
    private final Yaml file;
    private String schematicName;
    private HashMap<Integer, ItemStack> defaultKit;
    private HashMap<String, List<Location>> spawnLocations;
    private Location lobby;
    private final World worldTemplate;

}
