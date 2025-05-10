package org.mythic_studios.gambler.init.alchohol;

import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import org.mythic_studios.gambler.GamblersDreamAlcohol;
import org.mythic_studios.gambler.effect.alcohol.AlcoholPoisoningEffect;

public class AlcoholEffects {

    public static RegistryEntry<StatusEffect> ALCOHOL_POISONING;

    public static void init(){

        ALCOHOL_POISONING = registerStatusEffect("alcohol_poisoning",
                new AlcoholPoisoningEffect()
                        .addAttributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED,
                                Identifier.of(GamblersDreamAlcohol.MOD_ID, "alcohol_poisoning"), -0.25f,
                                EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));

    }

    private static RegistryEntry<StatusEffect> registerStatusEffect(String name, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(GamblersDreamAlcohol.MOD_ID, name), statusEffect);
    }
}
