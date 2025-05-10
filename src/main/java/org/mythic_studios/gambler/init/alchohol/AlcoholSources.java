package org.mythic_studios.gambler.init.alchohol;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import org.mythic_studios.gambler.item.ModDamageType;

public class AlcoholSources {
    public static DamageSource alcohol(DamageSources sources) {
        return sources.create(ModDamageType.ALCOHOL);
    }
}