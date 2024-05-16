package abby.crystallised.items;

import abby.crystallised.blocks.GemCore;
import abby.crystallised.gems.GemType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class CutGemItem extends Item {
    private final GemType type;

    public CutGemItem(GemType type) {
        super(buildSettings(type));
        this.type = type;
    }

    public GemType getGemType() {
        return type;
    }

    private static Settings buildSettings(GemType gemType) {
        return new Settings();
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();

        if (world.isClient)
            return super.useOnBlock(context);

        BlockPos pos = context.getBlockPos();
        BlockState blockState = world.getBlockState(pos);
        Block block = blockState.getBlock();
        PlayerEntity player = context.getPlayer();

        if (type.getHardness() > 6) {
            chipObsidian(world, player, pos, block);
        }

        if (block instanceof GemCore) {
            int r = blockState.get(GemCore.RECOVER);
            world.setBlockState(pos, blockState.with(GemCore.RECOVER, ++r % 3));
        }

        return super.useOnBlock(context);
    }

    private void chipObsidian(World world, PlayerEntity player, BlockPos pos, Block block) {
        if (block != Blocks.OBSIDIAN)
            return;
        if (player == null)
            return;

        Random random = new Random();

        if (random.nextInt(4) == 0) {
            world.breakBlock(pos, false);
        }

        SoundEvent soundEvent = SoundEvents.BLOCK_STONE_BREAK;
        SoundCategory soundCategory = SoundCategory.PLAYERS;

        world.playSound(null, pos, soundEvent, soundCategory);

        player.giveItemStack(new ItemStack(ModItems.OBSIDIAN_SHARD, 1));
    }
}
