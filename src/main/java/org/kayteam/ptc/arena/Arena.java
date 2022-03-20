package org.kayteam.ptc.arena;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;
import org.kayteam.api.simple.yaml.SimpleYaml;
import org.kayteam.ptc.game.TeamColour;

import java.io.File;
import java.util.HashMap;

public class Arena {

    private final String name;
    private SimpleYaml file;
    private HashMap<TeamColour, HashMap<Integer, ItemStack>> defaultKit = new HashMap<>();
    private final HashMap<TeamColour, Location> spawnLocations = new HashMap<>();
    private Location waitingLobby;
    private final File worldTemplateDir;
    private final HashMap<TeamColour, Location> coreLocations = new HashMap<>();
    private final HashMap<TeamColour, Location> shopLocations = new HashMap<>();
    private int maxTeamPlayers = 0;

    public Arena(String name, File worldTemplateDir) {
        this.name = name;
        this.worldTemplateDir = worldTemplateDir;
    }

    public String getName() {
        return name;
    }

    public SimpleYaml getFile() {
        return file;
    }

    public HashMap<TeamColour, HashMap<Integer, ItemStack>> getDefaultKit() {
        return defaultKit;
    }

    public void setDefaultKit(HashMap<TeamColour, HashMap<Integer, ItemStack>> defaultKit) {
        this.defaultKit = defaultKit;
    }

    public HashMap<TeamColour, Location> getSpawnLocations() {
        return spawnLocations;
    }

    public Location getWaitingLobby() {
        return waitingLobby;
    }

    public void setWaitingLobby(Location waitingLobby) {
        this.waitingLobby = waitingLobby;
    }

    public File getWorldTemplateDir() {
        return worldTemplateDir;
    }

    public HashMap<TeamColour, Location> getCoreLocations() {
        return coreLocations;
    }

    public HashMap<TeamColour, Location> getShopLocations() {
        return shopLocations;
    }

    public void setFile(SimpleYaml file) {
        this.file = file;
    }

    public int getMaxTeamPlayers() {
        return maxTeamPlayers;
    }

    public void setMaxTeamPlayers(int maxTeamPlayers) {
        this.maxTeamPlayers = maxTeamPlayers;
    }

    public boolean isPlayable(){
        return ((name != null) && (file != null) && (defaultKit.size()>1) && (spawnLocations.values().size()>1) && (waitingLobby != null) &&
                (worldTemplateDir != null) && (coreLocations.values().size()>1) && (shopLocations.values().size()>1) && (maxTeamPlayers>0));
    }
}
