package org.kayteam.ptc;

import com.cryptomorin.xseries.XMaterial;
import me.neznamy.tab.api.scoreboard.Scoreboard;
import org.bukkit.Location;
import org.bukkit.Material;
import org.kayteam.api.yaml.Yaml;
import org.kayteam.ptc.player.GamePlayerStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GeneralConfigurations {

    public final Yaml settings = new Yaml(PTC.getPTC(), "settings");

    public HashMap<GamePlayerStatus, Scoreboard> scoreboardStatus = new HashMap<>();

    public int initialCoreLives = 0;
    public int defaultGameDuration = 0;
    public Location lobbyLocation;
    public final HashMap<Material, Integer> cooldownMaterials = new HashMap<>();

    public GeneralConfigurations() {
        settings.registerFileConfiguration();
        load();
    }

    private void load(){
        //
        initialCoreLives = settings.getInt("initalCoreLives");
        //
        defaultGameDuration = settings.getInt("defaultGameDuration");
        //
        lobbyLocation = settings.getLocation("lobbyLocation");
        //
        for(String materialName : settings.getFileConfiguration().getConfigurationSection("cooldownMaterials").getKeys(false)){
            XMaterial xmaterial = XMaterial.valueOf(materialName);
            Material material = xmaterial.parseMaterial();
            if(material != null){
                int cooldown = settings.getInt("cooldownMaterials."+materialName);
                cooldownMaterials.put(material, cooldown);
            }else{
                PTC.getPTC().getLogger().warning("Material "+materialName+" is an invalid material name");
            }
        }
        //
        List<String> inLobbyScoreboard = new ArrayList<>();
        inLobbyScoreboard.add("&7");
        inLobbyScoreboard.add(" &3Monedas: &f%ptc_coins%");
        inLobbyScoreboard.add(" &3Usuario: &f%player_name%");
        inLobbyScoreboard.add("&7");
        inLobbyScoreboard.add(" &3Muertes: &f%statistic_deaths%");
        inLobbyScoreboard.add(" &3Asesinatos: &f%statistic_player_kills%");
        inLobbyScoreboard.add("&7");
        inLobbyScoreboard.add(" &3Victorias: &f%ptc_victories%");
        inLobbyScoreboard.add(" &3Derrotas: &f%ptc_defeats%");
        inLobbyScoreboard.add(" &3Nucleos: &f%ptc_cores%");
        inLobbyScoreboard.add("");
        Scoreboard scoreboard = PTC.getTabAPI().getScoreboardManager().createScoreboard("playing", "&b&lPTC", inLobbyScoreboard);
        scoreboardStatus.put(GamePlayerStatus.IN_LOBBY, scoreboard);
    }
}
