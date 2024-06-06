package abby.crystallised.datagen;

import abby.crystallised.Constants;
import abby.crystallised.Utility;
import abby.crystallised.blocks.ModBlocks;
import abby.crystallised.gems.GemType;
import abby.crystallised.items.ModItems;
import abby.crystallised.items.jewelry.MetalBase;
import abby.crystallised.miscellaneous.ModItemTags;
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

            addTranslation(ModItems.gemItemMap.get(name), gemType.getDisplayName());

            addTranslation(ModItems.gemToolMap.get(
                    name + Constants.PICKAXE_SUFFIX),
                    displayName + " Pickaxe"
            );
            addTranslation(ModItems.gemToolMap.get(name + Constants.AXE_SUFFIX), displayName + " Axe");
            addTranslation(ModItems.gemToolMap.get(name + Constants.SHOVEL_SUFFIX), displayName + " Shovel");
            addTranslation(ModItems.gemToolMap.get(name + Constants.SWORD_SUFFIX), displayName + " Sword");

            for (MetalBase metal : MetalBase.values()) {
                addTranslation(
                        ModItems.accessoryItemMap.get(name + metal.getItemSuffix() + Constants.BRACELET_SUFFIX),
                        displayName + " " + metal.getDisplayName() + " Bracelet"
                );
                addTranslation(
                        ModItems.accessoryItemMap.get(name + metal.getItemSuffix() + Constants.NECKLACE_SUFFIX),
                        displayName + " " + metal.getDisplayName() + " Necklace"
                );
                addTranslation(
                        ModItems.accessoryItemMap.get(name + metal.getItemSuffix() + Constants.KEY_SUFFIX),
                        displayName + " " + metal.getDisplayName() + " Key"
                );
            }

            addTranslation(ModBlocks.blockMap.get(name + Constants.BLOCK_SUFFIX), "Block of " + displayName);
            addTranslation(ModBlocks.blockMap.get(name + Constants.ORE_SUFFIX), displayName + " Ore");
            addTranslation(ModBlocks.blockMap.get(
                    name + Constants.ORE_DEEPSLATE_SUFFIX),
                    displayName + " Deepslate Ore"
            );
            addTranslation(ModBlocks.blockMap.get(name + Constants.CORE_SUFFIX), displayName + " Core");
            addTranslation(ModBlocks.blockMap.get(name + Constants.LAMP_SUFFIX), displayName + " Lamp");
            addTranslation(ModBlocks.blockMap.get(
                    name + Constants.INVERTED_LAMP_SUFFIX),
                    displayName + " Inverted Lamp"
            );
        });

        for (MetalBase metal : MetalBase.values()) {
            addTranslation(
                    ModItems.accessoryItemMap.get(metal.getLowercaseName() + Constants.BRACELET_SUFFIX),
                    metal.getDisplayName() + " Bracelet"
            );
            addTranslation(
                    ModItems.accessoryItemMap.get(metal.getLowercaseName() + Constants.NECKLACE_SUFFIX),
                    metal.getDisplayName() + " Necklace"
            );
            addTranslation(
                    ModItems.accessoryItemMap.get(metal.getLowercaseName() + Constants.KEY_SUFFIX),
                    metal.getDisplayName() + " Key"
            );
        }

        addTranslation(ModBlocks.blockMap.get("gem_cutter"), "Gem Cutter");

        addTranslation(ModItems.basicItemMap.get("obsidian_shard"), "Obsidian Shard");
        addTranslation(ModItems.basicItemMap.get("tool_rod"), "Obsidian Plated Iron Rod");

        addTranslation(ModBlocks.PRIDE, "Pride Block");
        addTranslation(ModBlocks.LESBIAN, "Lesbian Pride Block");
        addTranslation(ModBlocks.VINCIAN, "Vincian Pride Block");
        addTranslation(ModBlocks.BISEXUAL, "Bisexual Pride Block");
        addTranslation(ModBlocks.PANSEXUAL, "Pansexual Pride Block");
        addTranslation(ModBlocks.ASEXUAL, "Asexual Pride Block");
        addTranslation(ModBlocks.AROMANTIC, "Aromantic Pride Block");
        addTranslation(ModBlocks.TRANS, "Trans Pride Block");
        addTranslation(ModBlocks.NONBINARY, "Nonbinary Pride Block");
        addTranslation(ModBlocks.GENDERFLUID, "Genderfluid Pride Block");
        addTranslation(ModBlocks.AGENDER, "Agender Pride Block");
        addTranslation(ModBlocks.DEMIGIRL, "Demigirl Pride Block");
        addTranslation(ModBlocks.DEMIBOY, "Demiboy Pride Block");
        addTranslation(ModBlocks.ITALIAN, "Italian Pride Block");
        addTranslation(ModBlocks.PROGRESS, "Progress Pride Block");
        addTranslation(ModBlocks.TRANSFEM, "Transfem Pride Block");
        addTranslation(ModBlocks.TRANSMASC, "Transmasc Pride Block");

        translationBuilder.add(
                Registries.ITEM_GROUP.getKey(
                        Registries.ITEM_GROUP.get(Utility.identifier(Constants.GENERAL_GROUP_ID))).orElseThrow(),
                "Crystallised General"
        );
        translationBuilder.add(
                Registries.ITEM_GROUP.getKey(
                        Registries.ITEM_GROUP.get(Utility.identifier(Constants.TOOL_GROUP_ID))).orElseThrow(),
                "Crystallised Tools"
        );
        translationBuilder.add(
                Registries.ITEM_GROUP.getKey(
                        Registries.ITEM_GROUP.get(Utility.identifier(Constants.ACCESSORY_GROUP_ID))).orElseThrow(),
                "Crystallised Accessories"
        );

        translationBuilder.add(
                "death.attack.eat_rock",
                "Just because Abigail does it, doesn't mean %1$s can eat rocks"
        );
        translationBuilder.add(
                "death.attack.moonfall",
                "%1$s succumbed to the moon"
        );

        translationBuilder.add(
                "trinkets.slot.hand.bracelet",
                "Bracelet"
        );
        translationBuilder.add(
                "trinkets.slot.offhand.bracelet",
                "Offhand Bracelet"
        );

        translationBuilder.add(
                ModItemTags.BRACELET,
                "Bracelets"
        );
        translationBuilder.add(
                ModItemTags.OFFHAND_BRACELET,
                "Offhand Bracelets"
        );
        translationBuilder.add(
                ModItemTags.NECKLACE,
                "Necklaces"
        );
        translationBuilder.add(
                ModItemTags.GEMS,
                "Crystallised Gems"
        );
    }

    private void addTranslation(Item item, String displayName) {
        translationBuilder.add(item, displayName);
    }

    private void addTranslation(Block block, String displayName) {
        translationBuilder.add(block, displayName);
    }
}
