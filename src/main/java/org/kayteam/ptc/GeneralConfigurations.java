package org.kayteam.ptc;

import org.kayteam.api.yaml.Yaml;

public class GeneralConfigurations {

    public int initialCoreLives = 0;
    public int defaultGameDuration = 0;

    public final Yaml settings = new Yaml(PTC.getPTC(), "settings");

    public GeneralConfigurations() {
        settings.registerFileConfiguration();
        load();
    }

    private void load(){
        initialCoreLives = settings.getInt("initalCoreLives");
        defaultGameDuration = settings.getInt("defaultGameDuration");
    }
}
