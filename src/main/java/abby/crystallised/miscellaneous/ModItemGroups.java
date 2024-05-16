package abby.crystallised.miscellaneous;

import abby.crystallised.Constants;
import abby.crystallised.Utility;
import abby.crystallised.blocks.ModBlocks;
import abby.crystallised.gems.GemType;
import abby.crystallised.items.ModItems;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;

import java.util.Map;

public class ModItemGroups {
    public ModItemGroups() {
        Registry.register(Registries.ITEM_GROUP, Utility.identifier("general_group"), GENERAL_GROUP);
        Registry.register(Registries.ITEM_GROUP, Utility.identifier("tool_group"), TOOL_GROUP);
    }
    private static final Map<String, Item> map = ModItems.gemToolMap;

    private static final ItemGroup GENERAL_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.gemItemMap.get(GemType.SAPPHIRE.getName())))
            .displayName(Text.translatable("itemGroup.crystallised.general"))
            .entries(((displayContext, entries) -> {
                ModItems.gemItemMap.forEach((s, item) -> entries.add(item));
                ModItems.rawGemItemMap.forEach((s, item) -> entries.add(item));
                ModItems.accessoryItemMap.forEach((s, item) -> entries.add(item));
                ModBlocks.blockMap.forEach((s, block) -> entries.add(block));
                ModItems.basicItemMap.forEach((s, item) -> entries.add(item));
            }))
            .build();

    private static final ItemGroup TOOL_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(map.get(GemType.SAPPHIRE.getName() + Constants.PICKAXE_SUFFIX)))
            .displayName(Text.translatable("itemGroup.crystallised.tools"))
            .entries((context, entries) -> {
                String name;
                for(GemType type: GemType.MAP.values()) {
                    name = type.getName();
                    entries.add(new ItemStack(map.get(name + Constants.PICKAXE_SUFFIX)));
                    entries.add(new ItemStack(map.get(name + Constants.AXE_SUFFIX)));
                    entries.add(new ItemStack(map.get(name + Constants.SHOVEL_SUFFIX)));
                    entries.add(new ItemStack(map.get(name + Constants.SWORD_SUFFIX)));
                }
            })
            .build();
}
