package net.staruhhha.test1.block.tile;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.staruhhha.test1.block.ModTiles;
import org.openjdk.nashorn.internal.objects.annotations.Getter;
import org.openjdk.nashorn.internal.objects.annotations.Setter;

public class LevitationBlockTile extends TileBase{

    private ItemStack stack = ItemStack.EMPTY;
    public int ticksExisted;

    public ItemStack getStack() {
        return stack;
    }

    public void setStack(ItemStack stack) {
        this.stack = stack;
    }




    public LevitationBlockTile(BlockPos pos, BlockState state) {
        super(ModTiles.LEVITATION_BLOCK_TILE.get(), pos, state);
    }

    public LevitationBlockTile(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, LevitationBlockTile tile) {
        if (level == null)
            return;

        tile.ticksExisted++;
    }

    @Override
    public void load(CompoundTag compound) {
        super.load(compound);

        stack = ItemStack.of((CompoundTag) compound.get("stack"));
    }

    @Override
    protected void saveAdditional(CompoundTag compound) {
        super.saveAdditional(compound);

        if (stack != null) {
            CompoundTag itemStack = new CompoundTag();

            stack.save(itemStack);

            compound.put("stack", itemStack);
        }
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = new CompoundTag();

        this.saveAdditional(tag);

        return tag;
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        super.onDataPacket(net, pkt);

        this.load(pkt.getTag());
    }


}
