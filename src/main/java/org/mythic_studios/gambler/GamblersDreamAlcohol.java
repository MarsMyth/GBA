package org.mythic_studios.gambler;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.entity.event.v1.EntitySleepEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.server.network.ServerPlayerEntity;
import org.mythic_studios.gambler.config.GDAconfig;
import org.mythic_studios.gambler.entity.GooseEntity;
import org.mythic_studios.gambler.init.ConfigRegistery;
import org.mythic_studios.gambler.init.ModEntities;
import org.mythic_studios.gambler.init.ModRecipes;
import org.mythic_studios.gambler.init.alchohol.*;
import org.mythic_studios.gambler.init.ModWorldGen;
import org.mythic_studios.gambler.init.alchohol.making.AlcoholGrapeDrinks;
import org.mythic_studios.gambler.init.alchohol.making.AlcoholIngredients;
import org.mythic_studios.gambler.init.alchohol.making.AlcoholItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class GamblersDreamAlcohol implements ModInitializer {
	public static final String MOD_ID = "gambler";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	private static final Map<UUID, StatusEffectData> playerHadAlcoholEffect = new HashMap<>();

	// Simple class to store effect data
	private static class StatusEffectData {
		final int duration;
		final int amplifier;

		public StatusEffectData(int duration, int amplifier) {
			this.duration = duration;
			this.amplifier = amplifier;
		}
	}

	@Override
	public void onInitialize() {
		ConfigRegistery.register();
		ModEntities.registerModEntities();
		ModWorldGen.generateModWorldGen();

		ModRecipes.register();

		AlcoholBlocks.init();
		AlcoholBlockEntities.registerBlockEntities();
		AlcoholEffects.init();
		AlcoholItems.init();
		AlcoholGrapeDrinks.init();
		AlcoholIngredients.init();
		AlchoholScreenHandlers.registerScreenHandlers();

		LOGGER.info("Hello Fabric world!");

		FabricDefaultAttributeRegistry.register(ModEntities.GOOSE, GooseEntity.createAttributes());

		ServerTickEvents.END_SERVER_TICK.register(server -> {
			if (GDAconfig.allowAlcoholPoisoning) {
				server.getPlayerManager().getPlayerList().forEach(player -> {
					// Check if player has just consumed milk (has MILK_BUCKET_EFFECT_REMOVAL_FLAG)
					if (player.getStatusEffects().isEmpty() && player.getActiveStatusEffects().isEmpty()) {
						// If player had our effect previously (we use a static field to track this)
						if (playerHadAlcoholEffect.containsKey(player.getUuid())) {
							// Get stored effect data
							StatusEffectData effectData = playerHadAlcoholEffect.get(player.getUuid());
							// Re-apply our effect with the same duration and amplifier
							player.addStatusEffect(new StatusEffectInstance(
									AlcoholEffects.ALCOHOL_POISONING,
									effectData.duration,
									effectData.amplifier
							));
							// Remove from tracking map
							playerHadAlcoholEffect.remove(player.getUuid());

							if (effectData.duration == 0) {
								player.removeStatusEffect(AlcoholEffects.ALCOHOL_POISONING);
							}
						}
					}

					// If player has our effect, store it for potential milk consumption
					if (player.hasStatusEffect(AlcoholEffects.ALCOHOL_POISONING)) {
						StatusEffectInstance effect = player.getStatusEffect(AlcoholEffects.ALCOHOL_POISONING);
						playerHadAlcoholEffect.put(player.getUuid(), new StatusEffectData(
								effect.getDuration(),
								effect.getAmplifier()
						));
					}
				});
			} else {
				server.getPlayerManager().getPlayerList().forEach(player -> {
					// If player has our effect, store it for potential milk consumption
					if (player.hasStatusEffect(AlcoholEffects.ALCOHOL_POISONING)) {
						playerHadAlcoholEffect.remove(player.getUuid());
						StatusEffectInstance effect = player.getStatusEffect(AlcoholEffects.ALCOHOL_POISONING);
						playerHadAlcoholEffect.remove(player.getUuid(), new StatusEffectData(
								effect.getDuration(),
								effect.getAmplifier()
						));
					}
				})
			;}
		});

		// Register sleep event to remove alcohol poisoning when player sleeps
		EntitySleepEvents.STOP_SLEEPING.register((entity, sleepingPos) -> {
			// Check if entity is a player and has our effect
			if (entity instanceof ServerPlayerEntity player && player.hasStatusEffect(AlcoholEffects.ALCOHOL_POISONING)) {
                player.removeStatusEffect(AlcoholEffects.ALCOHOL_POISONING);
				playerHadAlcoholEffect.remove(player.getUuid());
			}
		});
	}
}