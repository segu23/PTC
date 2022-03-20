package org.kayteam.ptc;

import com.cryptomorin.xseries.XMaterial;
import me.neznamy.tab.api.bossbar.BarColor;
import me.neznamy.tab.api.bossbar.BarStyle;
import me.neznamy.tab.api.bossbar.BossBar;
import me.neznamy.tab.api.scoreboard.Scoreboard;
import org.bukkit.Location;
import org.bukkit.Material;
import org.kayteam.api.simple.yaml.SimpleYaml;
import org.kayteam.ptc.player.GamePlayerStatus;

import java.util.HashMap;
import java.util.List;

public class GeneralConfigurations {

    public final SimpleYaml settings = new SimpleYaml(PTC.getPTC(), "settings");

    public HashMap<GamePlayerStatus, Scoreboard> scoreboardStatus = new HashMap<>();

    public int initialCoreLives = 0;
    public int defaultGameDuration = 0;
    public Location lobbyLocation;
    public final HashMap<Material, Integer> cooldownMaterials = new HashMap<>();
    public String bossBarTitle;
    public BossBar bossBar;
    public int bossBarProgress = 100;
    public Location mainLobby;

    public GeneralConfigurations() {
        settings.registerYamlFile();
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
        bossBarTitle = PTC.messages.getString("bossBar.PLAYING");
        //
        mainLobby = settings.getLocation("mainLobby");
        //
        for(String materialName : settings.getConfigurationSection("cooldownMaterials").getKeys(false)){
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
        for(String gameStatusKey : PTC.messages.getConfigurationSection("scoreboard").getKeys(false)){
            try{
                String scoreboardTitle = PTC.messages.getString("scoreboard."+gameStatusKey+".title");
                List<String> scoreboardLines = PTC.messages.getStringList("scoreboard."+gameStatusKey+".lines");
                GamePlayerStatus gamePlayerStatus = GamePlayerStatus.valueOf(gameStatusKey);
                Scoreboard scoreboard = PTC.getTabAPI().getScoreboardManager().createScoreboard(gamePlayerStatus.toString(), scoreboardTitle, scoreboardLines);
                scoreboardStatus.put(GamePlayerStatus.IN_LOBBY, scoreboard);
            }catch (Exception e){
                PTC.getPTC().getLogger().warning("An error has occurred trying to load "+gameStatusKey+" scoreboard");
            }
        }
        //
        bossBar = PTC.getTabAPI().getBossBarManager().createBossBar(bossBarTitle, bossBarProgress, BarColor.GREEN, BarStyle.PROGRESS);
    }
}
