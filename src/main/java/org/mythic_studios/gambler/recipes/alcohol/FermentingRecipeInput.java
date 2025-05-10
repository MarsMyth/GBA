package org.mythic_studios.gambler.recipes.alcohol;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.input.RecipeInput;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.input.RecipeInput;

public record FermentingRecipeInput(ItemStack input) implements RecipeInput {
    @Override
    public ItemStack getStackInSlot(int slot) {
        return input;
    }

    @Override
    public int getSize() {
        return 1;
    }
}
