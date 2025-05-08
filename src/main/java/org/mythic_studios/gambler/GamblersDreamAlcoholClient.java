package org.mythic_studios.gambler;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import org.mythic_studios.gambler.init.alchohol.AlchoholScreenHandlers;
import org.mythic_studios.gambler.screen.alcohol.BasicFermenterScreen;

public class GamblersDreamAlcoholClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {



        HandledScreens.register(AlchoholScreenHandlers.BASIC_FERMENTER_SCREEN_HANDLER, BasicFermenterScreen::new);
    }
}
