package net.ruusika.brewerywinery.blocks;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.ruusika.brewerywinery.blocks.entities.ServingTrayBlockEntity;
import net.ruusika.brewerywinery.items.BeverageItem;
import org.jetbrains.annotations.Nullable;

public class ServingTrayBlock extends BlockWithEntity implements Waterloggable {

    //Constructor
    public ServingTrayBlock(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(Properties.WATERLOGGED, false));
    }

    //Boilerplate
    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new ServingTrayBlockEntity(pos, state);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(Properties.WATERLOGGED);
    }

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
        return getDefaultState().with(Properties.WATERLOGGED, ctx.getWorld()
                .getFluidState(ctx.getBlockPos()).isOf(Fluids.WATER));
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(Properties.WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState,
                                                WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(Properties.WATERLOGGED)) {
            world.createAndScheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    //changes to block
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!(world.getBlockEntity(pos) instanceof ServingTrayBlockEntity servingTrayBlockEntity)) {
            return super.onUse(state, world, pos, player, hand, hit);
        }

        ItemStack stack = player.getStackInHand(hand);
        if (stack.getItem().isFood() || stack.getItem() instanceof BeverageItem) {
            if (servingTrayBlockEntity.addToTray(stack)) {
                if (!player.isCreative()) {
                    stack.decrement(stack.getCount());
                }
                return ActionResult.SUCCESS;
            }
        } else if (stack.isEmpty()) {
            ItemStack returnedStack = servingTrayBlockEntity.removeFromTray();
            if (returnedStack != null) {
                player.getInventory().offerOrDrop(returnedStack);
                return ActionResult.SUCCESS;
            }
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        super.onStateReplaced(state, world, pos, newState, moved);
        if (!(world.getBlockEntity(pos) instanceof ServingTrayBlockEntity servingTrayBlockEntity)) {
            return;
        }
        if (!state.getBlock().equals(newState.getBlock())) {
            ItemScatterer.spawn(world, pos, servingTrayBlockEntity.getInventory());
        }
    }
}
