package sm.lavenderbiome.world;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.*;
import sm.lavenderbiome.LavenderBiome;
import sm.lavenderbiome.block.ModBlocks;

import java.util.List;

public class ModPlacedFeatures {

    public static final RegistryKey<PlacedFeature> LAVENDRITE_ORE_PLACED_KEY = registryKey("lavendrite_ore_placed");

    public static final RegistryKey<PlacedFeature> LAVENDERWOOD_PLACED_KEY = registryKey("lavenderwood_placed");
    
    public static final RegistryKey<PlacedFeature> LAVENDER_PLACED_KEY = registryKey("lavender_placed");

    public static void bootstrap(Registerable<PlacedFeature> context) {
        //tying it to the configured feature
        var configuredFeatures = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        register(context, LAVENDRITE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.LAVENDRITE_ORE_KEY),
                ModOrePlacement.modifiersWithCount(14,
                        //Y levels to find the ore
                        HeightRangePlacementModifier.uniform(YOffset.fixed(-80), YOffset.fixed(80))));

        register(context, LAVENDERWOOD_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.LAVENDERWOOD_KEY),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(
                        PlacedFeatures.createCountExtraModifier(2, 0.1f, 2), ModBlocks.LAVENDERWOOD_SAPLING));


        register(
                context,
                LAVENDER_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.LAVENDER_KEY),
                List.of(
                        CountPlacementModifier.of(3), // Try this many times per chunk
                        SquarePlacementModifier.of(),
                        PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
                        BiomePlacementModifier.of()
                ));

    }

    public static RegistryKey<PlacedFeature> registryKey(String name){
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(LavenderBiome.MOD_ID, name));
    }

    private static void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }


    //bush
    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key,
                                                                                   RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                                                                   PlacementModifier... modifiers) {
        register(context, key, configuration, List.of(modifiers));
    }
}
