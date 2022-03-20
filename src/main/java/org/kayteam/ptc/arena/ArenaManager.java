package org.kayteam.ptc.arena;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;
import org.kayteam.api.simple.yaml.SimpleYaml;
import org.kayteam.ptc.PTC;
import org.kayteam.ptc.game.TeamColour;

import java.io.File;
import java.time.Instant;
import java.util.HashMap;

public class ArenaManager {

    public HashMap<String, Arena> arenas = new HashMap<>();

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
        for(SimpleYaml arenaFile : SimpleYaml.getYamlFiles(PTC.getPTC().getDataFolder()+File.separator+"arenas")){
            loadArena(arenaFile.getName());
        }
        long finalTime = Instant.now().toEpochMilli();
        long elapsedTime = finalTime-initialTime;
        PTC.getPTC().getLogger().info("All arenas have been loaded in "+elapsedTime+"ms");
    }

    public void loadArena(String arenaName){
        SimpleYaml arenaFile = getArenaFile(arenaName);
        //String worldTemplateName = arenaFile.getString("worldTemplateName");
        File worldTemplateDir = new File(PTC.getPTC().getDataFolder()+File.separator+"arenas"+File.separator+arenaName);
        if(worldTemplateDir.exists()){
            Arena arena = new Arena(arenaName, worldTemplateDir);
            arena.setFile(arenaFile);
            Location waitingLobby = arenaFile.getLocation("waitingLobby");
            arena.setWaitingLobby(waitingLobby);
            if(arenaFile.contains("defaultKit")){
                for(String team : arenaFile.getConfigurationSection("defaultKit").getKeys(false)){
                    for(String itemKey : arenaFile.getConfigurationSection("defaultKit").getKeys(false)){
                        try{
                            TeamColour teamColour = TeamColour.valueOf(team);

                            ItemStack kitItem = arenaFile.getItemStack("defaultKit."+teamColour+"."+itemKey);
                            arena.getDefaultKit().get(teamColour).put(Integer.parseInt(itemKey), kitItem);
                        }catch (Exception e){
                            PTC.getPTC().getLogger().warning("An error has occurred trying to load default kit item "+itemKey+" on arena "+arenaName);
                        }
                    }
                }
            }
            if(arenaFile.contains("spawnLocations")) {
                for (String team : arenaFile.getConfigurationSection("spawnLocations").getKeys(false)) {
                    try {
                        TeamColour teamColour = TeamColour.valueOf(team);

                        Location spawnLocation = arenaFile.getLocation("spawnLocations/" + team);
                        arena.getSpawnLocations().put(teamColour, spawnLocation);
                    } catch (Exception ignored) {
                    }
                }
            }
            if(arenaFile.contains("coreLocations")) {
                for (String team : arenaFile.getConfigurationSection("coreLocations").getKeys(false)) {
                    try {
                        TeamColour teamColour = TeamColour.valueOf(team);

                        Location coreLocation = arenaFile.getLocation("coreLocations." + teamColour);
                        arena.getCoreLocations().put(teamColour, coreLocation);
                    } catch (Exception ignored) {}
                }
            }
            if(arenaFile.contains("shopLocations")){
                for(String team : arenaFile.getConfigurationSection("shopLocations").getKeys(false)) {
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
        SimpleYaml arenaFile = getArenaFile(arena.getName());
        for(TeamColour teamColour : TeamColour.values()){
            if(arena.getSpawnLocations().get(teamColour) != null) {
                arenaFile.setLocation("spawnLocations." + teamColour, arena.getSpawnLocations().get(teamColour));
            }
            if(arena.getCoreLocations().get(teamColour) != null) {
                arenaFile.setLocation("coreLocations." + teamColour, arena.getCoreLocations().get(teamColour));
            }
            if(arena.getShopLocations().get(teamColour) != null){
                arenaFile.setLocation("shopLocations."+teamColour, arena.getShopLocations().get(teamColour));
            }
        }
        if(!arena.getDefaultKit().isEmpty()){
            for(TeamColour teamColour : arena.getDefaultKit().keySet()){
                for(int itemPosition : arena.getDefaultKit().get(teamColour).keySet()){
                    arenaFile.setItemStack("defaultKit."+teamColour+"."+itemPosition, arena.getDefaultKit().get(teamColour).get(itemPosition));
                }
            }
        }
        if(arena.getWaitingLobby() != null){
            arenaFile.setLocation("waitingLobby", arena.getWaitingLobby());
        }
        arenaFile.set("maxTeamPlayers", arena.getMaxTeamPlayers());
        arenaFile.saveYamlFile();
        arenas.put(arena.getName(), arena);
    }

    public SimpleYaml getArenaFile(String arenaName){
        SimpleYaml arenaFile = new SimpleYaml(PTC.getPTC(), "arenas/"+arenaName);
        arenaFile.registerYamlFile();
        return arenaFile;
    }
}
