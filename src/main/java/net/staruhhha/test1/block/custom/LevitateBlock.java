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
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.staruhhha.test1.block.ModBlocks;
import net.staruhhha.test1.block.ModTiles;
import net.staruhhha.test1.block.tile.LevitationBlockTile;
import net.staruhhha.test1.utils.TickerUtils;

import javax.annotation.Nullable;

public class LevitateBlock extends Block implements EntityBlock {


    public LevitateBlock() {
        super(Block.Properties.of(Material.STONE)
                .strength(1f));
    }

    @Override
    public void onRemove(BlockState state, Level worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock()) && worldIn.getBlockEntity(pos) instanceof LevitationBlockTile tile) {
            ItemStack stack = tile.getStack();

            if (stack != null && !stack.isEmpty())
                worldIn.addFreshEntity(new ItemEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), stack));
        }

        super.onRemove(state, worldIn, pos, newState, isMoving);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return TickerUtils.getTicker(type, ModTiles.LEVITATION_BLOCK_TILE.get(), LevitationBlockTile::tick);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new LevitationBlockTile(pos, state);
    }

    @Mod.EventBusSubscriber
    public static class Events {
        @SubscribeEvent
        public static void onBlockRightClick(PlayerInteractEvent.RightClickBlock event) {
            InteractionHand hand = event.getHand();

            if (hand != InteractionHand.MAIN_HAND)
                return;

            Level level = event.getLevel();
            BlockPos pos = event.getPos();

            if (!(level.getBlockEntity(pos) instanceof LevitationBlockTile tile))
                return;

            Player player = event.getEntity();

            ItemStack handStack = player.getMainHandItem();
            ItemStack tileStack = tile.getStack();

            BlockState oldState = level.getBlockState(pos);

            if (tileStack.isEmpty()){
                tile.setStack(handStack.split(1));
            }

            tile.setChanged();

            level.sendBlockUpdated(pos, oldState, level.getBlockState(pos), 3);
        }
    }

}
