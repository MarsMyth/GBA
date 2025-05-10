package org.mythic_studios.gambler.init.alchohol;

import net.minecraft.item.Item;
import org.mythic_studios.gambler.item.ModFoodComponents;

import static org.mythic_studios.gambler.init.alchohol.AlcoholItems.createItem;

public class AlcoholIngredients {

    public static Item GRAPES;

    public static void init() {

        GRAPES = createItem("grapes", new Item(new Item.Settings().food(ModFoodComponents.FRUITS)));

    }
}
