package sm.lavenderbiome.world;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.*;
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

    public static final RegistryKey<ConfiguredFeature<?,?>> LAVENDRITE_ORE_KEY = registerKey("lavendrite_ore");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        // Defining rules for generation so things arent our of place
        RuleTest stoneReplaceables = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);


        //the ores that can go in normal will be replaced by lavendrite ore and the ores that go in deepslate will be replaced by deepslate lavendrite ore
        // (Lavendrite Ores get added to the list of all ores in Minecraft that can spawn in stone and deepslate)
        List<OreFeatureConfig.Target> overworldLavendriteOres = List.of(
                OreFeatureConfig.createTarget(stoneReplaceables, ModBlocks.LAVENDRITE_ORE.getDefaultState()),
                OreFeatureConfig.createTarget(deepslateReplaceables, ModBlocks.DEEPSLATE_LAVENDRITE_ORE.getDefaultState())
        );

        register(context, LAVENDRITE_ORE_KEY, Feature.ORE, new OreFeatureConfig(overworldLavendriteOres, 12));

    }


    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(LavenderBiome.MOD_ID, name));
    }


    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }


}
