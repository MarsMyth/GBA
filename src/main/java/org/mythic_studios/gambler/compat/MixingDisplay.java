package org.mythic_studios.gambler.compat;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.recipe.RecipeEntry;
import org.mythic_studios.gambler.recipes.alcohol.MixingRecipe;

import java.util.ArrayList;
import java.util.List;

public class MixingDisplay extends BasicDisplay {
    public MixingDisplay(RecipeEntry<MixingRecipe> recipe) {
        super(
                getInputs(recipe),
                getOutputs(recipe)
        );

        // Debug logging
        System.out.println("Creating MixingDisplay for recipe: " + recipe.id());
        System.out.println("Ingredients count: " + recipe.value().getIngredients().size());
        System.out.println("Has result: " + (recipe.value().getResult(null) != null));
    }

    private static List<EntryIngredient> getInputs(RecipeEntry<MixingRecipe> recipe) {
        List<EntryIngredient> inputs = new ArrayList<>();

        try {
            EntryIngredient ingredient1 = EntryIngredients.ofIngredient(recipe.value().getinputItem1());
            EntryIngredient ingredient2 = EntryIngredients.ofIngredient(recipe.value().getinputItem2());

            System.out.println("Input 1 has " + ingredient1.size() + " possibilities");
            System.out.println("Input 2 has " + ingredient2.size() + " possibilities");

            inputs.add(ingredient1);
            inputs.add(ingredient2);
        } catch (Exception e) {
            System.err.println("Error processing recipe inputs: " + e.getMessage());
            e.printStackTrace();
        }

        return inputs;
    }


    private static List<EntryIngredient> getOutputs(RecipeEntry<MixingRecipe> recipe) {
        List<EntryIngredient> outputs = new ArrayList<>();

        try {
            if (recipe.value().getResult(null) != null) {
                outputs.add(EntryIngredient.of(EntryStacks.of(recipe.value().getResult(null))));
                System.out.println("Output added successfully");
            } else {
                System.out.println("Recipe result is null");
            }
        } catch (Exception e) {
            System.err.println("Error processing recipe outputs: " + e.getMessage());
            e.printStackTrace();
        }

        return outputs;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return MixingCategory.MIXER;
    }
}