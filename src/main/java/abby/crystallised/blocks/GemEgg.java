package abby.crystallised.blocks;

import abby.crystallised.Utility;
import abby.crystallised.gems.GemType;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class GemEgg extends Block {
    public static final IntProperty HATCH;

    public static final VoxelShape BOTTOM = Block.createCuboidShape(4, 0, 4, 12, 6, 12);
    public static final VoxelShape TOP = Block.createCuboidShape(5, 6, 5, 11, 11, 11);

    private GemType type;

    public GemEgg(GemType type) {
        super(getSettings1());
        this.setDefaultState((this.stateManager.getDefaultState()));
        this.type = type;
    }

    public static AbstractBlock.Settings getSettings1() {
        Settings settings = Settings.copy(Blocks.DRAGON_EGG).ticksRandomly();
        //settings.breakByHand(false);
        settings.strength(3.0F, 3.5F).requiresTool(); // .breakByTool(FabricToolTags.PICKAXES, 2);
        return settings;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        return VoxelShapes.union(BOTTOM, TOP);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, net.minecraft.util.math.random.Random random) {
        if (this.shouldHatchProgress(world) && this.isOnGem(world, pos)) {
            int i = state.get(HATCH);
            if (i < 2) {
                world.playSound((PlayerEntity) null, pos, SoundEvents.ENTITY_TURTLE_EGG_CRACK, SoundCategory.BLOCKS, 0.7F, 0.9F + random.nextFloat() * 0.2F);
                world.setBlockState(pos, (BlockState) state.with(HATCH, i + 1), 2);
                Utility.LOGGER.warn("hatch: " + HATCH);
                return;
            }
//                BabyDragonEntity dragonEntity = (BabyDragonEntity)ModEntities.entityMap.get("dragon_" + this.type.getName()).create(world);
//                DragonEggBlockEntity be = (DragonEggBlockEntity)world.getBlockEntity(pos);
//                UUID uuid = be.getPlacer();
//                dragonEntity.setOwnerUuid(uuid);
//
//                world.playSound((PlayerEntity)null, pos, SoundEvents.ENTITY_TURTLE_EGG_HATCH, SoundCategory.BLOCKS, 0.7F, 0.9F + random.nextFloat() * 0.2F);
//                world.removeBlock(pos, false);
//
//                world.playLevelEvent(2001, pos, Block.getRawIdFromState(state));
//
//                dragonEntity.refreshPositionAndAngles((double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), 0.0F, 0.0F);
//                world.spawnEntity(dragonEntity);
        }
    }

    private boolean shouldHatchProgress(World world) {
        return world.random.nextInt(20) == 0;
    }

    private boolean isOnGem(BlockView world, BlockPos pos) {
        String name;
        Block block = world.getBlockState(pos.down()).getBlock();
        for(GemType type: GemType.MAP.values()) {
            name = type.getName() + "_block";
            if(block == ModBlocks.blockMap.get(name)) {
                return true;
            }
        }
        return world.getBlockState(pos.down()).getBlock() == Blocks.SAND;
    }


    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(HATCH);
    }

    static {
        HATCH = Properties.HATCH;
    }

//    @Override
//    public BlockEntity createBlockEntity(BlockView view) {
//        return new DragonEggBlockEntity(this.type);
//    }
//
//    @Override
//    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
//        super.onPlaced(world, pos, state, placer, itemStack);
//        if(placer instanceof PlayerEntity)
//            ((DragonEggBlockEntity)world.getBlockEntity(pos)).SetOwner(placer.getUuid());
//    }

    public GemType getGemType() {
        return this.type;
    }
}
