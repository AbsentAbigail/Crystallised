package abby.crystallised.blocks;

import abby.crystallised.blocks.blockentities.GemCutterEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class GemCutter extends Block implements BlockEntityProvider {
    public static final String NAME = "gem_cutter";
    public GemCutter() {
        super(Settings.copy(Blocks.STONECUTTER).nonOpaque());
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new GemCutterEntity(pos, state);
    }
}
