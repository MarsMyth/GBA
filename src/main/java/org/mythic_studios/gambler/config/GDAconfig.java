package org.mythic_studios.gambler.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "gambler")
@Config.Gui.Background("minecraft:textures/block/stone.png")
public class GDAconfig implements ConfigData {

    @ConfigEntry.Category("general_settings")
    @ConfigEntry.Gui.Tooltip(count = 2)
    public static boolean allowAlcoholPoisoning = true;



}
