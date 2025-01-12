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

    private final Shape shape;
    private final Color color;

    public BeverageBlock(Settings settings, Shape shape, Color color) {
        super(settings);
        this.shape = shape;
        this.color = color;

        setDefaultState(getDefaultState().with(FACING, Direction.NORTH));
    }

    public Shape getSize() {
        return shape;
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
        return Block.createCuboidShape(
                this.shape.getMin().getX(), this.shape.getMin().getY(),this.shape.getMin().getZ(),
                this.shape.getMax().getX(),this.shape.getMax().getY(),this.shape.getMax().getZ()
        );
    }

    public enum Shape {
        SMALL("small", new Vec3i(5, 0, 5), new Vec3i(11, 8, 11)),
        LARGE("large", new Vec3i(5, 0, 5), new Vec3i(11, 10, 11));

        private final String identifier;
        private final Vec3i min;
        private final Vec3i max;

        Shape(String identifier, Vec3i min, Vec3i max){
            this.identifier = identifier;
            this.min = min;
            this.max = max;

        }

        public String getName() {
            return identifier;
        }

        public Vec3i getMax() {
            return max;
        }

        public Vec3i getMin() {
            return min;
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
