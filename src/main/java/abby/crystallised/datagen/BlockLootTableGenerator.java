package abby.crystallised.datagen;

import abby.crystallised.Constants;
import abby.crystallised.blocks.ModBlocks;
import abby.crystallised.gems.GemType;
import abby.crystallised.items.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class BlockLootTableGenerator extends FabricBlockLootTableProvider {

    public BlockLootTableGenerator(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        GemType.forEach((name, gemType) -> {
            addDrop(ModBlocks.blockMap.get(name + Constants.ORE_SUFFIX),
                    oreDrops(
                            ModBlocks.blockMap.get(name + Constants.ORE_SUFFIX),
                            ModItems.rawGemItemMap.get(name + Constants.RAW_SUFFIX)
                    ));
            addDrop(ModBlocks.blockMap.get(name + Constants.BLOCK_SUFFIX), drops(ModBlocks.blockMap.get(name + Constants.BLOCK_SUFFIX)));
            addDrop(ModBlocks.blockMap.get(name + Constants.LAMP_SUFFIX), drops(ModBlocks.blockMap.get(name + Constants.LAMP_SUFFIX)));
            addDrop(ModBlocks.blockMap.get(name + Constants.INVERTED_LAMP_SUFFIX), drops(ModBlocks.blockMap.get(name + Constants.INVERTED_LAMP_SUFFIX)));
            addDrop(ModBlocks.blockMap.get(name + Constants.CORE_SUFFIX), drops(ModBlocks.blockMap.get(name + Constants.CORE_SUFFIX)));
        });

    }
}
