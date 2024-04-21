package abby.crystallised.gems;

import abby.crystallised.Utility;
import abby.crystallised.items.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.TagKey;

public class MaterialGem implements ToolMaterial {
    private GemType type;

    public MaterialGem(GemType type) {
        this.type = type;
    }

    public int getDurability() {
        return (int)(type.getHardness() * 156F);
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 1;
    }

    public float getMiningSpeed() {
        switch(type.getName().toUpperCase()) {
            case "AMBER":
                return 2.0F;
            case "PETALITE":
                return 8.0F;
            case "AZURITE":
            case "PHOSPHOPHYLLITE":
                return 5F;
            case "AMETHYST":
            case "PERIDOT":
                return 8.5F;
            case "RUBY":
            case "SAPPHIRE":
                return 7.2F;
            default:
                return 8.0F;
        }
    }

    @Override
    public float getAttackDamage() {
        switch(type.getName().toUpperCase()) {
            case "AMBER":
                return 2.0F;
            case "PETALITE":
                return 4.5F;
            case "AZURITE":
            case "PHOSPHOPHYLLITE":
                return 6.0F;
            case "AMETHYST":
            case "PERIDOT":
                return 2.1F;
            case "RUBY":
            case "SAPPHIRE":
                return 2.7F;
            default:
                return 3.0F;
        }
    }

    @Override
    public TagKey<Block> getInverseTag() {
        return TagKey.of(Registries.BLOCK.getKey(), Utility.identifier("inverse_tag"));
    }

//    @Override
//    public int getMiningLevel() {
//        double h = type.getHardness();
//
//        return h >= 8 ? 3 :
//                (h > 6 ? 2 :
//                        (h > 4 ? 1 :
//                                0));
//    }

    @Override
    public int getEnchantability() {
        return 30;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(ModItems.gemItemMap.get(type.getName()));
    }

    public GemType getGemType() {
        return type;
    }
}
