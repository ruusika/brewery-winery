package net.ruusika.brewerywinery.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.predicate.StatePredicate;
import net.ruusika.brewerywinery.init.BreweryWineryBlocks;
import net.ruusika.brewerywinery.init.BreweryWineryItems;
import net.ruusika.brewerywinery.init.BreweryWineryProperties;

public class BreweryWineryLootTableProvider extends FabricBlockLootTableProvider {
    public BreweryWineryLootTableProvider(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    public void generateBlockLootTables() {
        LootCondition.Builder allowDropBuilder = BlockStatePropertyLootCondition.builder(BreweryWineryBlocks.HOPS_PLANT)
                .properties(StatePredicate.Builder.create().exactMatch(BreweryWineryProperties.HAS_PLANT, true));
        addDrop(BreweryWineryBlocks.HOPS_PLANT, cropDrops(BreweryWineryBlocks.HOPS_PLANT, BreweryWineryItems.HOPS,
                BreweryWineryItems.HOPS, allowDropBuilder));
    }
}
