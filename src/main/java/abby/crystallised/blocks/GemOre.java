package abby.crystallised.blocks;

import abby.crystallised.gems.GemType;
import net.minecraft.block.Blocks;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class GemOre extends ExperienceDroppingBlock {

    private final GemType type;

    public GemOre(GemType type) {
        super(UniformIntProvider.create(3, 7), getSettingsFromType(type));
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
