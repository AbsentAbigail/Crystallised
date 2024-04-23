package abby.crystallised.items;

import abby.crystallised.Utility;
import abby.crystallised.gems.GemType;
import abby.crystallised.gems.MaterialGem;
import abby.crystallised.items.jewelry.RingItem;
import abby.crystallised.items.tools.AxeBase;
import abby.crystallised.items.tools.PickaxeBase;
import abby.crystallised.items.tools.ShovelBase;
import abby.crystallised.items.tools.SwordBase;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ToolItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import java.util.LinkedHashMap;
import java.util.Map;

public class ModItems {
    public static final Map<String, Item> gemItemMap = new LinkedHashMap<>();
    public static final Map<String, Item> scaleItemMap = new LinkedHashMap<>();
    public static final Map<String, Item> accessoryItemMap = new LinkedHashMap<>();
    public static final Map<String, Item> gemToolMap = new LinkedHashMap<>();
    public static final Map<String, Item> basicItemMap = new LinkedHashMap<>();

    public ModItems() {
        Utility.LOGGER.debug("START REGISTER ITEMS");

        registerGemItems();

        registerItemInMap("obsidian_shard", new BasicItem(), basicItemMap);
        registerItemInMap("tool_rod", new BasicItem(), basicItemMap);

    }

    private void registerItemInMap(String name, Item item, Map<String, Item> map) {
        map.put(name, item);
        registerItem(name, item);
    }

    private void registerItem(String name, Item item) {
        Utility.LOGGER.debug("Registered: " + name);

        Registry.register(Registries.ITEM, Utility.identifier(name), item);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(content -> content.add(item));
    }

    private void registerGemItems() {
        String name;
        for(GemType type: GemType.MAP.values()) {
            name = type.getName();
            registerItemInMap(name, new GemItem(type), gemItemMap);
            registerItemInMap(name + "_scale", new GemItem(type), scaleItemMap);
            registerItemInMap(name + "_ring", new RingItem(type), accessoryItemMap);
        }
        registerTools();
    }

    private void registerTools() {
        String pickaxe = "_pickaxe";
        String axe = "_axe";
        String shovel = "_shovel";
        String sword = "_sword";

        String namePick;
        String nameAxe;
        String nameShovel;
        String nameSword;

        for(GemType type: GemType.MAP.values()) {
            namePick = type.getName() + pickaxe;
            nameAxe = type.getName() + axe;
            nameShovel = type.getName() + shovel;
            nameSword = type.getName() + sword;

            gemToolMap.put(namePick, new PickaxeBase(type.getMaterial()));
            registerTool((ToolItem)gemToolMap.get(namePick), pickaxe);

            gemToolMap.put(nameAxe, new AxeBase(type.getMaterial()));
            registerTool((ToolItem)gemToolMap.get(nameAxe), axe);

            gemToolMap.put(nameShovel, new ShovelBase(type.getMaterial()));
            registerTool((ToolItem)gemToolMap.get(nameShovel), shovel);

            gemToolMap.put(nameSword, new SwordBase(type.getMaterial()));
            registerTool((ToolItem)gemToolMap.get(nameSword), sword);
        }
    }

    private void registerTool(ToolItem item, String suffix) {

        String name = ((MaterialGem)item.getMaterial()).getGemType().getName() + suffix;
        registerItem(name, item);
    }
}
