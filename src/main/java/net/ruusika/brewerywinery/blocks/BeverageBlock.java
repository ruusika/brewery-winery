package net.ruusika.brewerywinery.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

public class BeverageBlock extends HorizontalFacingBlock {

    private final Vec3i min;
    private final Vec3i max;
    private final Size size;
    private final Color color;

    public BeverageBlock(Settings settings, Vec3i min, Vec3i max, Size size, Color color) {
        super(settings);
        this.min = min;
        this.max = max;
        this.size = size;
        this.color = color;

        setDefaultState(getDefaultState().with(FACING, Direction.NORTH));
    }

    public Size getSize() {
        return size;
    }

    public Color getColor() {
        return color;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(FACING);
    }

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
        return getDefaultState().with(FACING, ctx.getPlayerFacing());
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return Block.createCuboidShape(min.getX(), min.getY(), min.getZ(), max.getX(), max.getY(), max.getZ());
    }

    public enum Size {
        SMALL("small"),
        LARGE("large");

        private final String identifier;

        Size(String identifier){
            this.identifier = identifier;
        }

        public String getName() {
            return identifier;
        }
    }

    public enum Color {
        DARK("dark", 0x64491F),
        LIGHT("light", 0xBD7E37),
        RED("red", 0x5D271B),

        DARK_RED("dark_red", 0x8D021F),
        WHITE("white", 0xf7cac9),
        ROSE("rose", 0xF9E8C0),

        HONEY("honey", 0xCB8E00);



        private final String identifier;
        private final int color;

        Color(String identifier, int color){
            this.identifier = identifier;
            this.color = color;
        }

        public String getName() {
            return identifier;
        }

        public int getColor() {
            return color;
        }
    }
}
