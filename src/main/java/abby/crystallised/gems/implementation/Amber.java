package abby.crystallised.gems.implementation;

import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

import java.util.ArrayList;
import java.util.List;

public class Amber implements GemImplementation {
    @Override
    public List<Pair<StatusEffectInstance, Float>> getStatusEffectsWhenEaten() {
        List<Pair<StatusEffectInstance, Float>> list = new ArrayList<>();
        list.add(Pair.of(new StatusEffectInstance(StatusEffects.SLOWNESS, 200), 0.7F));
        list.add(Pair.of(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 200, 1), 0.7F));
        list.add(Pair.of(new StatusEffectInstance(StatusEffects.STRENGTH, 200, 4), 0.7F));
        return list;
    }
}
