package abby.crystallised.items;

import abby.crystallised.Constants;
import abby.crystallised.Utility;
import abby.crystallised.gems.GemType;
import abby.crystallised.items.jewelry.BraceletItem;
import abby.crystallised.items.jewelry.KeyItem;
import abby.crystallised.items.jewelry.MetalBase;
import abby.crystallised.items.jewelry.NecklaceItem;
import abby.crystallised.items.tools.*;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import java.util.LinkedHashMap;
import java.util.Map;

public class ModItems {
    public static final Map<String, Item> gemItemMap = new LinkedHashMap<>();
    public static final Map<String, Item> accessoryItemMap = new LinkedHashMap<>();
    public static final Map<String, Item> gemToolMap = new LinkedHashMap<>();
    public static final Map<String, Item> basicItemMap = new LinkedHashMap<>();

    public static final Item OBSIDIAN_SHARD = new BasicItem();
    public static final Item TOOL_ROD = new BasicItem();

    public ModItems() {
        Utility.LOGGER.debug("START REGISTER ITEMS");

        for (MetalBase metalBase : MetalBase.values()) {
            registerItemInMap(
                    metalBase.getLowercaseName() + Constants.BRACELET_SUFFIX, new BasicItem(), accessoryItemMap
            );
            registerItemInMap(
                    metalBase.getLowercaseName() + Constants.NECKLACE_SUFFIX, new BasicItem(), accessoryItemMap
            );
            registerItemInMap(
                    metalBase.getLowercaseName() + Constants.KEY_SUFFIX, new BasicItem(), accessoryItemMap
            );
        }
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
            registerItemInMap(name, new GemItem(type), gemItemMap);
            for (MetalBase metalBase : MetalBase.values()) {
                registerItemInMap(name + metalBase.getItemSuffix() + Constants.BRACELET_SUFFIX, new BraceletItem(type), accessoryItemMap);
                registerItemInMap(name + metalBase.getItemSuffix() + Constants.NECKLACE_SUFFIX, new NecklaceItem(type), accessoryItemMap);
                registerItemInMap(name + metalBase.getItemSuffix() + Constants.KEY_SUFFIX, new KeyItem(type), accessoryItemMap);
            }
        });
        registerTools();
    }

    private void registerTools() {
        GemType.forEach((name, type) -> {
            registerTool(name + Constants.PICKAXE_SUFFIX, new PickaxeBase(type.getMaterial()));
            registerTool(name + Constants.AXE_SUFFIX, new AxeBase(type.getMaterial()));
            registerTool(name + Constants.SHOVEL_SUFFIX, new ShovelBase(type.getMaterial()));
            registerTool(name + Constants.SWORD_SUFFIX, new SwordBase(type.getMaterial()));
            registerTool(name + Constants.HOE_SUFFIX, new HoeBase(type.getMaterial()));
        });
    }

    private void registerTool(String name, Item tool) {
        gemToolMap.put(name, tool);
        registerItem(name, tool);

    }
}
