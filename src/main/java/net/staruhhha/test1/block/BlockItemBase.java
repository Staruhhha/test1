package net.staruhhha.test1.block;

import com.google.common.collect.Lists;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.staruhhha.test1.items.ICreativeTabEntry;

import java.util.List;

public class BlockItemBase extends BlockItem implements ICreativeTabEntry {
    public BlockItemBase(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public List<ItemStack> processCreativeTab() {
        return Lists.newArrayList(this.getDefaultInstance());
    }
}