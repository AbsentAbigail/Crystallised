package abby.crystallised.datagen;

import abby.crystallised.Constants;
import abby.crystallised.blocks.ModBlocks;
import abby.crystallised.gems.GemType;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class BlockTagGenerator extends FabricTagProvider.BlockTagProvider {
    public BlockTagGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        GemType.forEach((name, type) -> {
            Block block = ModBlocks.blockMap.get(name + Constants.BLOCK_SUFFIX);
            Block ore = ModBlocks.blockMap.get(name + Constants.ORE_SUFFIX);
            Block core = ModBlocks.blockMap.get(name + Constants.CORE_SUFFIX);

            getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                    .add(
                            block,
                            ore,
                            ModBlocks.blockMap.get(name + Constants.LAMP_SUFFIX),
                            ModBlocks.blockMap.get(name + Constants.INVERTED_LAMP_SUFFIX),
                            core
                    );
            getOrCreateTagBuilder(BlockTags.BEACON_BASE_BLOCKS)
                    .add(block);
            getOrCreateTagBuilder(BlockTags.STONE_ORE_REPLACEABLES)
                    .add(ore);
            getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                    .add(
                            ore,
                            core
                    );
        });
    }
}
