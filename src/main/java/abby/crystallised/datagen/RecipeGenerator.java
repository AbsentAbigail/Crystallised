package abby.crystallised.datagen;

import abby.crystallised.Constants;
import abby.crystallised.blocks.ModBlocks;
import abby.crystallised.gems.GemType;
import abby.crystallised.items.ModItems;
import abby.crystallised.items.jewelry.MetalBase;
import abby.crystallised.miscellaneous.ModItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class RecipeGenerator extends FabricRecipeProvider {
    public RecipeGenerator(FabricDataOutput generator, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(generator, registryLookup);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.TOOL_ROD)
                .pattern("oio")
                .pattern("oio")
                .pattern("oio")
                .input('o', ModItems.OBSIDIAN_SHARD)
                .input('i', Items.IRON_NUGGET)
                .criterion(FabricRecipeProvider.hasItem(Items.IRON_NUGGET),
                        FabricRecipeProvider.conditionsFromItem(Items.IRON_NUGGET))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.OBSIDIAN_SHARD, 16)
                .input(Items.OBSIDIAN)
                .input(ModItemTags.GEMS)
                .criterion(FabricRecipeProvider.hasItem(Items.OBSIDIAN),
                        FabricRecipeProvider.conditionsFromItem(Items.OBSIDIAN))
                .offerTo(exporter);

        GemType.forEach((name, gemType) -> {
//            Item rawGem = ModItems.rawGemItemMap.get(name + Constants.RAW_SUFFIX);
            Item cutGem = ModItems.gemItemMap.get(name);
            Block gemBlock = ModBlocks.blockMap.get(name + Constants.BLOCK_SUFFIX);
            Block lampBlock = ModBlocks.blockMap.get(name + Constants.LAMP_SUFFIX);

//            RecipeProvider.offerStonecuttingRecipe(exporter, RecipeCategory.MISC, cutGem, rawGem);

            ShapedRecipeJsonBuilder.create(RecipeCategory.MISC,
                            ModBlocks.blockMap.get(name + Constants.CORE_SUFFIX))
                    .pattern("omo")
                    .pattern("mgm")
                    .pattern("omo")
                    .input('g', gemBlock)
                    .input('m', ModItems.TOOL_ROD)
                    .input('o', Items.OBSIDIAN)
                    .criterion(FabricRecipeProvider.hasItem(gemBlock),
                            FabricRecipeProvider.conditionsFromItem(gemBlock))
                    .criterion(FabricRecipeProvider.hasItem(ModItems.TOOL_ROD),
                            FabricRecipeProvider.conditionsFromItem(ModItems.TOOL_ROD))
                    .group(Constants.MODID + ":cores")
                    .offerTo(exporter);

            ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, gemBlock)
                    .pattern("aaa")
                    .pattern("aaa")
                    .pattern("aaa")
                    .input('a', cutGem)
                    .criterion(FabricRecipeProvider.hasItem(cutGem), FabricRecipeProvider.conditionsFromItem(cutGem))
                    .offerTo(exporter);

            ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, cutGem, 9)
                    .input(gemBlock)
                    .criterion(FabricRecipeProvider.hasItem(gemBlock), FabricRecipeProvider.conditionsFromItem(gemBlock))
                    .offerTo(exporter);

            ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, lampBlock)
                    .pattern(" g ")
                    .pattern("glg")
                    .pattern(" g ")
                    .input('g', cutGem)
                    .input('l', Blocks.REDSTONE_LAMP)
                    .criterion(FabricRecipeProvider.hasItem(Blocks.REDSTONE_LAMP),
                            FabricRecipeProvider.conditionsFromItem(Blocks.REDSTONE_LAMP))
                    .criterion(FabricRecipeProvider.hasItem(cutGem),
                            FabricRecipeProvider.conditionsFromItem(cutGem))
                    .group(Constants.MODID + ":lamps")
                    .offerTo(exporter);
            ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE,
                    ModBlocks.blockMap.get(name + Constants.INVERTED_LAMP_SUFFIX))
                    .input(lampBlock)
                    .input(Blocks.REDSTONE_TORCH)
                    .criterion(FabricRecipeProvider.hasItem(lampBlock),
                            FabricRecipeProvider.conditionsFromItem(lampBlock))
                    .group(Constants.MODID + ":lamps")
                    .offerTo(exporter);

            ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS,
                    ModItems.gemToolMap.get(name + Constants.PICKAXE_SUFFIX))
                    .pattern("ggg")
                    .pattern(" r ")
                    .pattern(" r ")
                    .input('g', cutGem)
                    .input('r', ModItems.TOOL_ROD)
                    .criterion(FabricRecipeProvider.hasItem(cutGem),
                            FabricRecipeProvider.conditionsFromItem(cutGem))
                    .offerTo(exporter);
            ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS,
                            ModItems.gemToolMap.get(name + Constants.AXE_SUFFIX))
                    .pattern("gg ")
                    .pattern("gr ")
                    .pattern(" r ")
                    .input('g', cutGem)
                    .input('r', ModItems.TOOL_ROD)
                    .criterion(FabricRecipeProvider.hasItem(cutGem),
                            FabricRecipeProvider.conditionsFromItem(cutGem))
                    .offerTo(exporter);
            ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS,
                            ModItems.gemToolMap.get(name + Constants.SHOVEL_SUFFIX))
                    .pattern("g")
                    .pattern("r")
                    .pattern("r")
                    .input('g', cutGem)
                    .input('r', ModItems.TOOL_ROD)
                    .criterion(FabricRecipeProvider.hasItem(cutGem),
                            FabricRecipeProvider.conditionsFromItem(cutGem))
                    .offerTo(exporter);
            ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS,
                            ModItems.gemToolMap.get(name + Constants.SWORD_SUFFIX))
                    .pattern("g")
                    .pattern("g")
                    .pattern("r")
                    .input('g', cutGem)
                    .input('r', ModItems.TOOL_ROD)
                    .criterion(FabricRecipeProvider.hasItem(cutGem),
                            FabricRecipeProvider.conditionsFromItem(cutGem))
                    .offerTo(exporter);
        });

        for (MetalBase metal : MetalBase.values()) {
            ShapedRecipeJsonBuilder.create(RecipeCategory.MISC,
                            ModItems.accessoryItemMap.get(metal.getLowercaseName() + Constants.BRACELET_SUFFIX))
                    .pattern(" m ")
                    .pattern("mim")
                    .pattern(" m ")
                    .input('m', metal.getMaterial())
                    .input('i', Items.IRON_NUGGET)
                    .criterion(FabricRecipeProvider.hasItem(metal.getMaterial()),
                            FabricRecipeProvider.conditionsFromItem(metal.getMaterial()))
                    .group(Constants.MODID + ":bracelets")
                    .offerTo(exporter);

            ShapedRecipeJsonBuilder.create(RecipeCategory.MISC,
                            ModItems.accessoryItemMap.get(metal.getLowercaseName() + Constants.NECKLACE_SUFFIX))
                    .pattern(" m ")
                    .pattern("mcm")
                    .pattern(" m ")
                    .input('m', metal.getMaterial())
                    .input('c', Items.CHAIN)
                    .criterion(FabricRecipeProvider.hasItem(metal.getMaterial()),
                            FabricRecipeProvider.conditionsFromItem(metal.getMaterial()))
                    .group(Constants.MODID + ":necklaces")
                    .offerTo(exporter);

            ShapedRecipeJsonBuilder.create(RecipeCategory.MISC,
                            ModItems.accessoryItemMap.get(metal.getLowercaseName() + Constants.KEY_SUFFIX))
                    .pattern("mm ")
                    .pattern("im ")
                    .pattern(" m ")
                    .input('m', metal.getMaterial())
                    .input('i', Items.IRON_NUGGET)
                    .criterion(FabricRecipeProvider.hasItem(metal.getMaterial()),
                            FabricRecipeProvider.conditionsFromItem(metal.getMaterial()))
                    .group(Constants.MODID + ":keys")
                    .offerTo(exporter);
        }

        registerAccessories(exporter);
    }

    private void registerAccessories(RecipeExporter exporter) {
        for (MetalBase metal : MetalBase.values()) {
            GemType.forEach((gemName, gemType) -> {
                ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC,
                        ModItems.accessoryItemMap.get(
                                gemName + metal.getItemSuffix() + Constants.BRACELET_SUFFIX
                        ))
                        .input(ModItems.accessoryItemMap.get(
                                metal.getLowercaseName() + Constants.BRACELET_SUFFIX
                        ))
                        .input(ModItems.gemItemMap.get(
                                gemName
                        ))
                        .criterion(FabricRecipeProvider.hasItem(metal.getMaterial()),
                                FabricRecipeProvider.conditionsFromItem(metal.getMaterial()))
                        .group(Constants.MODID + ":bracelets")
                        .offerTo(exporter);

                ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC,
                                ModItems.accessoryItemMap.get(
                                        gemName + metal.getItemSuffix() + Constants.NECKLACE_SUFFIX
                                ))
                        .input(ModItems.accessoryItemMap.get(
                                metal.getLowercaseName() + Constants.NECKLACE_SUFFIX
                        ))
                        .input(ModItems.gemItemMap.get(
                                gemName
                        ))
                        .criterion(FabricRecipeProvider.hasItem(metal.getMaterial()),
                                FabricRecipeProvider.conditionsFromItem(metal.getMaterial()))
                        .group(Constants.MODID + ":necklaces")
                        .offerTo(exporter);

                ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC,
                                ModItems.accessoryItemMap.get(
                                        gemName + metal.getItemSuffix() + Constants.KEY_SUFFIX
                                ))
                        .input(ModItems.accessoryItemMap.get(
                                metal.getLowercaseName() + Constants.KEY_SUFFIX
                        ))
                        .input(ModItems.gemItemMap.get(
                                gemName
                        ))
                        .criterion(FabricRecipeProvider.hasItem(metal.getMaterial()),
                                FabricRecipeProvider.conditionsFromItem(metal.getMaterial()))
                        .group(Constants.MODID + ":keys")
                        .offerTo(exporter);
            });
        }
    }
}
