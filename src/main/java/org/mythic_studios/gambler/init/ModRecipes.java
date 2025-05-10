package org.mythic_studios.gambler.init;

import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.mythic_studios.gambler.GamblersDreamAlcohol;
import org.mythic_studios.gambler.recipes.alcohol.FermentingRecipe;
import org.mythic_studios.gambler.recipes.alcohol.MixingRecipe;
import org.mythic_studios.gambler.recipes.alcohol.serializer.MixingRecipeSerializer;

public class ModRecipes {
    public static final RecipeSerializer<MixingRecipe> MIXING_SERIALIZER =
            Registry.register(Registries.RECIPE_SERIALIZER, Identifier.of("gambler", "mixing"), new MixingRecipeSerializer());

    public static final RecipeType<MixingRecipe> MIXING_TYPE =
            Registry.register(Registries.RECIPE_TYPE, Identifier.of("gambler", "mixing"), new RecipeType<>() {
                public String toString() {
                    return "gambler:mixing";
                }
            });

    public static final RecipeSerializer<FermentingRecipe> FERMENTING_RECIPE_SERIALIZER = Registry.register(
            Registries.RECIPE_SERIALIZER, Identifier.of(GamblersDreamAlcohol.MOD_ID, "fermenting"), new FermentingRecipe.Serializer());
    public static final RecipeType<FermentingRecipe> FERMENTING_TYPE = Registry.register(
            Registries.RECIPE_TYPE, Identifier.of(GamblersDreamAlcohol.MOD_ID, "fermenting"), new RecipeType<>() {
                @Override
                public String toString() {
                    return "crystallizing";
                }
            });

    public static void register() {
        // This method must be called during mod init
    }
}
