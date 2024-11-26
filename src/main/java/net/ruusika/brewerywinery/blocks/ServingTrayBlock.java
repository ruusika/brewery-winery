package net.ruusika.brewerywinery.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.ruusika.brewerywinery.blocks.entities.ServingTrayBlockEntity;
import net.ruusika.brewerywinery.items.BeverageItem;

public class ServingTrayBlock extends Block {
    public ServingTrayBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack stack = player.getStackInHand(hand);
        if (stack.getItem().isFood() || stack.getItem() instanceof BeverageItem) {
            if (world.getBlockEntity(pos) instanceof ServingTrayBlockEntity servingTrayBlockEntity) {
                servingTrayBlockEntity.getInventory();
            }
            return ActionResult.SUCCESS;
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }
}
