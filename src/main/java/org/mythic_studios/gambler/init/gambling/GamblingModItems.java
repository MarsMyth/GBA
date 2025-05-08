package org.mythic_studios.gambler.init.gambling;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.mythic_studios.gambler.GamblersDreamAlcohol;

public class GamblingModItems {

    public static Item GAMBLERS_DIE_6;

    public static void init() {



    }


    private static Item createItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(GamblersDreamAlcohol.MOD_ID, name), item);
    }
}
