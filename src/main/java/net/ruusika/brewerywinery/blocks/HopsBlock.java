package net.ruusika.brewerywinery.blocks;

import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.ruusika.brewerywinery.init.BreweryWineryItems;
import net.ruusika.brewerywinery.init.BreweryWineryProperties;
import net.ruusika.brewerywinery.util.BreweryWineryTags;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class HopsBlock extends CropBlock {
    public static final int MAX_HEIGHT_WITH_SUPPORT = 10;
    public static final int MAX_HEIGHT_WITHOUT_SUPPORT = 2;
    public static final IntProperty HOP_AGE = Properties.AGE_2;

    public HopsBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(BreweryWineryProperties.HAS_PLANT, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        // super.appendProperties(builder);
        builder.add(HOP_AGE, BreweryWineryProperties.HAS_PLANT);
    }

    @Override
    public IntProperty getAgeProperty() {
        return HOP_AGE;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return HopsBlock.createCuboidShape(2, 0, 2, 14, 16, 14);
    }

    @Override
    protected ItemConvertible getSeedsItem() {
        return BreweryWineryItems.HOPS;
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return super.canPlaceAt(state, world, pos);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
        int spaceToSupport = 0;
        world.setBlockState(pos, state.with(BreweryWineryProperties.HAS_PLANT, true));

        BlockPos.Mutable posWalkerUp = pos.mutableCopy();
        while (spaceToSupport <= MAX_HEIGHT_WITH_SUPPORT && !isSupport(world, posWalkerUp.toImmutable()) && isAir(world, posWalkerUp.toImmutable())) {
            spaceToSupport++;
            posWalkerUp.move(Direction.UP);
        }

        posWalkerUp.set(pos);
        boolean hasSupport = spaceToSupport < MAX_HEIGHT_WITH_SUPPORT;

        int structureHeight;
        if (hasSupport) {
            structureHeight = MAX_HEIGHT_WITH_SUPPORT;
        } else {
            structureHeight = MAX_HEIGHT_WITHOUT_SUPPORT;
        }

        do {
            world.setBlockState(posWalkerUp.toImmutable(), this.getDefaultState());
            posWalkerUp.move(Direction.UP);
            structureHeight--;
        } while (structureHeight >= 0);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        int lightLevel = world.getBaseLightLevel(pos, 0);
        int age = this.getAge(state);
        BlockState stateBelow = world.getBlockState(pos.down());
        boolean canGrow = age < this.getMaxAge() && lightLevel >= 9;
        if (stateBelow.getBlock().equals(this) && !stateBelow.get(BreweryWineryProperties.HAS_PLANT) && stateBelow.get(HOP_AGE) < this.getMaxAge()) {
            canGrow = false;
        }
        if (!canGrow) return;

        BlockPos.Mutable baseBlockFinder = pos.mutableCopy();
        while (world.getBlockState(baseBlockFinder.toImmutable()).getBlock().equals(this)) {
            baseBlockFinder.move(Direction.DOWN);
        }

        float moistureAtBase = getAvailableMoisture(this, world, baseBlockFinder.toImmutable());
        int chance = random.nextInt((int) (25.0F / moistureAtBase) + 1);
        if (chance == 0) {
            world.setBlockState(pos, this.withAge(age + 1), Block.NOTIFY_LISTENERS);
        }
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        BlockState newState = super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
        if (newState.isOf(Blocks.AIR)) {
            List<BlockPos> plantStructure = new ArrayList<>();
            BlockPos.Mutable posWalker = pos.up().mutableCopy();

            while (world.getBlockState(posWalker.toImmutable()).isOf(this)) {
                plantStructure.add(posWalker.toImmutable());
                posWalker.move(Direction.UP);
            }

            posWalker.set(pos.down());
            while (world.getBlockState(posWalker.toImmutable()).isOf(this)) {
                plantStructure.add(posWalker.toImmutable());
                posWalker.move(Direction.DOWN);
            }

            for (BlockPos entry : plantStructure) {
                BlockState entryState = world.getBlockState(entry);
                if (entryState.isOf(Blocks.AIR)) continue;
                world.breakBlock(entry, entryState.get(BreweryWineryProperties.HAS_PLANT));
            }

        }
        return newState;
    }

    /**
     * Counts Hops Block structure height. This doesn't account for if the block actually has a plant growing on it.
     * So it checks, if the block has the BlockState of {@link BreweryWineryProperties#HAS_PLANT} but not what actual
     * boolean value it has
     *
     * @param skipSelf skip the input BlockPos, for example if this block doesn't exist anymore but the size of
     *                 the structure still needs to be calculated. It is still included in the count
     * @return amount of Hops blocks in the structure
     */
    public static int getStructureHeight(WorldAccess world, BlockPos pos, boolean skipSelf, boolean countDown, boolean countUp) {
        int count = 0;
        BlockPos.Mutable posWalker = pos.mutableCopy();
        if (skipSelf) {
            posWalker.move(Direction.UP);
            count++;
        }
        if (countUp) {
            while (world.getBlockState(posWalker.toImmutable()).contains(BreweryWineryProperties.HAS_PLANT)) {
                count++;
                posWalker.move(Direction.UP);
            }
        }
        if (countDown) {
            posWalker.set(pos.down());
            while (world.getBlockState(posWalker.toImmutable()).contains(BreweryWineryProperties.HAS_PLANT)) {
                count++;
                posWalker.move(Direction.DOWN);
            }
        }
        return count;
    }

    private static boolean isSupport(World world, BlockPos pos) {
        return world.getBlockState(pos).isIn(BreweryWineryTags.Blocks.HOPS_PILLAR);
    }

    private static boolean hasSupportAbove(WorldAccess world, BlockPos pos) {
        BlockPos.Mutable posWalker = pos.up().mutableCopy();
        while (world.getBlockState(posWalker.toImmutable()).contains(BreweryWineryProperties.HAS_PLANT)) {
            posWalker.move(Direction.UP);
        }
        return isSupport(world, posWalker.toImmutable());
    }

    private static boolean isAir(World world, BlockPos pos) {
        return world.getBlockState(pos).isOf(Blocks.AIR);
    }
}
