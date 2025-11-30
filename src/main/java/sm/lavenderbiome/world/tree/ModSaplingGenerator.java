package sm.lavenderbiome.world.tree;

import net.minecraft.block.SaplingGenerator;
import sm.lavenderbiome.LavenderBiome;
import sm.lavenderbiome.world.ModConfiguredFeatures;

import java.util.Optional;

public class ModSaplingGenerator {
    public static final SaplingGenerator LAVENDERWOOD = new SaplingGenerator(LavenderBiome.MOD_ID + ":lavenderwood",
            Optional.empty(), Optional.of(ModConfiguredFeatures.LAVENDERWOOD_KEY), Optional.empty());
}
