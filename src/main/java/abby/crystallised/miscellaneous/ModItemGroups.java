package abby.crystallised.miscellaneous;

import abby.crystallised.Constants;
import abby.crystallised.Utility;
import abby.crystallised.blocks.ModBlocks;
import abby.crystallised.gems.GemType;
import abby.crystallised.items.ModItems;
import abby.crystallised.items.jewelry.MetalBase;
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
        Utility.LOGGER.debug("START REGISTER ITEM GROUPS");

        Registry.register(Registries.ITEM_GROUP, Utility.identifier(Constants.GENERAL_GROUP_ID), GENERAL_GROUP);
        Registry.register(Registries.ITEM_GROUP, Utility.identifier(Constants.TOOL_GROUP_ID), TOOL_GROUP);
        Registry.register(Registries.ITEM_GROUP, Utility.identifier(Constants.ACCESSORY_GROUP_ID), ACCESSORY_GROUP);
    }
    private static final Map<String, Item> TOOL_MAP = ModItems.gemToolMap;

    private static final ItemGroup GENERAL_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.gemItemMap.get(GemType.SAPPHIRE.getName())))
            .displayName(Text.translatable("itemgroup.crystallised.general"))
            .entries(((displayContext, entries) -> {
                ModItems.gemItemMap.forEach((s, item) -> entries.add(item));
                ModBlocks.blockMap.forEach((s, block) -> entries.add(block));
                ModItems.basicItemMap.forEach((s, item) -> entries.add(item));

                entries.add(ModBlocks.PRIDE);
                entries.add(ModBlocks.PROGRESS);
                entries.add(ModBlocks.LESBIAN);
                entries.add(ModBlocks.VINCIAN);
                entries.add(ModBlocks.BISEXUAL);
                entries.add(ModBlocks.PANSEXUAL);
                entries.add(ModBlocks.ASEXUAL);
                entries.add(ModBlocks.AROMANTIC);
                entries.add(ModBlocks.TRANS);
                entries.add(ModBlocks.TRANSFEM);
                entries.add(ModBlocks.TRANSMASC);
                entries.add(ModBlocks.NONBINARY);
                entries.add(ModBlocks.GENDERFLUID);
                entries.add(ModBlocks.AGENDER);
                entries.add(ModBlocks.DEMIGIRL);
                entries.add(ModBlocks.DEMIBOY);
            }))
            .build();

    private static final ItemGroup TOOL_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(TOOL_MAP.get(GemType.SAPPHIRE.getName() + Constants.PICKAXE_SUFFIX)))
            .displayName(Text.translatable("itemgroup.crystallised.tools"))
            .entries((context, entries) -> {
                String name;
                for(GemType type: GemType.MAP.values()) {
                    name = type.getName();
                    entries.add(new ItemStack(TOOL_MAP.get(name + Constants.PICKAXE_SUFFIX)));
                    entries.add(new ItemStack(TOOL_MAP.get(name + Constants.AXE_SUFFIX)));
                    entries.add(new ItemStack(TOOL_MAP.get(name + Constants.SHOVEL_SUFFIX)));
                    entries.add(new ItemStack(TOOL_MAP.get(name + Constants.SWORD_SUFFIX)));
                    entries.add(new ItemStack(TOOL_MAP.get(name + Constants.HOE_SUFFIX)));
                }
            })
            .build();

    private static final ItemGroup ACCESSORY_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(
                    ModItems.accessoryItemMap.get(
                            GemType.SAPPHIRE.getName() + MetalBase.IRON.getItemSuffix() + Constants.BRACELET_SUFFIX)
            ))
            .displayName(Text.translatable("itemgroup.crystallised.accessories"))
            .entries((context, entries) -> ModItems.accessoryItemMap.forEach(
                    (s, item) -> entries.add(item)
            ))
            .build();
}
