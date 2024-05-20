package abby.crystallised.items.jewelry;

import abby.crystallised.gems.GemType;
import net.minecraft.item.Item;

public class JewelryItem extends Item {
    protected final GemType gemType;

    public JewelryItem(GemType type, Item.Settings settings) {
        super(settings.maxCount(1));
        this.gemType = type;
    }

    public GemType getGemType() {
        return gemType;
    }
}
