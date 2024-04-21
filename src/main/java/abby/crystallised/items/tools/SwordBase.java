package abby.crystallised.items.tools;

import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

public class SwordBase extends SwordItem {
    public ToolMaterial material;

    public SwordBase(ToolMaterial material, int attackDamage, float attackSpeed) {
        super(material, new Item.Settings());
        this.material = material;
    }

    public SwordBase(ToolMaterial material) {
        this(material, 3, -2.4F);
    }
}
