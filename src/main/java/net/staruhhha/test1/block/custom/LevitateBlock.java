package net.staruhhha.test1.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.staruhhha.test1.block.ModBlocks;
import net.staruhhha.test1.block.custom.tile.LevitateBlockTile;

public class LevitateBlock extends Block implements EntityBlock, SimpleWaterloggedBlock {


    public LevitateBlock() {
        super(Block.Properties.of(Material.STONE)
                .strength(1f));
        registerDefaultState(defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, false));
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        if (hand != InteractionHand.MAIN_HAND)
            return InteractionResult.PASS;
        if (!level.isClientSide && level.getBlockEntity(pos) instanceof LevitateBlockTile tile) {
            if (tile.getStack() != null && player.getItemInHand(hand).isEmpty()) {
                ItemEntity item = new ItemEntity(level, player.getX(), player.getY(), player.getZ(), tile.getStack());
                level.addFreshEntity(item);
                tile.setStack(ItemStack.EMPTY);
            } else if (!player.getInventory().getSelected().isEmpty()) {
                if (tile.getStack() != null) {
                    ItemEntity item = new ItemEntity(level, player.getX(), player.getY(), player.getZ(), tile.getStack());
                    level.addFreshEntity(item);
                }
                tile.setStack(player.getInventory().removeItem(player.getInventory().selected, 1));
            }
            level.sendBlockUpdated(pos, state, state, 2);
        }
        return InteractionResult.SUCCESS;
        //return super.use(state, level, pos, player, hand, result);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new LevitateBlockTile(pos, state);
    }

    @Override
    public void playerWillDestroy(Level worldIn, BlockPos pos, BlockState state, Player player) {
        super.playerWillDestroy(worldIn, pos, state, player);
        if (worldIn.getBlockEntity(pos) instanceof LevitateBlockTile tile && tile.getStack() != null) {
            worldIn.addFreshEntity(new ItemEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), tile.getStack()));
        }
    }
}
