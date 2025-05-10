package org.mythic_studios.gambler.effect.alcohol;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.entry.RegistryEntry;
import org.mythic_studios.gambler.config.GDAconfig;
import org.mythic_studios.gambler.init.alchohol.AlcoholSources;
import org.mythic_studios.gambler.item.ModDamageType;

public class AlcoholPoisoningEffect extends StatusEffect {
    private static final int DAMAGE_INTERVAL = 140; // 7 seconds (140 ticks)



    public AlcoholPoisoningEffect() {
        super(StatusEffectCategory.HARMFUL, 0x800080); // Purple color
    }

    // No canBeRemovedByMilk method as it doesn't exist in current API
    // We'll handle milk resistance in a different way

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (GDAconfig.allowAlcoholPoisoning){
            DamageSources sources = entity.getDamageSources();
            DamageSource alcoholSource = AlcoholSources.alcohol(sources); // correct constructor

            entity.damage(alcoholSource, 3.0F + amplifier * 0.5F);
            return true;
        } // Indicates the effect was applied successfully
        return false;
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return duration % DAMAGE_INTERVAL == 0;
    }


}


