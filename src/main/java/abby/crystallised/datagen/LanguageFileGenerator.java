package abby.crystallised.datagen;

import abby.crystallised.Constants;
import abby.crystallised.Utility;
import abby.crystallised.blocks.ModBlocks;
import abby.crystallised.gems.GemType;
import abby.crystallised.items.ModItems;
import abby.crystallised.items.jewelry.MetalBase;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class LanguageFileGenerator extends FabricLanguageProvider {
    private TranslationBuilder translationBuilder;
    public LanguageFileGenerator(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {
        this.translationBuilder = translationBuilder;

        GemType.forEach((name, gemType) -> {
            String displayName = gemType.getDisplayName();

            addTranslation(ModItems.gemItemMap.get(name), "Cut " + gemType.getDisplayName());
            addTranslation(ModItems.rawGemItemMap.get(name + Constants.RAW_SUFFIX), "Raw " + gemType.getDisplayName());

            addTranslation(ModItems.gemToolMap.get(name + Constants.PICKAXE_SUFFIX), displayName + " Pickaxe");
            addTranslation(ModItems.gemToolMap.get(name + Constants.AXE_SUFFIX), displayName + " Axe");
            addTranslation(ModItems.gemToolMap.get(name + Constants.SHOVEL_SUFFIX), displayName + " Shovel");
            addTranslation(ModItems.gemToolMap.get(name + Constants.SWORD_SUFFIX), displayName + " Sword");

            for (MetalBase metal : MetalBase.values()) {
                addTranslation(ModItems.accessoryItemMap.get(name + metal.getItemSuffix() + Constants.BRACELET_SUFFIX), displayName + " Bracelet");
            }

            addTranslation(ModBlocks.blockMap.get(name + Constants.BLOCK_SUFFIX), "Block of " + displayName);
            addTranslation(ModBlocks.blockMap.get(name + Constants.ORE_SUFFIX), displayName + " Ore");
            addTranslation(ModBlocks.blockMap.get(name + Constants.CORE_SUFFIX), displayName + " Core");
            addTranslation(ModBlocks.blockMap.get(name + Constants.LAMP_SUFFIX), displayName + " Lamp");
            addTranslation(ModBlocks.blockMap.get(name + Constants.INVERTED_LAMP_SUFFIX), displayName + " Inverted Lamp");
        });

        addTranslation(ModBlocks.blockMap.get("gem_cutter"), "Gem Cutter");

        addTranslation(ModItems.basicItemMap.get("obsidian_shard"), "Obsidian Shard");
        addTranslation(ModItems.basicItemMap.get("tool_rod"), "Obsidian Plated Iron Rod");

        translationBuilder.add(
                Registries.ITEM_GROUP.getKey(
                        Registries.ITEM_GROUP.get(Utility.identifier("general_group"))).get(),
                "Crystallised General"
        );
        translationBuilder.add(
                Registries.ITEM_GROUP.getKey(
                        Registries.ITEM_GROUP.get(Utility.identifier("tool_group"))).get(),
                "Crystallised Tools"
        );

        translationBuilder.add(
                "death.attack.eat_rock",
                "Just because Abigail does it, doesn't mean %1$s can eat rocks"
        );
        translationBuilder.add(
                "death.attack.moonfall",
                "%1$s succumbed to the moon"
        );
    }

    private void addTranslation(Item item, String displayName) {
        translationBuilder.add(item, displayName);
    }

    private void addTranslation(Block block, String displayName) {
        translationBuilder.add(block, displayName);
    }
}
