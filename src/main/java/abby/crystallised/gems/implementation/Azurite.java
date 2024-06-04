package abby.crystallised.gems.implementation;

import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class Azurite implements GemImplementation {
    /**
     * Gem item effects
     */
    // Status effects and percentage chances when eaten
    public List<Pair<StatusEffectInstance, Float>> getStatusEffectsWhenEaten() {
        List<Pair<StatusEffectInstance, Float>> list = new ArrayList<>();
        list.add(Pair.of(new StatusEffectInstance(StatusEffects.WATER_BREATHING, 20 * 15), 1F));
        return list;
    }

    @Override
    public void braceletInventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!entity.isTouchingWaterOrRain())
            return;
        if (!entity.isLiving())
            return;

        LivingEntity livingEntity = (LivingEntity) entity;
        if (livingEntity.hasStatusEffect(StatusEffects.SPEED)) {
            livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 1, 1));
        }
        livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 1));

        if (livingEntity.hasStatusEffect(StatusEffects.STRENGTH)) {
            livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 1, 1));
        }
        livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 1));
    }

    @Override
    public void necklaceInventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!entity.isLiving())
            return;
        if (!entity.isTouchingWater())
            return;

        LivingEntity livingEntity = (LivingEntity) entity;

        livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.WATER_BREATHING, 1));
    }
}
