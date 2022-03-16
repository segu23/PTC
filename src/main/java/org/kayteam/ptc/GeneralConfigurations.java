package org.kayteam.ptc;

import com.cryptomorin.xseries.XMaterial;
import org.bukkit.Location;
import org.bukkit.Material;
import org.kayteam.api.yaml.Yaml;

import java.util.HashMap;

public class GeneralConfigurations {

    public final Yaml settings = new Yaml(PTC.getPTC(), "settings");

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
    }
}
