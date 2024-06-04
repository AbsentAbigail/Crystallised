package abby.crystallised.gems.implementation;

import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public interface GemImplementation {
    /**
     * Gem item effects
     */

    // Restored hunger when eaten
    default int getNutrition() { return 0; }

    // Restored Saturation when eaten (Saturation = Hunger * saturation * 2
    default float getSaturation() { return 0F; }

    default boolean isSnack() { return false; }

    // Status effects and percentage chances when eaten
    default List<Pair<StatusEffectInstance, Float>> getStatusEffectsWhenEaten() {
        return new ArrayList<>();
    }

    default ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        return stack;
    }

    /**
     * Jewelry item effects
     */
    default void braceletInventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {}

    default void necklaceInventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {}

    default ActionResult keyUseOnBlock(ItemUsageContext context, ActionResult actionResult) {
        return actionResult;
    }
}
