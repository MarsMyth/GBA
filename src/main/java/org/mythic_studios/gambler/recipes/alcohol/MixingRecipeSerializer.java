package org.mythic_studios.gambler.recipes.alcohol;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.network.RegistryByteBuf;

public class MixingRecipeSerializer implements RecipeSerializer<MixingRecipe> {

    // Custom Codec for ItemStack
    public static final Codec<ItemStack> ITEMSTACK_CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.STRING.fieldOf("item").forGetter(itemStack -> Registries.ITEM.getId(itemStack.getItem()).toString()),
                    Codec.INT.fieldOf("count").forGetter(ItemStack::getCount)
            ).apply(instance, (item, count) -> new ItemStack(Registries.ITEM.get(Identifier.tryParse(item)), count))
    );

    public static final MapCodec<MixingRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
            Ingredient.DISALLOW_EMPTY_CODEC.fieldOf("ingredient1").forGetter(MixingRecipe::getinputItem1),
            Ingredient.DISALLOW_EMPTY_CODEC.fieldOf("ingredient2").forGetter(MixingRecipe::getinputItem2),
            ITEMSTACK_CODEC.fieldOf("result").forGetter(MixingRecipe::output)
    ).apply(inst, MixingRecipe::new));

    public static final PacketCodec<RegistryByteBuf, MixingRecipe> STREAM_CODEC = PacketCodec.tuple(
            Ingredient.PACKET_CODEC, MixingRecipe::getinputItem1,
            Ingredient.PACKET_CODEC, MixingRecipe::getinputItem2,
            ItemStack.PACKET_CODEC,
            MixingRecipe::output,
            MixingRecipe::new
    );

    @Override
    public MapCodec<MixingRecipe> codec() {
        return CODEC;
    }

    @Override
    public PacketCodec<RegistryByteBuf, MixingRecipe> packetCodec() {
        return STREAM_CODEC;
    }
}
