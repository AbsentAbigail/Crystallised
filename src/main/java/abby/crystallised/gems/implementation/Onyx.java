package abby.crystallised.gems.implementation;

import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class Onyx extends BaseImplementation {
    /**
     * Gem item effects
     */
    // Status effects and percentage chances when eaten
    public List<Pair<StatusEffectInstance, Float>> getStatusEffectsWhenEaten() {
        List<Pair<StatusEffectInstance, Float>> list = new ArrayList<>();
        list.add(Pair.of(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 20 * 60), 1F));
        list.add(Pair.of(new StatusEffectInstance(StatusEffects.BLINDNESS, 20 * 5), 0.4F));
        return list;
    }

    @Override
    public void tickInventory(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.tickInventory(stack, world, entity, slot, selected);
        if (world.isClient) {
            return;
        }
        LivingEntity livingEntity = (LivingEntity) entity;
        if (true) {
            livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY, 21));
            livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 40));
            world.getOtherEntities(
                    livingEntity,
                    new Box(livingEntity.getX() - 10, livingEntity.getY() - 10, livingEntity.getZ() - 10,
                            livingEntity.getX() + 10, livingEntity.getY() + 10, livingEntity.getZ() + 10),
                    mob -> {
                        if (!(mob instanceof LivingEntity))
                            return false;
                        ((LivingEntity) mob).setAttacking(null);
                        return true;
                    }
            );
        } else {
            livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 21));
        }
    }
}
