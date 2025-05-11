package org.mythic_studios.gambler.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import org.mythic_studios.gambler.init.alchohol.AlcoholBlocks;
import org.mythic_studios.gambler.init.alchohol.making.AlcoholGrapeDrinks;
import org.mythic_studios.gambler.init.alchohol.making.AlcoholIngredients;
import org.mythic_studios.gambler.init.alchohol.making.AlcoholItems;

public class AlcoholModelGenerator extends FabricModelProvider {
    public AlcoholModelGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

        blockStateModelGenerator.registerSimpleCubeAll(AlcoholBlocks.MECHANICAL_FERMENTER);
        blockStateModelGenerator.registerSimpleCubeAll(AlcoholBlocks.INGREDIENT_MIXER);

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

        itemModelGenerator.register(AlcoholItems.EMPTY_JUG, Models.GENERATED);
        itemModelGenerator.register(AlcoholItems.GOOSE_IN_A_JUG, Models.GENERATED);
        itemModelGenerator.register(AlcoholItems.WATER_JUG, Models.GENERATED);
        itemModelGenerator.register(AlcoholItems.GRAPE_JUICE_JUG, Models.GENERATED);

        itemModelGenerator.register(AlcoholItems.EMPTY_WINE_BOTTLE, Models.GENERATED);

        itemModelGenerator.register(AlcoholGrapeDrinks.UNAGED_GRAPE_WINE, Models.GENERATED);
        itemModelGenerator.register(AlcoholGrapeDrinks.AGED_GRAPE_WINE, Models.GENERATED);

        itemModelGenerator.register(AlcoholIngredients.GRAPES, Models.GENERATED);

        itemModelGenerator.register(AlcoholItems.PIZZA_SLICE, Models.GENERATED);
        itemModelGenerator.register(AlcoholItems.RECOOKED_PIZZA, Models.GENERATED);

    }
}
