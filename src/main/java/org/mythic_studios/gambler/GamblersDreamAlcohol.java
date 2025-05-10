package org.mythic_studios.gambler;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import org.mythic_studios.gambler.entity.GooseEntity;
import org.mythic_studios.gambler.init.ConfigRegistery;
import org.mythic_studios.gambler.init.ModEntities;
import org.mythic_studios.gambler.init.ModRecipes;
import org.mythic_studios.gambler.init.alchohol.AlchoholScreenHandlers;
import org.mythic_studios.gambler.init.alchohol.AlcoholBlockEntities;
import org.mythic_studios.gambler.init.alchohol.AlcoholBlocks;
import org.mythic_studios.gambler.init.alchohol.AlcoholItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GamblersDreamAlcohol implements ModInitializer {
	public static final String MOD_ID = "gambler";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ConfigRegistery.register();
		ModEntities.registerModEntities();

		ModRecipes.register();

		AlcoholBlocks.init();
		AlcoholBlockEntities.registerBlockEntities();
		AlcoholItems.init();
		AlchoholScreenHandlers.registerScreenHandlers();

		LOGGER.info("Hello Fabric world!");

		FabricDefaultAttributeRegistry.register(ModEntities.GOOSE, GooseEntity.createAttributes());
	}
}