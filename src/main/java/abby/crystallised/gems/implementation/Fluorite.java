package abby.crystallised.gems.implementation;

import com.mojang.datafixers.util.Pair;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.ArrayList;
import java.util.List;

public class Fluorite extends BaseImplementation {
    @Override
    public List<Pair<StatusEffectInstance, Float>> getStatusEffectsWhenEaten() {
        List<Pair<StatusEffectInstance, Float>> list = new ArrayList<>();
        list.add(Pair.of(new StatusEffectInstance(StatusEffects.NAUSEA, 20 * 7, 1), 0.1F));
        return list;
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        ItemStack itemStack = super.finishUsing(stack, world, user);

        if (world.isClient) {
            return itemStack;
        }

        // repeat 5 times
        for (int i = 0; i < 5; i++) {
            // get random coordinates within 8 blocks of user
            int d1 = user.getBlockX() + (int)((user.getRandom().nextDouble() - 0.5) * 16);
            int e1 = user.getBlockY() + (int)((user.getRandom().nextDouble() - 0.5) * 16);
            int f1 = user.getBlockZ() + (int)((user.getRandom().nextDouble() - 0.5) * 16);
            BlockPos blockPos1 = new BlockPos(d1, e1, f1);

            // get random coordinates within 8 blocks of user
            int d2 = user.getBlockX() + (int)((user.getRandom().nextDouble() - 0.5) * 16);
            int e2 = user.getBlockY() + (int)((user.getRandom().nextDouble() - 0.5) * 16);
            int f2 = user.getBlockZ() + (int)((user.getRandom().nextDouble() - 0.5) * 16);
            BlockPos blockPos2 = new BlockPos(d2, e2, f2);

            // Save chosen blocks
            BlockState block1 = world.getBlockState(blockPos1);
            BlockState block2 = world.getBlockState(blockPos2);

            // Skip if block has block entity
            if (block1.hasBlockEntity() || block2.hasBlockEntity())
            {
                continue;
            }
            // Skip if block is bedrock
            if (block1.getBlock() == Blocks.BEDROCK || block2.getBlock() == Blocks.BEDROCK) {
                continue;
            }

            // Swap blocks
            world.setBlockState(blockPos1, block2);
            world.setBlockState(blockPos2, block1);
        }

        Vec3d vec3d = user.getPos();
        world.emitGameEvent(GameEvent.TELEPORT, vec3d, GameEvent.Emitter.of(user));
        SoundCategory soundCategory = SoundCategory.PLAYERS;

        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, soundCategory);
        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.BLOCK_GLASS_BREAK, soundCategory);

        return itemStack;
    }
}
