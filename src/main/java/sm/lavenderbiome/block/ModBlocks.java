package sm.lavenderbiome.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.effect.StatusEffects;
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
import sm.lavenderbiome.block.custom.LavenderCropBlock;
import sm.lavenderbiome.block.custom.LavenderLeavesBlock;
import sm.lavenderbiome.world.tree.ModSaplingGenerator;

import java.util.function.Function;

public class ModBlocks {

    // -- BLOCKS --
        // Mineral Blocks
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
        // Wood Blocks
    public static final Block LAVENDERWOOD_LOG = registerFactoryBlock("lavenderwood_log",
            PillarBlock::new,
            AbstractBlock.Settings.copy(Blocks.OAK_LOG).mapColor(MapColor.MAGENTA));
    public static final Block LAVENDERWOOD_WOOD = registerFactoryBlock("lavenderwood_wood",
            PillarBlock::new,
            AbstractBlock.Settings.copy(Blocks.OAK_WOOD).mapColor(MapColor.MAGENTA));
    public static final Block STRIPPED_LAVENDERWOOD_LOG = registerFactoryBlock("stripped_lavenderwood_log",
            PillarBlock::new,
            AbstractBlock.Settings.copy(Blocks.STRIPPED_OAK_LOG).mapColor(MapColor.TERRACOTTA_MAGENTA));
    public static final Block STRIPPED_LAVENDERWOOD_WOOD = registerFactoryBlock("stripped_lavenderwood_wood",
            PillarBlock::new,
            AbstractBlock.Settings.copy(Blocks.STRIPPED_OAK_WOOD).mapColor(MapColor.TERRACOTTA_MAGENTA));
    public static final Block LAVENDERWOOD_PLANKS = registerBlock("lavenderwood_planks",
            AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).mapColor(MapColor.MAGENTA));
    public static final Block LAVENDERWOOD_LEAVES = registerFactoryBlock("lavenderwood_leaves",
            settings -> new LavenderLeavesBlock(settings),
            AbstractBlock.Settings.copy(Blocks.OAK_LEAVES).mapColor(MapColor.MAGENTA));
    public static final Block LAVENDERWOOD_SAPLING = registerFactoryBlock("lavenderwood_sapling",
            settings -> new SaplingBlock(ModSaplingGenerator.LAVENDERWOOD, settings),
            AbstractBlock.Settings.copy(Blocks.OAK_SAPLING).mapColor(MapColor.MAGENTA));

    // -- PLANTS --
    public static final Block LAVENDER_CROP = registerBlockWithoutBlockItem("lavender_crop",
            properties -> new LavenderCropBlock(properties.noCollision()
                    .ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP)
                    .pistonBehavior(PistonBehavior.DESTROY)));
    public static final Block LAVENDER = registerFactoryBlock("lavender",
            settings -> new FlowerBlock(StatusEffects.HASTE, 3.0F, settings),
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.PURPLE)
                    .noCollision()
                    .breakInstantly()
                    .sounds(BlockSoundGroup.AZALEA_LEAVES)
                    .offset(AbstractBlock.OffsetType.XZ)
                    .pistonBehavior(PistonBehavior.DESTROY)
    );

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

    /*
    private static Block registerBlockWithoutBlockItem(String name, Block block){
        return Registry.register(Registries.BLOCK, Identifier.of(LavenderBiome.MOD_ID, name), block);
    } */

    private static Block registerBlockWithoutBlockItem(String name, Function<AbstractBlock.Settings, Block> function) {
        return Registry.register(Registries.BLOCK, Identifier.of(LavenderBiome.MOD_ID, name),
                function.apply(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(LavenderBiome.MOD_ID, name)))));
    }

    // Register the BlockItem for the newly registered Block instance (the item form of the block).
    private static void registerBlockItem(String name, Block block) {
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(LavenderBiome.MOD_ID, name));
        BlockItem item = new BlockItem(block, new Item.Settings().registryKey(key));
        Registry.register(Registries.ITEM, key, item);

        //Registry.register(Registries.ITEM, Identifier.of(LavenderBiome.MOD_ID, name),
        //      neew BlockItm(block, new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(LavenderBiome.MOD_ID, name)))));
    }

    // Method to register all mod blocks for their creative mode categories
    public static void registerModBlocks() {
        LavenderBiome.LOGGER.info("Registering Mod Blocks for: " + LavenderBiome.MOD_ID);

        // BUILDING BLOCKS item group
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            // Mineral Blocks
            entries.add(ModBlocks.LAVENDRITE_BLOCK);
            entries.add(ModBlocks.RAW_LAVENDRITE_BLOCK);
            entries.add(ModBlocks.LAVENDRITE_ORE);
            entries.add(ModBlocks.DEEPSLATE_LAVENDRITE_ORE);

            // Wood Blocks
            entries.add(ModBlocks.LAVENDERWOOD_LOG);
            entries.add(ModBlocks.LAVENDERWOOD_WOOD);
            entries.add(ModBlocks.STRIPPED_LAVENDERWOOD_LOG);
            entries.add(ModBlocks.STRIPPED_LAVENDERWOOD_WOOD);
            entries.add(ModBlocks.LAVENDERWOOD_PLANKS);
        });

        // NATURAL item group
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries ->{
            entries.add(ModBlocks.LAVENDER);
            entries.add(ModBlocks.LAVENDERWOOD_LEAVES);
            entries.add(ModBlocks.LAVENDERWOOD_SAPLING);
        });

        // FOOD AND DRINK item group
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(entries ->{
            entries.add(ModBlocks.LAVENDER_CROP);
        });
    }
}
