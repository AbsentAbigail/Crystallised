package abby.crystallised;

import abby.crystallised.gems.GemType;
import abby.crystallised.items.ModItems;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class Utility {

    public static final String MODID = "crystallised";
    public static final String MODNAME = "Crystallised";

    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

    public static Identifier identifier(String path) {
        return new Identifier(MODID, path);
    }
}
