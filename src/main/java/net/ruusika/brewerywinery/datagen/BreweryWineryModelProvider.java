package net.ruusika.brewerywinery.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.data.client.*;
import net.minecraft.util.Identifier;
import net.ruusika.brewerywinery.BreweryWinery;
import net.ruusika.brewerywinery.blocks.KegBlock;
import net.ruusika.brewerywinery.init.BreweryWineryBlocks;

public class BreweryWineryModelProvider extends FabricModelProvider {

    public BreweryWineryModelProvider(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator
                .createSingletonBlockState(BreweryWineryBlocks.SERVING_TRAY,
                        new Identifier(BreweryWinery.MOD_ID, "block/serving_tray_block")));

        generateDefaultNorthFacingBlock(blockStateModelGenerator, BreweryWineryBlocks.LAGER_BEER, "beer_lager_block");
        generateDefaultNorthFacingBlock(blockStateModelGenerator, BreweryWineryBlocks.CRAFT_BEER, "beer_craft_block");
        generateDefaultNorthFacingBlock(blockStateModelGenerator, BreweryWineryBlocks.WEIRD_BEER, "beer_weird_block");

        generateDefaultNorthFacingBlock(blockStateModelGenerator, BreweryWineryBlocks.LAGER_BEER_LARGE, "beer_lager_large_block");
        generateDefaultNorthFacingBlock(blockStateModelGenerator, BreweryWineryBlocks.CRAFT_BEER_LARGE, "beer_craft_large_block");
        generateDefaultNorthFacingBlock(blockStateModelGenerator, BreweryWineryBlocks.WEIRD_BEER_LARGE, "beer_weird_large_block");

        generateDefaultNorthFacingBlock(blockStateModelGenerator, BreweryWineryBlocks.RED_WINE, "wine_red_block");
        generateDefaultNorthFacingBlock(blockStateModelGenerator, BreweryWineryBlocks.WHITE_WINE, "wine_white_block");
        generateDefaultNorthFacingBlock(blockStateModelGenerator, BreweryWineryBlocks.ROSE_WINE, "wine_rose_block");

        generateDefaultNorthFacingBlock(blockStateModelGenerator, BreweryWineryBlocks.MEAD, "mead_block");
        generateDefaultNorthFacingBlock(blockStateModelGenerator, BreweryWineryBlocks.CIDER, "cider_block");

        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(BreweryWineryBlocks.KEG_BLOCK)
                .coordinate(BlockStateModelGenerator.createNorthDefaultHorizontalRotationStates())
                .coordinate(createIsFinishedMap()));

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

    }

    private static void generateDefaultNorthFacingBlock(BlockStateModelGenerator blockStateModelGenerator, Block block, String name) {
        blockStateModelGenerator.blockStateCollector.accept(
                VariantsBlockStateSupplier.create(block, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, new Identifier(BreweryWinery.MOD_ID, "block/" + name)))
                        .coordinate(BlockStateModelGenerator.createNorthDefaultHorizontalRotationStates()));
    }

    private static BlockStateVariantMap createIsFinishedMap() {
        return BlockStateVariantMap.create(KegBlock.IS_FINISHED).register(aBoolean -> {
            String name = "block/keg_block";
            if (aBoolean) {
                name += "_finished";
            }
            return BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(BreweryWinery.MOD_ID, name));
        });
    }
}
