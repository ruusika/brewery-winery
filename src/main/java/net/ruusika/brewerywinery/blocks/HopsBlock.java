package net.ruusika.brewerywinery.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.item.ItemConvertible;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.ruusika.brewerywinery.init.BreweryWineryItems;

public class HopsBlock extends CropBlock {

    public HopsBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState()
          //      .with(Properties.AGE_2, 0)
                .with(Properties.DOUBLE_BLOCK_HALF, DoubleBlockHalf.LOWER));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
       // super.appendProperties(builder);
        builder.add(Properties.AGE_2, Properties.DOUBLE_BLOCK_HALF);
    }

    @Override
    public IntProperty getAgeProperty() {
        return Properties.AGE_2;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return HopsBlock.createCuboidShape(2, 0, 2, 14, 16, 14);
    }

    @Override
    protected ItemConvertible getSeedsItem() {
        return BreweryWineryItems.HOPS;
    }

    //TODO: add canPlaceAt - Method
}
