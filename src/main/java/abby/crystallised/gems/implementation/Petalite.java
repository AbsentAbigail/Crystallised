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

public class Petalite implements GemImplementation {
    @Override
    public List<Pair<StatusEffectInstance, Float>> getStatusEffectsWhenEaten() {
        List<Pair<StatusEffectInstance, Float>> list = new ArrayList<>();
        list.add(Pair.of(new StatusEffectInstance(StatusEffects.LEVITATION, 30, 2), 1F));
        return list;
    }

    @Override
    public void braceletInventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        LivingEntity livingEntity = (LivingEntity) entity;
        livingEntity.fallDistance = 0;
    }
}
