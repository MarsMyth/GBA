package org.mythic_studios.gambler.block.alcohol;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.mythic_studios.gambler.block.alcohol.entity.BasicFermenterBlockEntity;
import org.mythic_studios.gambler.init.alchohol.AlcoholBlockEntities;
import com.mojang.serialization.MapCodec;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

public class BasicFermenterBlock extends BlockWithEntity {
    public static final MapCodec<BasicFermenterBlock> CODEC = createCodec(BasicFermenterBlock::new);

    public BasicFermenterBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new BasicFermenterBlockEntity(pos, state);
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            if (world.getBlockEntity(pos) instanceof BasicFermenterBlockEntity crystallizerBlockEntity) {
                ItemScatterer.spawn(world, pos, crystallizerBlockEntity);
                world.updateComparators(pos, this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            NamedScreenHandlerFactory screenHandlerFactory = ((BasicFermenterBlockEntity) world.getBlockEntity(pos));

            if (screenHandlerFactory != null) {
                player.openHandledScreen(screenHandlerFactory);
            }
        }

        return ItemActionResult.SUCCESS;
    }

    private static final VoxelShape VOXEL_SHAPE = Stream.of(
            VoxelShapes.combineAndSimplify(Block.createCuboidShape(5, 0, 5, 7, 1, 11), Block.createCuboidShape(6, 1, 5, 7, 2, 11), BooleanBiFunction.OR),
            VoxelShapes.combineAndSimplify(Block.createCuboidShape(9, 0, 5, 11, 1, 11), Block.createCuboidShape(9, 1, 5, 10, 2, 11), BooleanBiFunction.OR),
            Block.createCuboidShape(5, 2, 3, 11, 3, 13),
            Block.createCuboidShape(4, 3, 3, 12, 4, 13),
            Block.createCuboidShape(3, 4, 3, 13, 5, 13),
            Block.createCuboidShape(2, 5, 3, 14, 11, 13),
            Block.createCuboidShape(4, 12, 3, 12, 13, 13),
            Block.createCuboidShape(3, 11, 3, 13, 12, 13),
            Block.createCuboidShape(5, 13, 3, 11, 14, 13)
            ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VOXEL_SHAPE;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(type, AlcoholBlockEntities.BASIC_FERMENTER_BE, (world1, pos, state1, blockEntity) -> blockEntity.tick(world1, pos, state1));
    }
}
