package org.mythic_studios.gambler.init;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.mythic_studios.gambler.GamblersDreamAlcohol;
import org.mythic_studios.gambler.entity.GooseEntity;

public class ModEntities {
    public static final EntityType<GooseEntity> GOOSE = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(GamblersDreamAlcohol.MOD_ID, "goose"),
            EntityType.Builder.create(GooseEntity::new, SpawnGroup.CREATURE)
                    .dimensions(0.25f, 0.5f).build());


    public static void registerModEntities() {
        GamblersDreamAlcohol.LOGGER.info("Registering Mod Entities for " + GamblersDreamAlcohol.MOD_ID);
    }
}
