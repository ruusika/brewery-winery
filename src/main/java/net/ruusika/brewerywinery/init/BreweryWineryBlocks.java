package net.ruusika.brewerywinery.init;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.registry.Registry;
import net.ruusika.brewerywinery.BreweryWinery;
import net.ruusika.brewerywinery.blocks.BeverageBlock;
import net.ruusika.brewerywinery.blocks.KegBlock;
import net.ruusika.brewerywinery.blocks.ServingTrayBlock;

@SuppressWarnings("unused")
public class BreweryWineryBlocks {
    public static final Block KEG_BLOCK = register("keg",
            new KegBlock(AbstractBlock.Settings.of(Material.WOOD, MapColor.BROWN)
                    .requiresTool().strength(1.5F, 6.0F)), true);

    public static final Block SERVING_TRAY = register("serving_tray",
            new ServingTrayBlock(AbstractBlock.Settings.of(Material.WOOD, MapColor.BROWN)
                    .breakInstantly()), false);


    public static final Block CRAFT_BEER = register("beer_craft",
            new BeverageBlock(AbstractBlock.Settings.of(Material.GLASS, MapColor.BROWN).breakInstantly(),
                    new Vec3i(4, 0, 4), new Vec3i(12, 8, 12)),
            false);

    public static final Block CRAFT_BEER_LARGE = register("beer_craft_large",
            new BeverageBlock(AbstractBlock.Settings.of(Material.GLASS, MapColor.BROWN).breakInstantly(),
                    new Vec3i(4, 0, 4), new Vec3i(12, 13, 12)),
            false);

    public static final Block LAGER_BEER = register("beer_lager",
            new BeverageBlock(AbstractBlock.Settings.of(Material.GLASS, MapColor.BROWN).breakInstantly(),
                    new Vec3i(4, 0, 4), new Vec3i(12, 8, 12)),
            false);

    public static final Block LAGER_BEER_LARGE = register("beer_lager_large",
            new BeverageBlock(AbstractBlock.Settings.of(Material.GLASS, MapColor.BROWN).breakInstantly(),
                    new Vec3i(4, 0, 4), new Vec3i(12, 13, 12)),
            false);

    public static final Block WEIRD_BEER = register("beer_weird",
            new BeverageBlock(AbstractBlock.Settings.of(Material.GLASS, MapColor.BROWN).breakInstantly(),
                    new Vec3i(4, 0, 4), new Vec3i(12, 8, 12)),
            false);

    public static final Block WEIRD_BEER_LARGE = register("beer_weird_large",
            new BeverageBlock(AbstractBlock.Settings.of(Material.GLASS, MapColor.BROWN).breakInstantly(),
                    new Vec3i(4, 0, 4), new Vec3i(12, 13, 12)),
            false);

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
