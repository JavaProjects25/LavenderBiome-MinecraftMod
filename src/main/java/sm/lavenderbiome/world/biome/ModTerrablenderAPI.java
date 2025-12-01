package sm.lavenderbiome.world.biome;

import net.minecraft.util.Identifier;
import sm.lavenderbiome.LavenderBiome;
import sm.lavenderbiome.world.biome.surface.ModMaterialRules;
import terrablender.api.RegionType;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;
import terrablender.api.TerraBlenderApi;

public class ModTerrablenderAPI implements TerraBlenderApi {
    @Override
    public void onTerraBlenderInitialized() {
        Regions.register(new ModOverworldRegion(Identifier.of(LavenderBiome.MOD_ID, "overworld"), RegionType.OVERWORLD, 4));

        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, LavenderBiome.MOD_ID, ModMaterialRules.makeRules());
    }
}
