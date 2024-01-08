package net.staruhhha.test1.block;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.staruhhha.test1.Test1Mod;
import net.staruhhha.test1.block.tile.LevitationBlockTile;

public class ModTiles {
    private static final DeferredRegister<BlockEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Test1Mod.MOD_ID);

    public static final RegistryObject<BlockEntityType<LevitationBlockTile>> LEVITATION_BLOCK_TILE = TILES.register("levitation_block", () ->
            BlockEntityType.Builder.of(LevitationBlockTile::new, ModBlocks.LEVITATION_BLOCK.get()).build(null));

    public static void register() {
        TILES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
