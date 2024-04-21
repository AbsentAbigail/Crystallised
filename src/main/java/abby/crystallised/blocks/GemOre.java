package abby.crystallised.blocks;

import abby.crystallised.gems.GemType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldAccess;

import java.util.Random;

public class GemOre extends Block {
    private final static BooleanProperty WAS_PLACED;

    static {
        WAS_PLACED = BooleanProperty.of("was_placed");
    }

    private GemType type;

    protected static final Random RANDOM = new Random();

    public GemOre(GemType type) {
        super(getSettingsFromType(type));
        this.type = type;
        this.setDefaultState(this.stateManager.getDefaultState().with(WAS_PLACED, false));
    }

    public static Settings getSettingsFromType(GemType type) {

        Settings settings = Settings.copy(Blocks.STONE);

        double h = type.getHardness();

//        int level = h >= 9 ? 3 :
//                (h > 5 ? 2 :
//                        (h > 1 ? 1 :
//                                0));

        // settings.strength(h * 0.5F, h * 0.6F).requiresTool();

        return settings;
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(WAS_PLACED);
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(WAS_PLACED, true);
    }

    public GemType getGemType() {
        return type;
    }

    @Override
    public void onBroken(WorldAccess world, BlockPos pos, BlockState state) {
        super.onBroken(world, pos, state);

        if(!((Boolean)state.get(WAS_PLACED)) && RANDOM.nextInt(50) == 0) {
            world.setBlockState(pos, ModBlocks.blockMap.get(type.getName() + "_egg").getDefaultState(), Block.NOTIFY_NEIGHBORS);
            BlockState blockState = world.getBlockState(pos);
            if (!blockState.isAir()) {
                for(int i = 0; i < 15; ++i) {
                    double d = RANDOM.nextGaussian() * 0.02D;
                    double e = RANDOM.nextGaussian() * 0.02D;
                    double f = RANDOM.nextGaussian() * 0.02D;
                    world.addParticle(ParticleTypes.HAPPY_VILLAGER, (double)((float)pos.getX() + RANDOM.nextFloat()), (double)pos.getY() + (double)RANDOM.nextFloat() * blockState.getOutlineShape(world, pos).getMax(Direction.Axis.Y), (double)((float)pos.getZ() + RANDOM.nextFloat()), d, e, f);
                }
            }
        }
    }
}
