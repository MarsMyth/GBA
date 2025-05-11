package org.mythic_studios.gambler.compat;

import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.client.registry.screen.ScreenRegistry;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.recipe.RecipeType;
import org.mythic_studios.gambler.init.ModRecipes;
import org.mythic_studios.gambler.init.alchohol.AlcoholBlocks;
import org.mythic_studios.gambler.recipes.alcohol.FermentingRecipe;
import org.mythic_studios.gambler.recipes.alcohol.MixingRecipe;
import org.mythic_studios.gambler.screen.alcohol.BasicFermenterScreen;
import org.mythic_studios.gambler.screen.alcohol.IngredientMixerScreen;
import org.mythic_studios.gambler.screen.alcohol.MechanicalFermenterScreen;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class GDAReiClient implements REIClientPlugin {
    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(new FermenterCategory());
        registry.addWorkstations(FermenterCategory.BREWER, EntryStacks.of(AlcoholBlocks.BASIC_FERMENTER));
        registry.addWorkstations(FermenterCategory.BREWER, EntryStacks.of(AlcoholBlocks.MECHANICAL_FERMENTER));

        registry.add(new MixingCategory());

        // Make sure to associate the workstation with the category
        registry.addWorkstations(MixingCategory.MIXER,
                EntryStacks.of(AlcoholBlocks.INGREDIENT_MIXER));
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.registerRecipeFiller(FermentingRecipe.class, ModRecipes.FERMENTING_TYPE,
                FermenterDisplay::new);

        // Debug how many recipes we find
        System.out.println("Registering REI displays for mixing recipes");

        // Get recipe manager - this is a safe approach for REI
        RecipeManager recipeManager = MinecraftClient.getInstance().world != null ?
                MinecraftClient.getInstance().world.getRecipeManager() : null;

        // Fallback for when world isn't loaded yet
        if (recipeManager == null) {
            System.out.println("Recipe manager is null - world not loaded yet");
            // You might register a callback for when the world loads
            return;
        }

        // Find all mixing recipes
        List<RecipeEntry<MixingRecipe>> recipes = recipeManager.listAllOfType(ModRecipes.MIXING_TYPE);
        System.out.println("Found " + recipes.size() + " mixing recipes");

        // Use a set to avoid duplicates
        Set<String> processedRecipeIds = new HashSet<>();

        // Process each recipe - avoid duplicates
        for (RecipeEntry<MixingRecipe> recipe : recipes) {
            if (!processedRecipeIds.contains(recipe.id().toString())) {
                try {
                    // Debug info
                    System.out.println("Processing recipe: " + recipe.id());

                    // Create and register display
                    MixingDisplay display = new MixingDisplay(recipe);
                    registry.add(display);

                    // Mark as processed
                    processedRecipeIds.add(recipe.id().toString());
                } catch (Exception e) {
                    System.err.println("Error registering display for recipe " + recipe.id() + ": " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }

        System.out.println("Registered " + processedRecipeIds.size() + " displays");
    }

    @Override
    public void registerScreens(ScreenRegistry registry) {
        registry.registerClickArea(screen -> new Rectangle(((screen.width - 176) / 2) + 78,
                        ((screen.height - 166) / 2) + 30, 20, 25), BasicFermenterScreen.class,
                FermenterCategory.BREWER);

        registry.registerClickArea(screen -> new Rectangle(((screen.width - 176) / 2) + 78,
                        ((screen.height - 166) / 2) + 30, 20, 25), MechanicalFermenterScreen.class,
                FermenterCategory.BREWER);

        registry.registerClickArea(screen -> new Rectangle(((screen.width - 176) / 2) + 78,
                        ((screen.height - 166) / 2) + 30, 20, 25), IngredientMixerScreen.class,
                MixingCategory.MIXER);
    }
}
