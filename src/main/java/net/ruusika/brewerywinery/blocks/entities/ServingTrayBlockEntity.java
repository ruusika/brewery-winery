package net.ruusika.brewerywinery.blocks.entities;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;

public class ServingTrayBlockEntity extends BlockEntity {

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(4, ItemStack.EMPTY);

    public ServingTrayBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
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

    public void addToTray(ItemStack stack) {
        if (isFull()) {
            return;
        }
        for (int i = 0; i < inventory.size(); i++) {
            ItemStack entry = inventory.get(i);
            if (entry.isEmpty()) {
                inventory.set(i, stack.copy());
                break;
            }
        }
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
