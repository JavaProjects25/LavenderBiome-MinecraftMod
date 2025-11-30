package sm.lavenderbiome.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import sm.lavenderbiome.world.ModPlacedFeatures;

public class ModFlowersGeneration {

    public static void generateFlowers() {
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.PLAINS, BiomeKeys.BIRCH_FOREST, BiomeKeys.MEADOW),
                GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.LAVENDER_PLACED_KEY);
    }
}
