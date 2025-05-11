package org.mythic_studios.gambler.init.alchohol.making;

import net.minecraft.item.Item;
import org.mythic_studios.gambler.item.ModFoodComponents;
import org.mythic_studios.gambler.item.alcohol_section.AlcoholWineSingleItem;

import static org.mythic_studios.gambler.init.alchohol.making.AlcoholItems.createItem;

public class AlcoholGrapeDrinks {

    public static Item UNAGED_GRAPE_WINE;
    public static Item AGED_GRAPE_WINE;


    public static void init() {
        UNAGED_GRAPE_WINE = createItem("unaged_grape_wine", new AlcoholWineSingleItem(new Item.Settings().food(ModFoodComponents.ALCOHOL_UNAGED), false, "Grape"));
        AGED_GRAPE_WINE = createItem("aged_grape_wine", new AlcoholWineSingleItem(new Item.Settings().food(ModFoodComponents.ALCOHOL_AGED), true, "Grape"));
    }
}
