package abby.crystallised.items;

import abby.crystallised.gems.GemType;
import net.minecraft.item.Item;

public class RawGemItem extends Item {
    private final GemType type;

    public RawGemItem(GemType type) {
        super(buildSettings(type));
        this.type = type;
    }

    public GemType getGemType() {
        return type;
    }

    private static Settings buildSettings(GemType gemType) {
        return new Settings();
    }
}
