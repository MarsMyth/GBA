package org.mythic_studios.gambler.recipes.alcohol;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.*;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.mythic_studios.gambler.init.ModRecipes;

public class MixingRecipe implements Recipe<MixingRecipeInput> {
    private final Ingredient inputItem1;
    private final Ingredient inputItem2;
    private final ItemStack output;

    public MixingRecipe(Ingredient inputItem1, Ingredient inputItem2, ItemStack output) {
        this.inputItem1 = inputItem1;
        this.inputItem2 = inputItem2;
        this.output = output;
    }

    @Override
    public boolean matches(MixingRecipeInput input, World world) {
        return (inputItem1.test(input.getStackInSlot(0)) && inputItem2.test(input.getStackInSlot(1))) ||
                (inputItem1.test(input.getStackInSlot(1)) && inputItem2.test(input.getStackInSlot(0)));
    }

    @Override
    public ItemStack craft(MixingRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        return output.copy();
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResult(RegistryWrapper.WrapperLookup lookup) {
        return output;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.MIXING_SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.MIXING_TYPE;
    }

    public ItemStack output() {
        return output;
    }

    public Ingredient getinputItem1() {
        return inputItem1;
    }

    public Ingredient getinputItem2() {
        return inputItem2;
    }
}
