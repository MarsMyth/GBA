package org.mythic_studios.gambler.init;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import org.mythic_studios.gambler.config.GDAconfig;

public class ConfigRegistery {
    public static GDAconfig CONFIG = new GDAconfig();

    public static void register() {
        AutoConfig.register(GDAconfig.class, JanksonConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(GDAconfig.class).getConfig();
    }
}
