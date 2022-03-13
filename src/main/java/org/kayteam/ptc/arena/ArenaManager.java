package org.kayteam.ptc.arena;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;
import org.kayteam.api.yaml.Yaml;
import org.kayteam.ptc.PTC;
import org.kayteam.ptc.game.TeamColour;

import java.io.File;
import java.time.Instant;
import java.time.temporal.Temporal;
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
        long initialTime = Instant.now().toEpochMilli();
        for(File arenaFile : Yaml.getFolderFiles(PTC.getPTC().getDataFolder()+File.separator+"arenas")){
            loadArena(arenaFile.getName().replaceAll(".yml", ""));
        }
        long finalTime = Instant.now().toEpochMilli();
        long elapsedTime = initialTime-finalTime;
        PTC.getPTC().getLogger().info("All arenas have been loaded in "+elapsedTime+"ms");
    }

    public void loadArena(String arenaName){
        Yaml arenaFile = new Yaml(PTC.getPTC(), "arenas/"+arenaName);
        String worldTemplateName = arenaFile.getString("worldTemplateName");
        File worldTemplateDir = new File(PTC.getPTC().getDataFolder()+File.separator+"worldTemplates"+File.separator+arenaName);
        if(worldTemplateDir.exists()){
            Arena arena = new Arena(arenaName, arenaFile, worldTemplateDir);
            Location waitingLobby = arenaFile.getLocation("waitingLobby");
            arena.setWaitingLobby(waitingLobby);
            HashMap<Integer, ItemStack> defaultKit = new HashMap<>();
            for(String itemKey : arenaFile.getFileConfiguration().getConfigurationSection("defaultKit").getKeys(false)){
                try{
                    ItemStack kitItem = arenaFile.getItemStack("defaultKit."+itemKey);
                    defaultKit.put(Integer.valueOf(itemKey), kitItem);
                }catch (Exception e){
                    PTC.getPTC().getLogger().warning("An error has ocurred trying to load default kit item "+itemKey+" on arena "+arenaName);
                }
            }
            arena.setDefaultKit(defaultKit);
            HashMap<TeamColour, Location> teamSpawnLocations = new HashMap<>();
            for(String team : arenaFile.getFileConfiguration().getConfigurationSection("spawnLocations").getKeys(false)) {
                try{
                    TeamColour teamColour = TeamColour.valueOf(team);
                    Location spawnLocation = arenaFile.getLocation("spawnLocations/"+team);
                    teamSpawnLocations.put(teamColour, spawnLocation);
                }catch (Exception ignored){}
            }
            arena.setSpawnLocations(teamSpawnLocations);
            arenas.put(arenaName, arena);
            PTC.getPTC().getLogger().info("Arena "+arenaName+" has been loaded");
        }else{
            PTC.getPTC().getLogger().warning("An error has ocurred trying to load arena called "+arenaName);
        }
    }


}
