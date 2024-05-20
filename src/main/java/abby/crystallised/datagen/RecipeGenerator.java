package abby.crystallised.datagen;

import abby.crystallised.Constants;
import abby.crystallised.Utility;
import abby.crystallised.gems.GemType;
import abby.crystallised.items.ModItems;
import abby.crystallised.items.jewelry.MetalBase;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.RecipeProvider;
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
                .criterion(FabricRecipeProvider.hasItem(ModItems.OBSIDIAN_SHARD),
                        FabricRecipeProvider.conditionsFromItem(ModItems.OBSIDIAN_SHARD))
                .offerTo(exporter);

        GemType.forEach((s, gemType) -> {
            String raw_name = gemType.getName() + Constants.RAW_SUFFIX;
            String cut_name = gemType.getName();
            Item raw = ModItems.rawGemItemMap.get(raw_name);
            Item cut = ModItems.gemItemMap.get(cut_name);

            RecipeProvider.offerStonecuttingRecipe(exporter, RecipeCategory.MISC, cut, raw);
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
                    .group("crystallised:bracelets")
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
                    .group("crystallised:necklaces")
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
                    .group("crystallised:keys")
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
                        .group("crystallised:bracelets")
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
                        .group("crystallised:necklaces")
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
                        .group("crystallised:keys")
                        .offerTo(exporter);
            });
        }
    }
}
