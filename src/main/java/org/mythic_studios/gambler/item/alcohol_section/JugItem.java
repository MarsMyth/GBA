package org.mythic_studios.gambler.item.alcohol_section;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import org.mythic_studios.gambler.init.alchohol.AlcoholItems;

public class JugItem extends Item {
    public JugItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        // Only allow capturing specific entity type (e.g., Cow)
        if (!(entity instanceof CowEntity)) {
            return TypedActionResult.pass(stack).getResult();
        }

        if (!user.getWorld().isClient) {
            // Remove entity from world
            entity.remove(Entity.RemovalReason.KILLED);

            // Replace the item with the "captured" item
            ItemStack newStack = new ItemStack(AlcoholItems.GOOSE_IN_A_JUG);


            // Replace item in hand
            user.setStackInHand(hand, newStack);
        }

        return TypedActionResult.success(user.getStackInHand(hand), user.getWorld().isClient()).getResult();
    }
}
