package sm.lavenderbiome.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import sm.lavenderbiome.world.ModPlacedFeatures;
import sm.lavenderbiome.world.biome.ModBiomes;

public class ModTreeGeneration {
    public static void generateTrees() {
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(ModBiomes.LAVENDER_BIOME),
                GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.LAVENDERWOOD_PLACED_KEY);
    }
}
