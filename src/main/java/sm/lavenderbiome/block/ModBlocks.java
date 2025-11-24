package sm.lavenderbiome.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.AmethystBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import sm.lavenderbiome.LavenderBiome;

public class ModBlocks {

    // Register a new Block called "lavendrite_block", which is an instance of the Block class with specific settings,
    // not requires tool to mine it, and sounds like amethyst block., it has a strength of 4f so its kind of soft
    public static final Block LAVENDRITE_BLOCK = registerBlock("lavendrite_block",
            AbstractBlock.Settings.create().mapColor(MapColor.PURPLE).strength(1.5F).sounds(BlockSoundGroup.AMETHYST_BLOCK).requiresTool());

    //raw ore block
    public static final Block RAW_LAVENDRITE_BLOCK = registerBlock("raw_lavendrite_block",
            AbstractBlock.Settings.create().mapColor(MapColor.PURPLE).strength(0.8F).sounds(BlockSoundGroup.STONE).requiresTool());

    public static final Block LAVENDRITE_ORE = registerBlock("lavendrite_ore",
            AbstractBlock.Settings.create().mapColor(MapColor.PURPLE).strength(1F).sounds(BlockSoundGroup.STONE).requiresTool());



    private static Block registerBlock(String name, AbstractBlock.Settings blockSettings){
        RegistryKey<Block> key = RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(LavenderBiome.MOD_ID, name));
        Block block = new Block(blockSettings.registryKey(key));
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, key, block);
    }

    private static void registerBlockItem(String name, Block block) {

        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(LavenderBiome.MOD_ID, name));
        BlockItem item = new BlockItem(block, new Item.Settings().registryKey(key));
        Registry.register(Registries.ITEM, key, item);

        //Registry.register(Registries.ITEM, Identifier.of(LavenderBiome.MOD_ID, name),
        //      neew BlockItm(block, new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(LavenderBiome.MOD_ID, name)))));
    }

    public static void registerModBlocks() {
        LavenderBiome.LOGGER.info("Registering Mod Blocks for: " + LavenderBiome.MOD_ID);

        // Add the new block to the Building Blocks item group in the Creative Mode inventory
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.add(ModBlocks.LAVENDRITE_BLOCK);
            entries.add(ModBlocks.RAW_LAVENDRITE_BLOCK);
            entries.add(ModBlocks.LAVENDRITE_ORE);
        });
    }
}
