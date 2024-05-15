package abby.crystallised.blocks;

import abby.crystallised.gems.GemType;
import net.minecraft.block.*;
import net.minecraft.item.Items;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class GemCore extends Block {
    public static final IntProperty RECOVER;

    private final GemType type;

    public GemCore(GemType type) {
        super(getSettings1());
        this.setDefaultState(
                this.stateManager.getDefaultState()
                        .with(RECOVER, 0)
        );
        this.type = type;
    }

    public static AbstractBlock.Settings getSettings1() {
        Settings settings = Settings.copy(Blocks.IRON_BLOCK);
        settings.strength(3.0F, 3.5F).requiresTool();
        return settings;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        boolean boat = context.isHolding(Items.ACACIA_BOAT);
        if (boat)
            return VoxelShapes.fullCube();

        boolean empty = getGemType().equals(GemType.MAP.get("ONYX")) && state.get(RECOVER) == 2;
        return empty ? VoxelShapes.empty() : VoxelShapes.fullCube();
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(RECOVER);
    }

    static {
        RECOVER = IntProperty.of("recover", 0, 2);
    }

    public GemType getGemType() {
        return this.type;
    }
}
