package net.ruusika.brewerywinery.blocks.entities;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.ruusika.brewerywinery.init.BreweryWineryBlockEntities;
import org.jetbrains.annotations.Nullable;

public class ServingTrayBlockEntity extends BlockEntity {

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(4, ItemStack.EMPTY);

    public ServingTrayBlockEntity( BlockPos pos, BlockState state) {
        super(BreweryWineryBlockEntities.SERVING_TRAY, pos, state);
    }

    public DefaultedList<ItemStack> getInventory() {
        return inventory;
    }

    public boolean isFull() {
        boolean isFull = true;
        for (ItemStack entry : inventory) {
            if (entry.isEmpty()) {
                isFull = false;
                break;
            }
        }
        return isFull;
    }

    public boolean addToTray(ItemStack stack) {
        boolean changed = false;
        for (int i = 0; i < inventory.size(); i++) {
            ItemStack entry = inventory.get(i);
            if (entry.isEmpty()) {
                inventory.set(i, stack.copy());
                changed = true;
                break;
            }
        }
        return changed;
    }

    @Nullable
    public ItemStack removeFromTray() {
        ItemStack removedStack = null;
        for (int i = inventory.size()-1; i >= 0; i--) {
            ItemStack entry = inventory.get(i);
            if (!entry.isEmpty()) {
                removedStack = entry.copy();
                inventory.set(i, ItemStack.EMPTY);
                break;
            }
        }
        return removedStack;
    }


    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
    }
}
