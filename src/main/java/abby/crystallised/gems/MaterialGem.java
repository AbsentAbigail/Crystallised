package abby.crystallised.gems;

import abby.crystallised.items.ModItems;
import net.minecraft.block.Block;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.BlockTags;
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
        double h = type.getHardness();

        return h >= 8 ? BlockTags.INCORRECT_FOR_DIAMOND_TOOL :
                (h >= 6 ? BlockTags.INCORRECT_FOR_IRON_TOOL :
                        (h >= 4 ? BlockTags.INCORRECT_FOR_STONE_TOOL :
                                BlockTags.INCORRECT_FOR_WOODEN_TOOL));
    }

    @Override
    public int getEnchantability() {
        return type.getCapacity();
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(ModItems.gemItemMap.get(type.getName()));
    }

    public GemType getGemType() {
        return type;
    }
}
