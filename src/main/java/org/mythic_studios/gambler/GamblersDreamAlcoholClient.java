package org.mythic_studios.gambler;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import org.mythic_studios.gambler.entity.GooseEntity;
import org.mythic_studios.gambler.entity.client.goose.GooseModel;
import org.mythic_studios.gambler.entity.client.goose.GooseRenderer;
import org.mythic_studios.gambler.init.ModEntities;
import org.mythic_studios.gambler.init.alchohol.AlchoholScreenHandlers;
import org.mythic_studios.gambler.screen.alcohol.BasicFermenterScreen;

public class GamblersDreamAlcoholClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {


        EntityModelLayerRegistry.registerModelLayer(GooseModel.GOOSE, GooseModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.GOOSE, GooseRenderer::new);

        HandledScreens.register(AlchoholScreenHandlers.BASIC_FERMENTER_SCREEN_HANDLER, BasicFermenterScreen::new);
    }
}
