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
                            ModItems.gemItemMap.get(name)
                    ));
            addDrop(ModBlocks.blockMap.get(name + Constants.ORE_DEEPSLATE_SUFFIX),
                    oreDrops(
                            ModBlocks.blockMap.get(name + Constants.ORE_DEEPSLATE_SUFFIX),
                            ModItems.gemItemMap.get(name)
                    ));
            addDrop(ModBlocks.blockMap.get(name + Constants.BLOCK_SUFFIX));
            addDrop(ModBlocks.blockMap.get(name + Constants.LAMP_SUFFIX));
            addDrop(ModBlocks.blockMap.get(name + Constants.INVERTED_LAMP_SUFFIX));
            addDrop(ModBlocks.blockMap.get(name + Constants.CORE_SUFFIX));
        });

        addDrop(ModBlocks.PRIDE);
        addDrop(ModBlocks.LESBIAN);
        addDrop(ModBlocks.VINCIAN);
        addDrop(ModBlocks.BISEXUAL);
        addDrop(ModBlocks.PANSEXUAL);
        addDrop(ModBlocks.ASEXUAL);
        addDrop(ModBlocks.AROMANTIC);
        addDrop(ModBlocks.TRANS);
        addDrop(ModBlocks.NONBINARY);
        addDrop(ModBlocks.GENDERFLUID);
        addDrop(ModBlocks.AGENDER);
        addDrop(ModBlocks.DEMIGIRL);
        addDrop(ModBlocks.DEMIBOY);
    }
}
