package abby.crystallised.items.tools;

import net.minecraft.item.Item;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.ToolMaterial;

public class ShovelBase extends ShovelItem {
    public ToolMaterial material;
    public ShovelBase(ToolMaterial material, float attackDamage, float attackSpeed) {
        super(material, new Item.Settings());
        this.material = material;
    }
    public ShovelBase(ToolMaterial material) {
        this(material, 1.5F, -3.0F);
    }
}
