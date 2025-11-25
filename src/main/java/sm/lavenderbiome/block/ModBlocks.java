package sm.lavenderbiome.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import sm.lavenderbiome.LavenderBiome;

import java.util.function.Function;

public class ModBlocks {

    // Blocks
    public static final Block LAVENDRITE_BLOCK = registerBlock("lavendrite_block",
            AbstractBlock.Settings.create().mapColor(MapColor.PURPLE).strength(5F, 6F).sounds(BlockSoundGroup.AMETHYST_BLOCK).requiresTool());
    public static final Block RAW_LAVENDRITE_BLOCK = registerBlock("raw_lavendrite_block",
            AbstractBlock.Settings.create().mapColor(MapColor.PURPLE).strength(5F, 6F).sounds(BlockSoundGroup.STONE).requiresTool());
    public static final Block LAVENDRITE_ORE = registerFactoryBlock("lavendrite_ore",
            (settings -> new ExperienceDroppingBlock(UniformIntProvider.create(2, 5), settings)),
            AbstractBlock.Settings.create().mapColor(MapColor.PURPLE).strength(3F, 3F).sounds(BlockSoundGroup.STONE).requiresTool());
    public static final Block DEEPSLATE_LAVENDRITE_ORE = registerFactoryBlock("deepslate_lavendrite_ore",
            (settings -> new ExperienceDroppingBlock(UniformIntProvider.create(3, 7), settings)),
            AbstractBlock.Settings.create().mapColor(MapColor.PURPLE).strength(4.5F, 6F).sounds(BlockSoundGroup.DEEPSLATE).requiresTool());



    // Factory design method to create blocks with custom settings.
    private static Block registerFactoryBlock(String name, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings) {
        RegistryKey<Block> key = RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(LavenderBiome.MOD_ID, name));
        Block block = factory.apply(settings.registryKey(key));
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, key, block);
    }

    // Register a new block instance with a specified name and settings.
    private static Block registerBlock(String name, AbstractBlock.Settings blockSettings){
        RegistryKey<Block> key = RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(LavenderBiome.MOD_ID, name));
        Block block = new Block(blockSettings.registryKey(key));
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, key, block);
    }

    // Register the BlockItem for the newly registered Block instance (the item form of the block).
    private static void registerBlockItem(String name, Block block) {
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(LavenderBiome.MOD_ID, name));
        BlockItem item = new BlockItem(block, new Item.Settings().registryKey(key));
        Registry.register(Registries.ITEM, key, item);

        //Registry.register(Registries.ITEM, Identifier.of(LavenderBiome.MOD_ID, name),
        //      neew BlockItm(block, new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(LavenderBiome.MOD_ID, name)))));
    }

    // Method to register all mod blocks.
    public static void registerModBlocks() {
        LavenderBiome.LOGGER.info("Registering Mod Blocks for: " + LavenderBiome.MOD_ID);

        // Add the new block to the Building Blocks item group in the Creative Mode inventory
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.add(ModBlocks.LAVENDRITE_BLOCK);
            entries.add(ModBlocks.RAW_LAVENDRITE_BLOCK);
            entries.add(ModBlocks.LAVENDRITE_ORE);
            entries.add(ModBlocks.DEEPSLATE_LAVENDRITE_ORE);
        });
    }
}
