package sm.lavenderbiome.world.biome;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.sound.MusicType;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import sm.lavenderbiome.LavenderBiome;
import sm.lavenderbiome.world.ModPlacedFeatures;

public class ModBiomes {
    public static final RegistryKey<Biome> LAVENDER_BIOME = RegistryKey.of(RegistryKeys.BIOME,
            Identifier.of(LavenderBiome.MOD_ID, "lavenderbiome"));

    //colors
    private static final int WATER_COLOR = 0x5DB7EF;
    private static final int WATER_FOG_COLOR = 0x264D67;

    private static final int SKY_COLOR = 0xE0D4FC;
    private static final int FOG_COLOR = 0xD2C2F7;
    private static final int GRASS_COLOR = 0xB69FDB;
    private static final int FOLIAGE_COLOR = 0x9B86C4;

    public static void bootstrap(Registerable<Biome> context) {
        context.register(LAVENDER_BIOME, createLavenderBiome(context));
    }

    public static Biome createLavenderBiome(Registerable<Biome> context) {
        return new Biome.Builder()
                .precipitation(true)
                .downfall(0.4f)
                .temperature(0.7f)
                .generationSettings(biomeGeneration(context))
                .spawnSettings(mobSpawns())
                .effects(biomeEffects())
                .build();
    }


    // 1. MOBS
    private static SpawnSettings mobSpawns() {
        SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();
        
        spawnBuilder.spawn(SpawnGroup.CREATURE, 4, new SpawnSettings.SpawnEntry(EntityType.RABBIT, 2, 4));
        spawnBuilder.spawn(SpawnGroup.CREATURE, 30, new SpawnSettings.SpawnEntry(EntityType.SHEEP, 4, 12));
        spawnBuilder.spawn(SpawnGroup.CREATURE, 8, new SpawnSettings.SpawnEntry(EntityType.PIG, 4, 4));
        spawnBuilder.spawn(SpawnGroup.CREATURE, 10, new SpawnSettings.SpawnEntry(EntityType.CHICKEN, 4, 8));
        spawnBuilder.spawn(SpawnGroup.CREATURE, 4, new SpawnSettings.SpawnEntry(EntityType.GOAT, 4, 8));

        DefaultBiomeFeatures.addBatsAndMonsters(spawnBuilder);

        return spawnBuilder.build();
    }

    // 2. WORLD GEN
    private static GenerationSettings biomeGeneration(Registerable<Biome> context) {
        GenerationSettings.LookupBackedBuilder biomeBuilder =
                new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE),
                        context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        // Global Settings
        DefaultBiomeFeatures.addLandCarvers(biomeBuilder);
        DefaultBiomeFeatures.addAmethystGeodes(biomeBuilder);
        DefaultBiomeFeatures.addDungeons(biomeBuilder);
        DefaultBiomeFeatures.addMineables(biomeBuilder);
        DefaultBiomeFeatures.addSprings(biomeBuilder);
        DefaultBiomeFeatures.addFrozenTopLayer(biomeBuilder);
        DefaultBiomeFeatures.addBushes(biomeBuilder);

        // Ores
        DefaultBiomeFeatures.addDefaultOres(biomeBuilder);
        biomeBuilder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.LAVENDRITE_ORE_PLACED_KEY);

        // Vegetation
        biomeBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.LAVENDERWOOD_PLACED_KEY);
        biomeBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.LAVENDER_PLACED_KEY);

        // Standard decoration (Grass, mushrooms)
        DefaultBiomeFeatures.addDefaultMushrooms(biomeBuilder);
        DefaultBiomeFeatures.addDefaultVegetation(biomeBuilder, false);

        return biomeBuilder.build();
    }

    // 3. VISUALS & SOUNDS
    private static BiomeEffects biomeEffects() {
        return new BiomeEffects.Builder()
                .waterColor(WATER_COLOR)
                .waterFogColor(WATER_FOG_COLOR)
                .skyColor(SKY_COLOR)
                .grassColor(GRASS_COLOR)
                .foliageColor(FOLIAGE_COLOR)
                .fogColor(FOG_COLOR)
                .moodSound(BiomeMoodSound.CAVE)
                .music(MusicType.createIngameMusic(SoundEvents.MUSIC_OVERWORLD_CHERRY_GROVE))
                .build();
    }
}