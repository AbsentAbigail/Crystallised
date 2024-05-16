package abby.crystallised.datagen;

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
            addTranslation(ModItems.rawGemItemMap.get(name + "_raw"), "Raw " + gemType.getDisplayName());

            addTranslation(ModItems.gemToolMap.get(name + "_pickaxe"), displayName + " Pickaxe");
            addTranslation(ModItems.gemToolMap.get(name + "_axe"), displayName + " Axe");
            addTranslation(ModItems.gemToolMap.get(name + "_shovel"), displayName + " Shovel");
            addTranslation(ModItems.gemToolMap.get(name + "_sword"), displayName + " Sword");

            for (MetalBase metal : MetalBase.values()) {
                addTranslation(ModItems.accessoryItemMap.get(name + metal.getItemSuffix() + "_bracelet"), displayName + " Bracelet");
            }

            addTranslation(ModBlocks.blockMap.get(name + "_block"), "Block of " + displayName);
            addTranslation(ModBlocks.blockMap.get(name + "_ore"), displayName + " Ore");
            addTranslation(ModBlocks.blockMap.get(name + "_gem_core"), displayName + " Core");
            addTranslation(ModBlocks.blockMap.get(name + "_lamp"), displayName + " Lamp");
            addTranslation(ModBlocks.blockMap.get(name + "_lamp_inverted"), displayName + " Inverted Lamp");
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
