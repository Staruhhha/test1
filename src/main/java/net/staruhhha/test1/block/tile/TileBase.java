package net.staruhhha.test1.block.tile;

import net.minecraft.core.BlockPos;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class TileBase extends BlockEntity {

    public TileBase(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        if (pkt.getTag() != null)
            load(pkt.getTag());
    }

    public double getX() {
        return this.getBlockPos().getX() + 0.5D;
    }

    public double getY() {
        return this.getBlockPos().getY() + 1D ;
    }

    public double getZ() {
        return this.getBlockPos().getZ() + 0.5D;
    }

}
