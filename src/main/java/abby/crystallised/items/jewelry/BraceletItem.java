package abby.crystallised.items.jewelry;

import abby.crystallised.gems.GemType;
import abby.crystallised.gems.implementation.BaseImplementation;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BraceletItem extends Item {
    protected final GemType gemType;

    public BraceletItem(GemType type) {
        super(new Item.Settings());
        this.gemType = type;
    }

    public GemType getGemType() {
        return gemType;
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
        if (world.isClient)
            return;
        if (entity.isLiving()) {
            BaseImplementation implementation = gemType.getImplementation();
            implementation.tickInventory(stack, world, entity, slot, selected);
        }
    }
}
