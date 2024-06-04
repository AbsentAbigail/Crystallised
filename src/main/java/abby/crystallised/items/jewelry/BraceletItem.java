package abby.crystallised.items.jewelry;

import abby.crystallised.gems.GemType;
import abby.crystallised.gems.implementation.GemImplementation;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BraceletItem extends JewelryItem {
    public BraceletItem(GemType type) {
        super(type, new Item.Settings());
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
        if (world.isClient)
            return;
        if (entity.isLiving()) {
            GemImplementation implementation = gemType.getImplementation();
            implementation.braceletInventoryTick(stack, world, entity, slot, selected);
        }
    }
}
