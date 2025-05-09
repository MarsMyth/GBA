package org.mythic_studios.gambler.init.alchohol;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.mythic_studios.gambler.GamblersDreamAlcohol;
import org.mythic_studios.gambler.item.alcohol_section.JugItem;

import java.util.List;

public class AlcoholItems {

    public static Item EMPTY_JUG;
    public static Item GOOSE_IN_A_JUG;

    public static void init() {

        EMPTY_JUG = createItem("empty_jug", new JugItem(new Item.Settings()));
        GOOSE_IN_A_JUG = createItem("goose_jug", new Item(new Item.Settings()) {
            @Override
            public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {

                tooltip.add(Text.literal(" "));
                tooltip.add(Text.translatable("tooltip.goose_jug.1"));
                tooltip.add(Text.translatable("tooltip.goose_jug.2"));

                super.appendTooltip(stack, context, tooltip, type);
            }
        });

    }


    private static Item createItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(GamblersDreamAlcohol.MOD_ID, name), item);
    }
}
