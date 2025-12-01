package sm.lavenderbiome.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.gen.GenerationStep;
import sm.lavenderbiome.LavenderBiome;
import sm.lavenderbiome.world.ModPlacedFeatures;

public class ModOreGeneration {

    public static void generateOres() {

        /*
        var overworldWithoutLavender = BiomeSelectors.foundInOverworld()
                .and(context -> !context.getBiomeKey().getValue().getNamespace().equals(LavenderBiome.MOD_ID));

        BiomeModifications.addFeature(
                overworldWithoutLavender,
                GenerationStep.Feature.UNDERGROUND_ORES,
                ModPlacedFeatures.LAVENDRITE_ORE_PLACED_KEY
        );
        */

        BiomeModifications.addFeature(

                BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES,

                ModPlacedFeatures.LAVENDRITE_ORE_PLACED_KEY
        );
    }
}