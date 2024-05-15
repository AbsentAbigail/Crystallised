package abby.crystallised.blocks;

import abby.crystallised.gems.GemType;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

public class GemOre extends Block {

    private final GemType type;

    public GemOre(GemType type) {
        super(getSettingsFromType(type));
        this.type = type;
        this.setDefaultState(this.stateManager.getDefaultState());
    }

    public static Settings getSettingsFromType(GemType type) {
//        Settings settings = Settings.copy(Blocks.STONE);
//        double h = type.getHardness();
//        int level = h >= 9 ? 3 :
//                (h > 5 ? 2 :
//                        (h > 1 ? 1 :
//                                0));
//        settings.strength(h * 0.5F, h * 0.6F).requiresTool();
        return Settings.copy(Blocks.STONE);
    }

    public GemType getGemType() {
        return type;
    }
}
