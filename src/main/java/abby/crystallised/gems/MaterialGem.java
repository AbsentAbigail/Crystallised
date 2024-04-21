package abby.crystallised.gems;

import abby.crystallised.Utility;
import abby.crystallised.items.ModItems;
import net.minecraft.block.Block;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.TagKey;

public class MaterialGem implements ToolMaterial {
    private final GemType type;

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
        return switch (type.getName().toUpperCase()) {
            case "AMBER" -> 2.0F;
            case "PETALITE" -> 8.0F;
            case "AZURITE", "PHOSPHOPHYLLITE" -> 5F;
            case "AMETHYST", "PERIDOT" -> 8.5F;
            case "RUBY", "SAPPHIRE" -> 7.2F;
            default -> 8.0F;
        };
    }

    @Override
    public float getAttackDamage() {
        return switch (type.getName().toUpperCase()) {
            case "AMBER" -> 2.0F;
            case "PETALITE" -> 4.5F;
            case "AZURITE", "PHOSPHOPHYLLITE" -> 6.0F;
            case "AMETHYST", "PERIDOT" -> 2.1F;
            case "RUBY", "SAPPHIRE" -> 2.7F;
            default -> 3.0F;
        };
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
