package abby.crystallised.blocks;

import abby.crystallised.Utility;
import abby.crystallised.gems.GemType;
import net.minecraft.block.Block;
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
        String name;
        for(GemType type: GemType.MAP.values()) {
            name = type.getName();
            blockMap.put(name + "_block", new GemBlock(type));
            blockMap.put(name + "_ore", new GemOre(type));
            blockMap.put(name + "_lamp", new GemLamp(false));
            blockMap.put(name + "_lamp_inverted", new GemLamp(true));
            blockMap.put(name + "_egg", new GemEgg(type));
        }
    }

    private void registerBlock(Block block, String name) {
        Utility.LOGGER.debug("Registered: " + name);
        Identifier identifier = Utility.identifier(name);
        Registry.register(Registries.BLOCK, identifier, block);
        Registry.register(Registries.ITEM, identifier, new BlockItem(block, new Item.Settings()));
    }
}
