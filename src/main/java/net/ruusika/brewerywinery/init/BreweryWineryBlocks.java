package net.ruusika.brewerywinery.init;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.ruusika.brewerywinery.BreweryWinery;
import net.ruusika.brewerywinery.blocks.KegBlock;

public class BreweryWineryBlocks {
    public static final Block KEG_BLOCK = register("keg",
            new KegBlock(AbstractBlock.Settings.of(Material.WOOD, MapColor.BROWN)
                    .requiresTool().strength(1.5F, 6.0F)), true);

    private static Block register(String name, Block block, boolean hasDefaultItem) {
        Identifier id = new Identifier(BreweryWinery.MOD_ID, name);
        if (hasDefaultItem) {
            Registry.register(Registry.ITEM, id, new BlockItem(block, new FabricItemSettings()
                    .group(BreweryWineryItemGroups.BREWERY_WINERY)));
        }
        return Registry.register(Registry.BLOCK, id, block);
    }

    public static void initialize() {
        //initialize class statically
    }
}
