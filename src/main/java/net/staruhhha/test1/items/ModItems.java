package net.staruhhha.test1.items;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.staruhhha.test1.Test1Mod;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Test1Mod.MOD_ID);



    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

}
