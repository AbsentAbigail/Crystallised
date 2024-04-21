package abby.crystallised.blocks;

import abby.crystallised.gems.GemType;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

public class GemBlock extends Block {
    private GemType type;

    public GemBlock(GemType type) {
        super(getSettingsFromType(type));
        this.type = type;
    }

    public GemType getGemType() {
        return type;
    }

    public static Settings getSettingsFromType(GemType type) {

        Settings settings = Settings.copy(Blocks.IRON_BLOCK);

        double h = type.getHardness();

//        int level = h >= 8 ? 3 :
//                (h > 6 ? 2 :
//                        (h > 4 ? 1 :
//                                0));
//        if(h > 2)
//            settings.strength(h * 0.5F, h * 0.6F).breakByTool(FabricToolTags.PICKAXES, level);
//        else
//            settings.strength(h * 0.5F, h * 0.6F).breakByHand(true);
        //settings.strength(h * 0.5F, h * 0.6F).requiresTool();

        return settings;
    }
}
