package abby.crystallised.gems.implementation;

import abby.crystallised.Utility;
import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Fluorite extends BaseImplementation {
    @Override
    public List<Pair<StatusEffectInstance, Float>> getStatusEffectsWhenEaten() {
        List<Pair<StatusEffectInstance, Float>> list = new ArrayList<>();
        list.add(Pair.of(new StatusEffectInstance(StatusEffects.SLOWNESS, 20 * 15, 1), 0.5F));
        list.add(Pair.of(new StatusEffectInstance(StatusEffects.SPEED, 20 * 60, 2), 0.5F));
        return list;
    }
}
