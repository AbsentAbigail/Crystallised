package abby.crystallised.gems.implementation;

import abby.crystallised.Utility;
import abby.crystallised.miscellaneous.ModDamageSources;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.List;

public class Moonstone extends BaseImplementation {
    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        ItemStack itemStack = super.finishUsing(stack, world, user);
        if (world.isClient) {
            return itemStack;
        }

        int moonphase = world.getMoonPhase();
        boolean isNight = world.isNight();

        if (!isNight) {
            SoundEvent soundEvent = SoundEvents.BLOCK_REDSTONE_TORCH_BURNOUT;
            SoundCategory soundCategory = SoundCategory.PLAYERS;

            world.playSound((PlayerEntity)null, user.getX(), user.getY(), user.getZ(), soundEvent, soundCategory);

            return itemStack;
        }

        if (moonphase == 0) {
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 30, 2));
        } else if (moonphase == 4) {
            int mobCount = dealAOE(world, user, 7, 10);
            if (mobCount != 0) {
                user.heal(mobCount);

                SoundEvent soundEvent = SoundEvents.BLOCK_CONDUIT_AMBIENT;
                SoundCategory soundCategory = SoundCategory.PLAYERS;

                world.playSound((PlayerEntity)null, user.getX(), user.getY(), user.getZ(), soundEvent, soundCategory);
            }
        }
        return itemStack;
    }

    private int dealAOE(World world, LivingEntity user, float radius, float damage) {
        Box box = new Box(
                user.getX() - radius, user.getY() - radius, user.getZ() - radius,
                user.getX() + radius, user.getY() + radius, user.getZ() + radius
        );
        int count = 0;

        List<Entity> entities = world.getOtherEntities(user, box, entity -> {
            entity.setGlowing(false);
            if (entity.isSpectator())
                return false;
            if (entity.squaredDistanceTo(user) > radius*radius)
                return false;
            if (!entity.isLiving())
                return false;

            entity.damage(ModDamageSources.of(world, ModDamageSources.MOONFALL), damage);

            return true;
        });

        return entities.size();
    }
}
