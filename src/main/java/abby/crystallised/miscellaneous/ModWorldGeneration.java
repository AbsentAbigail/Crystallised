package abby.crystallised.miscellaneous;

import abby.crystallised.Constants;
import abby.crystallised.Utility;
import abby.crystallised.gems.GemType;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class ModWorldGeneration {
    public static final HashMap<GemType, RegistryKey<PlacedFeature>> ORE_PLACED_KEYS = new LinkedHashMap<>();

    public ModWorldGeneration() {
        Utility.LOGGER.debug("START REGISTER WORLD GENERATION");

        GemType.forEach((name, type) ->
            BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES,
                ORE_PLACED_KEYS.get(type)));
    }

    static {
        GemType.forEach((name, type) ->
            ORE_PLACED_KEYS.put(
                type,
                RegistryKey.of(RegistryKeys.PLACED_FEATURE, Utility.identifier(name + Constants.ORE_SUFFIX))
        ));
    }
}
