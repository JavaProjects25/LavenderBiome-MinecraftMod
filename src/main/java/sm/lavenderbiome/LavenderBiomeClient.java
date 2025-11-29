package sm.lavenderbiome;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.render.BlockRenderLayer;
import sm.lavenderbiome.block.ModBlocks;

public class LavenderBiomeClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.putBlock(ModBlocks.LAVENDER_CROP, BlockRenderLayer.CUTOUT);
    }
}
