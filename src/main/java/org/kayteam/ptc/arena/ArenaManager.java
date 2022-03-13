package org.kayteam.ptc.arena;

import org.bukkit.Location;
import org.kayteam.api.yaml.Yaml;

import java.io.File;
import java.util.HashMap;

public class ArenaManager {

    public HashMap<String, Arena> arenas = new HashMap<>();

    private Location mainLobby;

    public Arena getArena(String arenaName){
        return arenas.get(arenaName);
    }

    public boolean isArena(String arenaName){
        return arenas.containsKey(arenaName);
    }

    public void loadArenas(){
        for(File arenaFile : Yaml.getFolderFiles("arenas")){
            loadArena(arenaFile.getName().replaceAll(".yml", ""));
        }
    }

    public void loadArena(String arenaName){

    }
}
