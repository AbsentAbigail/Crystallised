package abby.crystallised.gems.implementation;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class Kunzite implements GemImplementation {
    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (world.isClient) {
            return stack;
        }

        for(int i = 0; i < 16; ++i) {
            double d = user.getX() + (user.getRandom().nextDouble() - 0.5) * 8.0;
            double e = user.getY();
            double f = user.getZ() + (user.getRandom().nextDouble() - 0.5) * 8.0;
            if (user.hasVehicle()) {
                user.stopRiding();
            }

            Vec3d vec3d = user.getPos();
            if (user.teleport(d, e, f, true)) {
                world.emitGameEvent(GameEvent.TELEPORT, vec3d, GameEvent.Emitter.of(user));
                SoundCategory soundCategory = SoundCategory.PLAYERS;
                SoundEvent soundEvent = SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT;

                world.playSound(null, user.getX(), user.getY(), user.getZ(), soundEvent, soundCategory);
                user.onLanding();
                break;
            }
        }
        return stack;
    }
}
