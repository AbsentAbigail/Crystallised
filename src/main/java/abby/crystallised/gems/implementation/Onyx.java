package abby.crystallised.gems.implementation;

import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

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
}
