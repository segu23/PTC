package org.kayteam.ptc.arena;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;
import org.kayteam.ptc.PTC;
import org.kayteam.ptc.game.TeamColour;
import org.kayteam.api.yaml.Yaml;

import java.io.File;
import java.time.Instant;
import java.util.HashMap;

public class ArenaManager {

    public HashMap<String, Arena> arenas = new HashMap<>();

    private Location mainLobby;

    public ArenaManager() {
        loadArenas();
    }

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
        long elapsedTime = finalTime-initialTime;
        PTC.getPTC().getLogger().info("All arenas have been loaded in "+elapsedTime+"ms");
    }

    public void loadArena(String arenaName){
        Yaml arenaFile = getArenaFile(arenaName);
        //String worldTemplateName = arenaFile.getString("worldTemplateName");
        File worldTemplateDir = new File(PTC.getPTC().getDataFolder()+File.separator+"arenas"+File.separator+arenaName);
        if(worldTemplateDir.exists()){
            Arena arena = new Arena(arenaName, worldTemplateDir);
            arena.setFile(arenaFile);
            Location waitingLobby = arenaFile.getLocation("waitingLobby");
            arena.setWaitingLobby(waitingLobby);
            if(arenaFile.getFileConfiguration().contains("defaultKit")){
                for(String itemKey : arenaFile.getFileConfiguration().getConfigurationSection("defaultKit").getKeys(false)){
                    try{
                        ItemStack kitItem = arenaFile.getItemStack("defaultKit."+itemKey);
                        arena.getDefaultKit().put(Integer.valueOf(itemKey), kitItem);
                    }catch (Exception e){
                        PTC.getPTC().getLogger().warning("An error has ocurred trying to load default kit item "+itemKey+" on arena "+arenaName);
                    }
                }
            }
            if(arenaFile.contains("spawnLocations")) {
                for (String team : arenaFile.getFileConfiguration().getConfigurationSection("spawnLocations").getKeys(false)) {
                    try {
                        TeamColour teamColour = TeamColour.valueOf(team);

                        Location spawnLocation = arenaFile.getLocation("spawnLocations/" + team);
                        arena.getSpawnLocations().put(teamColour, spawnLocation);
                    } catch (Exception ignored) {
                    }
                }
            }
            if(arenaFile.contains("coreLocations")) {
                for (String team : arenaFile.getFileConfiguration().getConfigurationSection("coreLocations").getKeys(false)) {
                    try {
                        TeamColour teamColour = TeamColour.valueOf(team);

                        Location coreLocation = arenaFile.getLocation("coreLocations." + teamColour);
                        arena.getCoreLocations().put(teamColour, coreLocation);
                    } catch (Exception ignored) {}
                }
            }
            if(arenaFile.contains("shopLocations")){
                for(String team : arenaFile.getFileConfiguration().getConfigurationSection("shopLocations").getKeys(false)) {
                    try {
                        TeamColour teamColour = TeamColour.valueOf(team);

                        Location shopLocation = arenaFile.getLocation("shopLocations." + teamColour);
                        arena.getShopLocations().put(teamColour, shopLocation);
                    } catch (Exception ignored) {}
                }
            }
            arenas.put(arenaName, arena);
            PTC.getPTC().getLogger().info("Arena "+arenaName+" has been loaded");
        }else{
            PTC.getPTC().getLogger().warning("An error has ocurred trying to load arena called "+arenaName);
        }
    }

    public void saveArena(Arena arena){
        Yaml arenaFile = getArenaFile(arena.getName());
        for(TeamColour teamColour : TeamColour.values()){
            if(arena.getSpawnLocations().containsKey(teamColour)) {
                arenaFile.setLocation("spawnLocations." + teamColour, arena.getSpawnLocations().get(teamColour));
            }
            if(arena.getCoreLocations().containsKey(teamColour)) {
                arenaFile.setLocation("coreLocations." + teamColour, arena.getCoreLocations().get(teamColour));
            }
            if(arena.getShopLocations().containsKey(teamColour)){
                arenaFile.setLocation("shopLocations."+teamColour, arena.getShopLocations().get(teamColour));
            }
        }
        if(!arena.getDefaultKit().isEmpty()){
            for(int itemPosition : arena.getDefaultKit().keySet()){
                arenaFile.setItemStack("defaultKit."+itemPosition, arena.getDefaultKit().get(itemPosition));
            }
        }
        if(arena.getWaitingLobby() != null){
            arenaFile.setLocation("waitingLobby", arena.getWaitingLobby());
        }
        arenaFile.set("maxTeamPlayers", arena.getMaxTeamPlayers());
        arenaFile.saveFileConfiguration();
        arenas.put(arena.getName(), arena);
    }

    public Yaml getArenaFile(String arenaName){
        Yaml arenaFile = new Yaml(PTC.getPTC(), "arenas/"+arenaName);
        arenaFile.registerFileConfiguration();
        return arenaFile;
    }
}
