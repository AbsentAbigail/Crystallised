package abby.crystallised.items.tools;

import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;

public class AxeBase extends AxeItem {
    public ToolMaterial material;
    public AxeBase(ToolMaterial material, float attackDamage, float attackSpeed) {
        super(material, new Item.Settings());
        this.material = material;
    }
    public AxeBase(ToolMaterial material) {
        this(material, 6.0F, -2.8F);
    }
}
