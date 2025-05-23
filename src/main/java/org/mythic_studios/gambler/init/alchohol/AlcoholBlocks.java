package org.mythic_studios.gambler.init.alchohol;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.mythic_studios.gambler.GamblersDreamAlcohol;
import org.mythic_studios.gambler.block.alcohol.BasicFermenterBlock;
import org.mythic_studios.gambler.block.alcohol.IngredientMixerBlock;
import org.mythic_studios.gambler.block.alcohol.MechanicalFermenterBlock;

public class AlcoholBlocks {

    public static Block BASIC_FERMENTER;
    public static Block MECHANICAL_FERMENTER;
    public static Block INGREDIENT_MIXER;

    public static void init() {

        BASIC_FERMENTER = registerBlock("basic_fermenter", new BasicFermenterBlock(AbstractBlock.Settings.copy(Blocks.STONE_BRICKS)));
        MECHANICAL_FERMENTER = registerBlock("mechanical_fermenter", new MechanicalFermenterBlock(AbstractBlock.Settings.copy(Blocks.STONE_BRICKS)));
        INGREDIENT_MIXER = registerBlock("ingredient_mixer", new IngredientMixerBlock(AbstractBlock.Settings.copy(Blocks.STONE_BRICKS)));

    }


    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(GamblersDreamAlcohol.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(GamblersDreamAlcohol.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }
}
