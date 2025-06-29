package net.ruusika.brewerywinery.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.ruusika.brewerywinery.init.BreweryWineryBlocks;
import net.ruusika.brewerywinery.init.BreweryWineryItems;

public class BreweryWineryLootTableProvider extends FabricBlockLootTableProvider {
    public BreweryWineryLootTableProvider(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    public void generateBlockLootTables() {
        addDrop(BreweryWineryBlocks.HOPS_PLANT, drops(BreweryWineryItems.HOPS));
    }
}
