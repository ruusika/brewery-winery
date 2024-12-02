package net.ruusika.brewerywinery.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.*;
import net.minecraft.util.Identifier;
import net.ruusika.brewerywinery.BreweryWinery;
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

        blockStateModelGenerator.blockStateCollector.accept(
                VariantsBlockStateSupplier.create(BreweryWineryBlocks.LAGER_BEER, BlockStateVariant.create()
                                .put(VariantSettings.MODEL, new Identifier(BreweryWinery.MOD_ID, "block/beer_lager_block")))
                        .coordinate(BlockStateModelGenerator.createNorthDefaultHorizontalRotationStates())
        );

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

    }
}
