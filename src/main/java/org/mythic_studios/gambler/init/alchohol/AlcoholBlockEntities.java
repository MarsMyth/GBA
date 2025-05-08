package org.mythic_studios.gambler.init.alchohol;

import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.mythic_studios.gambler.GamblersDreamAlcohol;
import org.mythic_studios.gambler.block.entity.BasicFermenterBlockEntity;

public class AlcoholBlockEntities {

    public static final BlockEntityType<BasicFermenterBlockEntity> BASIC_FERMENTER_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(GamblersDreamAlcohol.MOD_ID, "basic_fermenter_be"),
                    BlockEntityType.Builder.create(BasicFermenterBlockEntity::new, AlcoholBlocks.BASIC_FERMENTER).build(null));

//    public static final BlockEntityType<InfuserBlockEntity> INFUSER_BE =
//            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(GamblersDreamAlcohol.MOD_ID, "infuser_be"),
//                    BlockEntityType.Builder.create(InfuserBlockEntity::new, ModBlocks.INFUSER).build(null));

    public static void registerBlockEntities() {
        GamblersDreamAlcohol.LOGGER.info("Registering Block Entities for " + GamblersDreamAlcohol.MOD_ID);
    }
}
