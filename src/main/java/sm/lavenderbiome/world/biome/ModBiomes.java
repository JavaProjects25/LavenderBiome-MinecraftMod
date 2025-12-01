package sm.lavenderbiome.world.biome;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.sound.MusicSound;
import net.minecraft.sound.MusicType;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import sm.lavenderbiome.LavenderBiome;
import sm.lavenderbiome.world.ModPlacedFeatures;

public class ModBiomes {
    public static final RegistryKey<Biome> LAVENDER_BIOME = RegistryKey.of(RegistryKeys.BIOME,
            Identifier.of(LavenderBiome.MOD_ID, "lavender_biome"));

    public static void bootstrap(Registerable<Biome> context) {
        context.register(LAVENDER_BIOME, lavenderBiome(context));
    }

    public static void globalOverworldGeneration(GenerationSettings.LookupBackedBuilder builder) {
        DefaultBiomeFeatures.addLandCarvers(builder);
        DefaultBiomeFeatures.addAmethystGeodes(builder);
        DefaultBiomeFeatures.addDungeons(builder);
        DefaultBiomeFeatures.addMineables(builder);
        DefaultBiomeFeatures.addSprings(builder);
        DefaultBiomeFeatures.addFrozenTopLayer(builder);
    }

    public static Biome lavenderBiome(Registerable<Biome> context) {
        SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();

        // Mobs
        DefaultBiomeFeatures.addFarmAnimals(spawnBuilder);
        DefaultBiomeFeatures.addBatsAndMonsters(spawnBuilder);
        spawnBuilder.spawn(SpawnGroup.AXOLOTLS, 10, new SpawnSettings.SpawnEntry(EntityType.AXOLOTL, 4, 6));

        GenerationSettings.LookupBackedBuilder biomeBuilder =
                new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE),
                        context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        // 1. Global Stuff (Caves, etc.)
        globalOverworldGeneration(biomeBuilder);
        DefaultBiomeFeatures.addMossyRocks(biomeBuilder);

        // 2. Ores
        DefaultBiomeFeatures.addDefaultOres(biomeBuilder);
        DefaultBiomeFeatures.addExtraGoldOre(biomeBuilder);
        biomeBuilder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.LAVENDRITE_ORE_PLACED_KEY);

        // 3. Vegetation (Trees & Flowers)
        biomeBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.LAVENDERWOOD_PLACED_KEY);

        biomeBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.LAVENDER_PLACED_KEY);

        DefaultBiomeFeatures.addForestFlowers(biomeBuilder);
        DefaultBiomeFeatures.addLargeFerns(biomeBuilder);
        DefaultBiomeFeatures.addDefaultMushrooms(biomeBuilder);
        DefaultBiomeFeatures.addDefaultVegetation(biomeBuilder, false);

        MusicSound musicSound = MusicType.createIngameMusic(SoundEvents.MUSIC_OVERWORLD_GROVE);

        return new Biome.Builder()
                .precipitation(true)
                .downfall(0.4f)
                .temperature(0.7f)
                .generationSettings(biomeBuilder.build())
                .spawnSettings(spawnBuilder.build())
                .effects((new BiomeEffects.Builder())
                        .waterColor(0xe82e3b)
                        .waterFogColor(0xbf1b26)
                        .skyColor(0x30c918)
                        .grassColor(0xdfc5fe)
                        .foliageColor(0x967bb6)
                        .fogColor(0x22a1e6)
                        .moodSound(BiomeMoodSound.CAVE)
                        .music(musicSound)
                        .build())
                .build();
    }
}