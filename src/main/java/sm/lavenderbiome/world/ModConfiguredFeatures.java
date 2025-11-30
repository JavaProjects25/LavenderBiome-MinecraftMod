package sm.lavenderbiome.world;

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.foliage.BushFoliagePlacer;
import net.minecraft.world.gen.foliage.PineFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;
import net.minecraft.world.gen.trunk.UpwardsBranchingTrunkPlacer;
import sm.lavenderbiome.LavenderBiome;
import sm.lavenderbiome.block.ModBlocks;

import java.util.List;

public class ModConfiguredFeatures {
    // Configured Features -> Placed Features -> Biome Modifications
    // (How does it look like) -> (How is it going to be placed) -> (Where is it going to be placed)

    /*
        registerKey(name) makes a unique name tag for a thing using your mod id so the game can find it later.

        register(context, key, feature, configuration) actually puts the thing into the game registry:
            context = the place where things are registered,
            key = the name tag,
            feature = the kind of thing (like a flower type),
            configuration = how that thing should look or behave.
    */

    public static final RegistryKey<ConfiguredFeature<?, ?>> LAVENDRITE_ORE_KEY = registerKey("lavendrite_ore");

    public static final RegistryKey<ConfiguredFeature<?, ?>> LAVENDERWOOD_KEY = registerKey("lavenderwood");

    public static final RegistryKey<ConfiguredFeature<?, ?>> LAVENDER_KEY = registerKey("lavender");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        // Defining rules for generation so things aren't out of place
        RuleTest stoneReplaceables = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        // We need this lookup to tell the branching tree what blocks it can poke through (leaves/vines)
        RegistryEntryLookup<Block> blockRegistry = context.getRegistryLookup(RegistryKeys.BLOCK);

        //the ores that can go in normal will be replaced by lavendrite ore and the ores that go in deepslate will be replaced by deepslate lavendrite ore
        // (Lavendrite Ores get added to the list of all ores in Minecraft that can spawn in stone and deepslate)
        List<OreFeatureConfig.Target> overworldLavendriteOres = List.of(
                OreFeatureConfig.createTarget(stoneReplaceables, ModBlocks.LAVENDRITE_ORE.getDefaultState()),
                OreFeatureConfig.createTarget(deepslateReplaceables, ModBlocks.DEEPSLATE_LAVENDRITE_ORE.getDefaultState())
        );

        register(context, LAVENDRITE_ORE_KEY, Feature.ORE, new OreFeatureConfig(overworldLavendriteOres, 12));

        register(context, LAVENDERWOOD_KEY, Feature.TREE, new TreeFeatureConfig.Builder(
                        BlockStateProvider.of(ModBlocks.LAVENDERWOOD_LOG), // Check TrunkPlacerType Class for more
                        new StraightTrunkPlacer(5, 6, 3),
                        //new UpwardsBranchingTrunkPlacer(4, 6, 3,
                                //ConstantIntProvider.create(3), 0.4F, ConstantIntProvider.create(2),
                                //blockRegistry.getOrThrow(BlockTags.MANGROVE_LOGS_CAN_GROW_THROUGH)),

                BlockStateProvider.of(ModBlocks.LAVENDERWOOD_LEAVES), // Check FoliagePlacerType Class for more
                //new BlobFoliagePlacer(ConstantIntProvider.create(4), ConstantIntProvider.create(1), 3),
                new PineFoliagePlacer(ConstantIntProvider.create(3), ConstantIntProvider.create(1), ConstantIntProvider.create(6)),

                new TwoLayersFeatureSize(1, 0, 2)).build());


        /*
        register(context, LAVENDER_FLOWER_KEY,
                Feature.SIMPLE_BLOCK,
                new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.LAVENDER)));

         */

        register(context, LAVENDER_KEY, Feature.RANDOM_PATCH, new RandomPatchFeatureConfig(
                48, // Tries: High number = dense patch, Low number = sparse
                12,  // XZ Spread
                2,  // Y Spread
                PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                        new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.LAVENDER)))
        ));

    }


    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(LavenderBiome.MOD_ID, name));
    }


    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }


}
