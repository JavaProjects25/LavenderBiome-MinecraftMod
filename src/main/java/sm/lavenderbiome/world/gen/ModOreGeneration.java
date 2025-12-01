package sm.lavenderbiome.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.gen.GenerationStep;
import sm.lavenderbiome.LavenderBiome;
import sm.lavenderbiome.world.ModPlacedFeatures;

public class ModOreGeneration {

    public static void generateOres() {
        // We create a "Selector" that finds all Overworld biomes...
        // ...BUT specifically EXCLUDES any biome that belongs to "lavenderbiome"
        var overworldWithoutLavender = BiomeSelectors.foundInOverworld()
                .and(context -> !context.getBiomeKey().getValue().getNamespace().equals(LavenderBiome.MOD_ID));

        BiomeModifications.addFeature(
                overworldWithoutLavender,
                GenerationStep.Feature.UNDERGROUND_ORES,
                ModPlacedFeatures.LAVENDRITE_ORE_PLACED_KEY
        );
    }
}