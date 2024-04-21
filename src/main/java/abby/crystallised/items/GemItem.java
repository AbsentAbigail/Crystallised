package abby.crystallised.items;

import abby.crystallised.gems.implementation.BaseImplementation;
import abby.crystallised.gems.GemType;
import abby.crystallised.miscellaneous.ModDamageSources;
import com.mojang.datafixers.util.Pair;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class GemItem extends Item {
    private GemType type;

    public GemItem(GemType type) {
        super(buildSettings(type));
        this.type = type;
    }

    public GemType getGemType() {
        return type;
    }

    private static Settings buildSettings(GemType gemType) {
        BaseImplementation implementation = gemType.getImplementation();
        Settings settings = new Item.Settings();

        FoodComponent.Builder foodBuilder =
                new FoodComponent.Builder()
                        .alwaysEdible()
                        .nutrition(implementation.getNutrition())
                        .saturationModifier(implementation.getSaturation());
        if (implementation.isSnack())
            foodBuilder.snack();
        for (Pair<StatusEffectInstance, Float> pair : implementation.getStatusEffectsWhenEaten()) {
            foodBuilder.statusEffect(pair.getFirst(), pair.getSecond());
        }
        settings.food(foodBuilder.build());

        return settings;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (type.getHardness() > 6) {
            World world = context.getWorld();
            BlockPos pos = context.getBlockPos();
            BlockState blockState = world.getBlockState(pos);
            Block b = blockState.getBlock();
            PlayerEntity player = context.getPlayer();

            if (b == Blocks.OBSIDIAN) {
                Random random = new Random();
                if (random.nextInt(3) == 0) {
                    world.breakBlock(pos, false);
                }
                if (player != null)
                    player.giveItemStack(new ItemStack(ModItems.basicItemMap.get("obsidian_shard"), 1));
            }
        }

        return super.useOnBlock(context);
    }


    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        ItemStack itemStack = super.finishUsing(stack, world, user);
        user.damage(ModDamageSources.of(world, ModDamageSources.EAT_ROCK), 1.0F);
        return type.getImplementation().finishUsing(itemStack, world, user);
    }
}
