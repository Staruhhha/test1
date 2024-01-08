package net.staruhhha.test1.items;

import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public interface ICreativeTabEntry {
    default List<ItemStack> processCreativeTab() {
        return new ArrayList<>();
    }
}
