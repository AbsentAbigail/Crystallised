package abby.crystallised.blocks;

import abby.crystallised.Constants;
import abby.crystallised.Utility;
import abby.crystallised.gems.GemType;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.LinkedHashMap;
import java.util.Map;

public class ModBlocks {
    public static final Map<String, Block> blockMap = new LinkedHashMap<>();

    public ModBlocks() {
        Utility.LOGGER.debug("START REGISTER BLOCKS");

        registerGemBlocks();

        blockMap.put("gem_cutter", new GemCutter());

        blockMap.forEach((name, block) ->
            registerBlock(block, name)
        );
    }

    private void registerGemBlocks() {
        GemType.forEach((name, type) -> {
            blockMap.put(name + Constants.BLOCK_SUFFIX, new GemBlock(type));
            blockMap.put(name + Constants.ORE_SUFFIX, new GemOre(type));
            blockMap.put(name + Constants.ORE_SUFFIX + Constants.DEEPSLATE_SUFFIX, new GemOre(type));
            blockMap.put(name + Constants.LAMP_SUFFIX, new GemLamp(false));
            blockMap.put(name + Constants.INVERTED_LAMP_SUFFIX, new GemLamp(true));
            blockMap.put(name + Constants.CORE_SUFFIX, new GemCore(type));
        });
    }

    private void registerBlock(Block block, String name) {
        Utility.LOGGER.debug("Registered: " + name);
        Identifier identifier = Utility.identifier(name);
        Registry.register(Registries.BLOCK, identifier, block);
        Registry.register(Registries.ITEM, identifier, new BlockItem(block, new Item.Settings()));
    }
}
