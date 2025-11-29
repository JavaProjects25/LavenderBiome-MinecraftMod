package sm.lavenderbiome.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.item.ItemConvertible;
import net.minecraft.server.command.PublishCommand;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import sm.lavenderbiome.item.ModItems;

public class LavenderCropBlock extends CropBlock {

    public static final int MAX_AGE = 3;

    public static final IntProperty AGE = IntProperty.of("age", 0, MAX_AGE);
    public LavenderCropBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    protected IntProperty getAgeProperty() {
        return AGE;
    }

    @Override
    protected ItemConvertible getSeedsItem() {
        return ModItems.LAVENDER_SEEDS;
    }
}
