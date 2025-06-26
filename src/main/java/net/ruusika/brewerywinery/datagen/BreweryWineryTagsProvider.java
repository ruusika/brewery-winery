package net.ruusika.brewerywinery.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Blocks;
import net.minecraft.tag.BlockTags;
import net.ruusika.brewerywinery.util.BreweryWineryTags;

public class BreweryWineryTagsProvider {
    public static class BWBlockTags extends FabricTagProvider.BlockTagProvider {

        public BWBlockTags(FabricDataGenerator dataGenerator) {
            super(dataGenerator);
        }

        @Override
        protected void generateTags() {
            getOrCreateTagBuilder(BreweryWineryTags.Blocks.HOPS_PILLAR).addOptionalTag(BlockTags.FENCES);
            getOrCreateTagBuilder(BreweryWineryTags.Blocks.HEAT_BLOCKS).add(Blocks.MAGMA_BLOCK, Blocks.LAVA, Blocks.LAVA_CAULDRON);
        }
    }

    public static void registerAll(FabricDataGenerator generator) {
        generator.addProvider(BWBlockTags::new);
    }
}
