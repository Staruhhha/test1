package net.staruhhha.test1.items;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.staruhhha.test1.block.ModBlocks;

public class ModCreativeModeTab {
    public static final CreativeModeTab TEST_TAB = new CreativeModeTab("test1tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModBlocks.LEVITATION_BLOCK.get());
        }
    };
}
