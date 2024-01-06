package net.staruhhha.test1.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.staruhhha.test1.Test1Mod;
import net.staruhhha.test1.block.custom.tile.LevitateBlockTile;
import net.staruhhha.test1.items.ModItems;




import java.util.function.Supplier;

public class ModBlocks  {


   /* public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Test1Mod.MOD_ID);*/

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Test1Mod.MOD_ID);

    public static RegistryObject<BlockEntityType<LevitateBlockTile>> LEVITATION_BLOCK;
    static {
        LEVITATION_BLOCK = BLOCK_ENTITIES.register("levitation_block",
                () -> BlockEntityType.Builder.of(LevitateBlockTile::new, LEVITATION_BLOCK.get()).build(null));
    }

    /* private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registrBlockItem(name, toReturn, tab);
        return toReturn;
    }


    private static <T extends Block> RegistryObject<Item> registrBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab){
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }*/

    public static void register(IEventBus eventBus){
        BLOCK_ENTITIES.register(eventBus);
    }

}
