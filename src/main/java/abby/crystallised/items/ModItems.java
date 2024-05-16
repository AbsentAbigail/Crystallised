package abby.crystallised.items;

import abby.crystallised.Constants;
import abby.crystallised.Utility;
import abby.crystallised.gems.GemType;
import abby.crystallised.gems.MaterialGem;
import abby.crystallised.items.jewelry.BraceletItem;
import abby.crystallised.items.jewelry.MetalBase;
import abby.crystallised.items.tools.AxeBase;
import abby.crystallised.items.tools.PickaxeBase;
import abby.crystallised.items.tools.ShovelBase;
import abby.crystallised.items.tools.SwordBase;
import net.minecraft.item.Item;
import net.minecraft.item.ToolItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import java.util.LinkedHashMap;
import java.util.Map;

public class ModItems {
    public static final Map<String, Item> gemItemMap = new LinkedHashMap<>();
    public static final Map<String, Item> rawGemItemMap = new LinkedHashMap<>();
    public static final Map<String, Item> accessoryItemMap = new LinkedHashMap<>();
    public static final Map<String, Item> gemToolMap = new LinkedHashMap<>();
    public static final Map<String, Item> basicItemMap = new LinkedHashMap<>();

    public static final Item OBSIDIAN_SHARD = new BasicItem();
    public static final Item TOOL_ROD = new BasicItem();

    public ModItems() {
        Utility.LOGGER.debug("START REGISTER ITEMS");

        registerGemItems();

        registerItemInMap("obsidian_shard", OBSIDIAN_SHARD, basicItemMap);
        registerItemInMap("tool_rod", TOOL_ROD, basicItemMap);
    }

    private void registerItemInMap(String name, Item item, Map<String, Item> map) {
        map.put(name, item);
        registerItem(name, item);
    }

    private void registerItem(String name, Item item) {
        Registry.register(Registries.ITEM, Utility.identifier(name), item);
    }

    private void registerGemItems() {
        GemType.forEach((name, type) -> {
            registerItemInMap(name, new CutGemItem(type), gemItemMap);
            registerItemInMap(name + Constants.RAW_SUFFIX, new RawGemItem(type), rawGemItemMap);
            for (MetalBase metalBase : MetalBase.values()) {
                registerItemInMap(name + metalBase.getItemSuffix() + Constants.BRACELET_SUFFIX, new BraceletItem(type), accessoryItemMap);
            }
        });
        registerTools();
    }

    private void registerTools() {
        GemType.forEach((name, type) -> {
            String namePick = name + Constants.PICKAXE_SUFFIX;
            String nameAxe = name + Constants.AXE_SUFFIX;
            String nameShovel = name + Constants.SHOVEL_SUFFIX;
            String nameSword = name + Constants.SWORD_SUFFIX;

            gemToolMap.put(namePick, new PickaxeBase(type.getMaterial()));
            registerTool((ToolItem)gemToolMap.get(namePick), Constants.PICKAXE_SUFFIX);

            gemToolMap.put(nameAxe, new AxeBase(type.getMaterial()));
            registerTool((ToolItem)gemToolMap.get(nameAxe), Constants.AXE_SUFFIX);

            gemToolMap.put(nameShovel, new ShovelBase(type.getMaterial()));
            registerTool((ToolItem)gemToolMap.get(nameShovel), Constants.SHOVEL_SUFFIX);

            gemToolMap.put(nameSword, new SwordBase(type.getMaterial()));
            registerTool((ToolItem)gemToolMap.get(nameSword), Constants.SWORD_SUFFIX);
        });
    }

    private void registerTool(ToolItem item, String suffix) {
        String name = ((MaterialGem)item.getMaterial()).getGemType().getName() + suffix;
        registerItem(name, item);
    }
}
