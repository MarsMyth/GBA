package org.mythic_studios.gambler.init.alchohol.making;

import net.minecraft.item.Item;
import org.mythic_studios.gambler.item.ModFoodComponents;

import static org.mythic_studios.gambler.init.alchohol.making.AlcoholItems.createItem;

public class AlcoholGrapeDrinks {

    public static Item UNAGED_GRAPE_WINE;


    public static void init() {
        UNAGED_GRAPE_WINE = createItem("unaged_grape_wine", new Item(new Item.Settings().food(ModFoodComponents.ALCOHOL_UNAGED)));
    }
}
