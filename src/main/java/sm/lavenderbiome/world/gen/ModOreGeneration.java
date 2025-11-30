package sm.lavenderbiome.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModification;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import sm.lavenderbiome.world.ModPlacedFeatures;

public class ModOreGeneration {

    public static void generateOres(){
        BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES,
                ModPlacedFeatures.LAVENDRITE_ORE_PLACED_KEY
        );

        //Biomes
        /*
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(BiomeKeys.DARK_FOREST, BiomeKeys.PLAINS), GenerationStep.Feature.UNDERGROUND_ORES,
                ModPlacedFeatures.LAVENDRITE_ORE_PLACED_KEY
        );
        */
    }
}
