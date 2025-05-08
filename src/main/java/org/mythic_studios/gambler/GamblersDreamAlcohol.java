package org.mythic_studios.gambler;

import net.fabricmc.api.ModInitializer;

import org.mythic_studios.gambler.init.ConfigRegistery;
import org.mythic_studios.gambler.init.alchohol.AlchoholScreenHandlers;
import org.mythic_studios.gambler.init.alchohol.AlcoholBlockEntities;
import org.mythic_studios.gambler.init.alchohol.AlcoholBlocks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GamblersDreamAlcohol implements ModInitializer {
	public static final String MOD_ID = "gambler";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ConfigRegistery.register();

		AlcoholBlocks.init();
		AlcoholBlockEntities.registerBlockEntities();
		AlchoholScreenHandlers.registerScreenHandlers();

		LOGGER.info("Hello Fabric world!");
	}
}