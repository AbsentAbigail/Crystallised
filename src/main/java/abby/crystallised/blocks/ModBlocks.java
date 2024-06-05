package abby.crystallised.blocks;

import abby.crystallised.Constants;
import abby.crystallised.Utility;
import abby.crystallised.gems.GemType;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import java.util.LinkedHashMap;
import java.util.Map;

public class ModBlocks {
    public static final Map<String, Block> blockMap = new LinkedHashMap<>();

    private static final AbstractBlock.Settings prideSettings = AbstractBlock.Settings.copy(Blocks.WHITE_WOOL)
            .hardness(0.0F)
            .resistance(1200.0F);
    public static final Block PRIDE = new Block(prideSettings);
    public static final Block LESBIAN = new Block(prideSettings);
    public static final Block VINCIAN = new Block(prideSettings);
    public static final Block BISEXUAL = new Block(prideSettings);
    public static final Block PANSEXUAL = new Block(prideSettings);
    public static final Block ASEXUAL = new Block(prideSettings);
    public static final Block AROMANTIC = new Block(prideSettings);
    public static final Block TRANS = new Block(prideSettings);
    public static final Block NONBINARY = new Block(prideSettings);
    public static final Block GENDERFLUID = new Block(prideSettings);
    public static final Block AGENDER = new Block(prideSettings);
    public static final Block DEMIGIRL = new Block(prideSettings);
    public static final Block DEMIBOY = new Block(prideSettings);
    public static final Block ITALIAN = new Block(prideSettings);

    public ModBlocks() {
        Utility.LOGGER.debug("START REGISTER BLOCKS");

        registerGemBlocks();

        blockMap.put("gem_cutter", new GemCutter());

        blockMap.forEach((name, block) ->
            registerBlock(block, name)
        );

        registerPrideBlock(PRIDE, "pride");
        registerPrideBlock(LESBIAN, "lesbian");
        registerPrideBlock(VINCIAN, "vincian");
        registerPrideBlock(BISEXUAL, "bisexual");
        registerPrideBlock(PANSEXUAL, "pansexual");
        registerPrideBlock(ASEXUAL, "asexual");
        registerPrideBlock(AROMANTIC, "aromantic");
        registerPrideBlock(TRANS, "trans");
        registerPrideBlock(NONBINARY, "nonbinary");
        registerPrideBlock(GENDERFLUID, "genderfluid");
        registerPrideBlock(AGENDER, "agender");
        registerPrideBlock(DEMIGIRL, "demigirl");
        registerPrideBlock(DEMIBOY, "demiboy");
        registerPrideBlock(ITALIAN, "italian");
    }

    private void registerGemBlocks() {
        GemType.forEach((name, type) -> {
            blockMap.put(name + Constants.BLOCK_SUFFIX, new GemBlock(type));
            blockMap.put(name + Constants.ORE_SUFFIX, new GemOre(type));
            blockMap.put(name + Constants.ORE_DEEPSLATE_SUFFIX, new GemOre(type));
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

    private void registerPrideBlock(Block block, String name) {
        Utility.LOGGER.debug("Registered: " + name);
        Identifier identifier = Utility.identifier(name);
        Registry.register(Registries.BLOCK, identifier, block);
        Registry.register(Registries.ITEM, identifier,
                new BlockItem(block, new Item.Settings().fireproof().rarity(Rarity.UNCOMMON)));
    }
}
