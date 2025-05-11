package org.mythic_studios.gambler.item;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import org.mythic_studios.gambler.init.alchohol.AlcoholEffects;

public class ModFoodComponents {

    public static final FoodComponent RECOOKED_PIZZA = new FoodComponent.Builder().nutrition(3).saturationModifier(0.25f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 200), 0.15f).build();

    public static final FoodComponent FRUITS = new FoodComponent.Builder().nutrition(4).saturationModifier(0.55f).snack().build();

    public static final FoodComponent ALCOHOL_UNAGED = new FoodComponent.Builder().nutrition(3).saturationModifier(0.25f)
            .statusEffect(new StatusEffectInstance(AlcoholEffects.ALCOHOL_POISONING, 24000), 40f).alwaysEdible().build();

    public static final FoodComponent ALCOHOL_AGED = new FoodComponent.Builder().nutrition(4).saturationModifier(0.25f)
            .statusEffect(new StatusEffectInstance(AlcoholEffects.ALCOHOL_POISONING, 24000), 0.05f).alwaysEdible().build();
}
