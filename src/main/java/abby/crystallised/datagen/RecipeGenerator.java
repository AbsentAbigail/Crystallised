package abby.crystallised.datagen;

import abby.crystallised.gems.GemType;
import abby.crystallised.items.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.RecipeProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
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
            String raw_name = gemType.getName() + "_raw";
            String cut_name = gemType.getName();
            Item raw = ModItems.rawGemItemMap.get(raw_name);
            Item cut = ModItems.gemItemMap.get(cut_name);

            RecipeProvider.offerStonecuttingRecipe(exporter, RecipeCategory.MISC, cut, raw);
        });


    }
}
