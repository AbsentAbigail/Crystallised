package abby.crystallised.gems.implementation;

import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class BaseImplementation {
    /**
     * Gem item effects
     */

    // Restored hunger when eaten
    public int getNutrition() { return 0; }

    // Restored Saturation when eaten (Saturation = Hunger * saturation * 2
    public float getSaturation() { return 0F; }

    public boolean isSnack() { return false; }

    // Status effects and percentage chances when eaten
    public List<Pair<StatusEffectInstance, Float>> getStatusEffectsWhenEaten() {
        return new ArrayList<>();
    }

    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        return stack;
    }
}
