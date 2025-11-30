package sm.lavenderbiome.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class LavenderLeavesBlock extends LeavesBlock {
    // 1. Define the MapCodec. This tells the game how to save/load the block.
    // 'createCodec' is a helper method that works if the constructor matches the signature (Settings).
    public static final MapCodec<LavenderLeavesBlock> CODEC = createCodec(LavenderLeavesBlock::new);

    public LavenderLeavesBlock(AbstractBlock.Settings settings) {
        // 2. Pass the particle chance (0.5f) to the super constructor here.
        super(0.5f, settings);
    }

    @Override
    public MapCodec<LavenderLeavesBlock> getCodec() {
        return CODEC;
    }

    @Override
    protected void spawnLeafParticle(World world, BlockPos pos, Random random) {
        // 3. Define what particles fall from the leaves.
        // For now, we use Cherry Leaves particles as a placeholder.
        // We can change 'ParticleTypes.CHERRY_LEAVES' to our own custom particle later.

        // Spawning a particle at the bottom of the leaf block
        double x = pos.getX() + random.nextDouble();
        double y = pos.getY() - 0.1;
        double z = pos.getZ() + random.nextDouble();

        // This makes the leaves drip/fall nicely
        world.addParticleClient(ParticleTypes.CHERRY_LEAVES, x, y, z, 0.0, 0.0, 0.0);
    }
}