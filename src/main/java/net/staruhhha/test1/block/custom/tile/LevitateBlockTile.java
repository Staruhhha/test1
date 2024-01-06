package net.staruhhha.test1.block.custom.tile;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Container;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.staruhhha.test1.block.ModBlocks;

public class LevitateBlockTile extends SingleItemTile implements Container {


    public float frames;
    public boolean hasSignal;

    public LevitateBlockTile(BlockEntityType<?> tileEntityTypeIn, BlockPos pos, BlockState state) {
        super(tileEntityTypeIn, pos, state);
    }

    public LevitateBlockTile(BlockPos blockPos, BlockState state) {
        super(ModBlocks.LEVITATION_BLOCK.get(), blockPos, state);
    }



    @Override
    public int getContainerSize() {
        return 1;
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putBoolean("hasSignal", hasSignal);
    }

    @Override
    public void load(CompoundTag compound) {
        super.load(compound);
        this.hasSignal = compound.getBoolean("hasSignal");
    }

}
