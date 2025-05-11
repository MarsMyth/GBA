package org.mythic_studios.gambler.init.alchohol;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import org.mythic_studios.gambler.GamblersDreamAlcohol;
import org.mythic_studios.gambler.screen.alcohol.BasicFermenterScreenHandler;
import org.mythic_studios.gambler.screen.alcohol.IngredientMixerScreenHandler;
import org.mythic_studios.gambler.screen.alcohol.MechanicalFermenterScreenHandler;

public class AlchoholScreenHandlers {
    public static final ScreenHandlerType<BasicFermenterScreenHandler> BASIC_FERMENTER_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(GamblersDreamAlcohol.MOD_ID, "basic_fermenter_screen_handler"),
                    new ExtendedScreenHandlerType<>(BasicFermenterScreenHandler::new, BlockPos.PACKET_CODEC));


    public static final ScreenHandlerType<MechanicalFermenterScreenHandler> MECHANICAL_FERMENTER_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(GamblersDreamAlcohol.MOD_ID, "mechanical_fermenter_screen_handler"),
                   new ExtendedScreenHandlerType<>(MechanicalFermenterScreenHandler::new, BlockPos.PACKET_CODEC));

    public static final ScreenHandlerType<IngredientMixerScreenHandler> INGREDIENT_MIXER_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(GamblersDreamAlcohol.MOD_ID, "ingredient_mixer_screen_handler"),
                    new ExtendedScreenHandlerType<>(IngredientMixerScreenHandler::new, BlockPos.PACKET_CODEC));

    public static void registerScreenHandlers() {
        GamblersDreamAlcohol.LOGGER.info("Registering Screen Handlers for " + GamblersDreamAlcohol.MOD_ID);
    }
}
