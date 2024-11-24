package net.ruusika.brewerywinery.blocks.entities;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.ruusika.brewerywinery.init.BreweryWineryBlockEntities;
import net.ruusika.brewerywinery.util.NbtKeys;

public class KegBlockEntity extends BlockEntity {

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(4 , ItemStack.EMPTY);
    private int progressTick;
    private int tick;

    public KegBlockEntity(BlockPos pos, BlockState state) {
        super(BreweryWineryBlockEntities.KEG, pos, state);
        progressTick = 0;
        tick = 0;
    }

    @SuppressWarnings("unused")
    public static void tick(World world, BlockPos blockPos, BlockState blockState, KegBlockEntity blockEntity) {
        blockEntity.tick ++;
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
        progressTick = nbt.getInt(NbtKeys.PROGRESS);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt(NbtKeys.PROGRESS, progressTick);
    }

}
