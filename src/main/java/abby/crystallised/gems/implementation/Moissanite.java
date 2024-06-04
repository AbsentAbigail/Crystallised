package abby.crystallised.gems.implementation;

import abby.crystallised.miscellaneous.ModDamageSources;
import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class Moissanite implements GemImplementation {
    @Override
    public List<Pair<StatusEffectInstance, Float>> getStatusEffectsWhenEaten() {
        List<Pair<StatusEffectInstance, Float>> list = new ArrayList<>();
        list.add(Pair.of(new StatusEffectInstance(StatusEffects.RESISTANCE, 20 * 60 * 5, 2), 1F));
        return list;
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (world.isClient)
            return stack;

        user.damage(ModDamageSources.of(world, ModDamageSources.EAT_ROCK), 40);
        return stack;
    }

    @Override
    public void braceletInventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        LivingEntity livingEntity = (LivingEntity) entity;
        livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 30, 3));
        livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 30));
    }
}
