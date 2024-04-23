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

public class Petalite extends BaseImplementation {
    @Override
    public List<Pair<StatusEffectInstance, Float>> getStatusEffectsWhenEaten() {
        List<Pair<StatusEffectInstance, Float>> list = new ArrayList<>();
        list.add(Pair.of(new StatusEffectInstance(StatusEffects.LEVITATION, 30, 2), 1F));
        return list;
    }

    @Override
    public void tickInventory(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        LivingEntity livingEntity = (LivingEntity) entity;
        if (!livingEntity.isSneaking()) {
            if (true) {
                livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 21, 2));
            } else {
                livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 21));
            }
        }
    }
}
