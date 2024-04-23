package abby.crystallised.items.jewelry;

import abby.crystallised.gems.GemType;
import abby.crystallised.gems.implementation.BaseImplementation;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class RingItem extends Item {
    protected final GemType gemType;

    public RingItem(GemType type) {
        super(new Item.Settings());
        this.gemType = type;
    }

    public GemType getGemType() {
        return gemType;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient) {
            ItemStack itemStack = user.getStackInHand(hand);
            SoundEvent soundEvent = SoundEvents.BLOCK_BEACON_ACTIVATE;
            SoundCategory soundCategory = SoundCategory.PLAYERS;

            world.playSound(null, user.getX(), user.getY(), user.getZ(), soundEvent, soundCategory);
        }
        return super.use(world, user, hand);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
        if (world.isClient)
            return;
        if (entity.isLiving() && (slot == 0 || slot == 8)) {
            BaseImplementation implementation = gemType.getImplementation();
            implementation.tickInventory(stack, world, entity, slot, selected);
        }
    }
}
