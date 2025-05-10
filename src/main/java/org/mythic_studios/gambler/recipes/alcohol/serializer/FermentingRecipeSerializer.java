package org.mythic_studios.gambler.recipes.alcohol.serializer;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.mythic_studios.gambler.recipes.alcohol.FermentingRecipe;
import org.mythic_studios.gambler.recipes.alcohol.MixingRecipe;

public class FermentingRecipeSerializer implements RecipeSerializer<FermentingRecipe> {
    // Custom Codec for ItemStack
    public static final Codec<ItemStack> ITEMSTACK_CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.STRING.fieldOf("item").forGetter(itemStack -> Registries.ITEM.getId(itemStack.getItem()).toString()),
                    Codec.INT.fieldOf("count").forGetter(ItemStack::getCount)
            ).apply(instance, (item, count) -> new ItemStack(Registries.ITEM.get(Identifier.tryParse(item)), count))
    );

    public static final MapCodec<FermentingRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
            Ingredient.DISALLOW_EMPTY_CODEC.fieldOf("ingredient").forGetter(FermentingRecipe::inputItem),
            ITEMSTACK_CODEC.fieldOf("result").forGetter(FermentingRecipe::output)
    ).apply(inst, FermentingRecipe::new));

    public static final PacketCodec<RegistryByteBuf, FermentingRecipe> STREAM_CODEC = PacketCodec.tuple(
            Ingredient.PACKET_CODEC, FermentingRecipe::inputItem,
            ItemStack.PACKET_CODEC, FermentingRecipe::output,
            FermentingRecipe::new
    );

    @Override
    public MapCodec<FermentingRecipe> codec() {
        return CODEC;
    }

    @Override
    public PacketCodec<RegistryByteBuf, FermentingRecipe> packetCodec() {
        return STREAM_CODEC;
    }
}
