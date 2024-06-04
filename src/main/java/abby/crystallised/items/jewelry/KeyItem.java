package abby.crystallised.items.jewelry;

import abby.crystallised.gems.GemType;
import abby.crystallised.gems.implementation.GemImplementation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;

public class KeyItem extends JewelryItem {
    public KeyItem(GemType type) {
        super(type, new Item.Settings());
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        ActionResult result = super.useOnBlock(context);
        GemImplementation implementation = gemType.getImplementation();
        return implementation.keyUseOnBlock(context, result);
    }
}
