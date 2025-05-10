package org.mythic_studios.gambler.item.alcohol_section;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.mythic_studios.gambler.entity.GooseEntity;
import org.mythic_studios.gambler.init.alchohol.AlcoholItems;

public class JugItem extends Item {
    public JugItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        // Only allow capturing specific entity type (e.g., Cow)
        if (!(entity instanceof GooseEntity)) {
            return TypedActionResult.pass(stack).getResult();
        }

        if (!user.getWorld().isClient) {
            // Remove entity from world
            entity.remove(Entity.RemovalReason.KILLED);

            if (!user.isCreative()) {
                stack.decrement(1);
            }

            // Replace the item with the goose
            ItemStack newStack = new ItemStack(AlcoholItems.GOOSE_IN_A_JUG);
            if (!user.giveItemStack(newStack)) {
                user.dropItem(newStack, false);
            }
        }

        return TypedActionResult.success(user.getStackInHand(hand), user.getWorld().isClient()).getResult();
    }

    // Called when right-clicking on a block (e.g. water)
    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        PlayerEntity user = context.getPlayer();
        ItemStack stack = context.getStack();

        BlockPos targetPos = context.getBlockPos().offset(context.getSide());
        if (world.getFluidState(targetPos).isIn(FluidTags.WATER)) {
            if (!user.getWorld().isClient) {

                if (!user.isCreative()) {
                    stack.decrement(1);
                }

                // Replace the item with the water
                ItemStack newStack = new ItemStack(AlcoholItems.WATER_JUG);
                if (!user.giveItemStack(newStack)) {
                    user.dropItem(newStack, false);
                }
            }
            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }
}
