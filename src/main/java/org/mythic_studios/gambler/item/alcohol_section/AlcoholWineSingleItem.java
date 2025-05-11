package org.mythic_studios.gambler.item.alcohol_section;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

import java.util.List;
import java.util.Objects;

public class AlcoholWineSingleItem extends Item {
    public boolean aged;
    public String ingredient;

    public AlcoholWineSingleItem(Settings settings, boolean aged, String ingredient) {
        super(settings);
        this.aged = aged;
        this.ingredient = ingredient;
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return aged;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {

        tooltip.add(Text.literal(" "));
        if (Screen.hasShiftDown() && !Screen.hasControlDown()) {
            if (aged) {
                tooltip.add(Text.translatable("tooltip.wine.aged"));
            } else {
                tooltip.add(Text.translatable("tooltip.wine.unaged"));
            }
        } else if (Screen.hasControlDown() && !Screen.hasShiftDown()) {
            tooltip.add(Text.translatable("tooltip.ingredient.1"));
            if (Objects.equals(ingredient, "Grape")) {
                tooltip.add(Text.translatable("tooltip.ingredient.grape"));
            } else if (Objects.equals(ingredient, "Goose")) {
                tooltip.add(Text.translatable("tooltip.ingredient.goose"));
            }

        } else {
            tooltip.add(Text.translatable("tooltip.shift"));
            tooltip.add(Text.translatable("tooltip.ctrl"));
        }
        super.appendTooltip(stack, context, tooltip, type);
    }
}
