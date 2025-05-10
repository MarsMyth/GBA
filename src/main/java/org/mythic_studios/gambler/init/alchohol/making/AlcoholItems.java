package org.mythic_studios.gambler.init.alchohol.making;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.mythic_studios.gambler.GamblersDreamAlcohol;
import org.mythic_studios.gambler.item.ModFoodComponents;
import org.mythic_studios.gambler.item.alcohol_section.JugItem;

import java.util.List;

public class AlcoholItems {

    public static Item EMPTY_JUG;
    public static Item GOOSE_IN_A_JUG;
    public static Item WATER_JUG;
    public static Item GRAPE_JUICE_JUG;

    public static Item EMPTY_WINE_BOTTLE;

    public static Item PIZZA_SLICE;
    public static Item RECOOKED_PIZZA;

    public static void init() {

        EMPTY_JUG = createItem("empty_jug", new JugItem(new Item.Settings()));
        WATER_JUG = createItem("water_jug", new Item(new Item.Settings()));
        GRAPE_JUICE_JUG = createItem("grape_juice_jug", new Item(new Item.Settings()));
        GOOSE_IN_A_JUG = createItem("goose_jug", new Item(new Item.Settings()) {
            @Override
            public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {

                tooltip.add(Text.literal(" "));
                tooltip.add(Text.translatable("tooltip.goose_jug.1"));
                tooltip.add(Text.translatable("tooltip.goose_jug.2"));

                super.appendTooltip(stack, context, tooltip, type);
            }
        });

        PIZZA_SLICE = createItem("pizza_slice", new Item(new Item.Settings()) {
            @Override
            public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {

                tooltip.add(Text.literal(" "));
                tooltip.add(Text.translatable("tooltip.pizza.1"));
                tooltip.add(Text.translatable("tooltip.pizza.2"));

                super.appendTooltip(stack, context, tooltip, type);
            }
        });

        RECOOKED_PIZZA = createItem("recooked_pizza", new Item(new Item.Settings().food(ModFoodComponents.RECOOKED_PIZZA)) {
            @Override
            public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {

                tooltip.add(Text.literal(" "));
                tooltip.add(Text.translatable("tooltip.recooked_pizza.1"));
                tooltip.add(Text.translatable("tooltip.recooked_pizza.2"));

                super.appendTooltip(stack, context, tooltip, type);
            }
        });

        EMPTY_WINE_BOTTLE = createItem("empty_wine_bottle", new Item(new Item.Settings()));
    }


    static Item createItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(GamblersDreamAlcohol.MOD_ID, name), item);
    }
}
