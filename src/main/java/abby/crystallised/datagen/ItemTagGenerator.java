package abby.crystallised.datagen;

import abby.crystallised.Constants;
import abby.crystallised.gems.GemType;
import abby.crystallised.items.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ItemTagGenerator extends FabricTagProvider.ItemTagProvider {
    public ItemTagGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        GemType.forEach((name, type) -> {
            getOrCreateTagBuilder(ItemTags.BEACON_PAYMENT_ITEMS).add(ModItems.gemItemMap.get(name));

            Item pickaxe = ModItems.gemToolMap.get(name + Constants.PICKAXE_SUFFIX);
            getOrCreateTagBuilder(ItemTags.PICKAXES).add(pickaxe);
            getOrCreateTagBuilder(ItemTags.MINING_LOOT_ENCHANTABLE).add(pickaxe);

            Item axe = ModItems.gemToolMap.get(name + Constants.AXE_SUFFIX);
            getOrCreateTagBuilder(ItemTags.AXES).add(axe);
            getOrCreateTagBuilder(ItemTags.MINING_LOOT_ENCHANTABLE).add(axe);

            Item shovel = ModItems.gemToolMap.get(name + Constants.SHOVEL_SUFFIX);
            getOrCreateTagBuilder(ItemTags.SHOVELS).add(shovel);
            getOrCreateTagBuilder(ItemTags.MINING_LOOT_ENCHANTABLE).add(shovel);

            Item sword = ModItems.gemToolMap.get(name + Constants.SWORD_SUFFIX);
            getOrCreateTagBuilder(ItemTags.SWORDS).add(sword);
            getOrCreateTagBuilder(ItemTags.SWORD_ENCHANTABLE).add(sword);
        });
    }
}
