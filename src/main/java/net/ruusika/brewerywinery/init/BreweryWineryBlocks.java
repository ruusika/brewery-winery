package net.ruusika.brewerywinery.init;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.ruusika.brewerywinery.BreweryWinery;
import net.ruusika.brewerywinery.blocks.BeverageBlock;
import net.ruusika.brewerywinery.blocks.HopsBlock;
import net.ruusika.brewerywinery.blocks.KegBlock;
import net.ruusika.brewerywinery.blocks.ServingTrayBlock;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class BreweryWineryBlocks {

    public static final List<BeverageBlock> BEERS = new ArrayList<>();

    public static final KegBlock KEG_BLOCK = register("keg",
            new KegBlock(AbstractBlock.Settings.of(Material.WOOD, MapColor.BROWN)
                    .requiresTool().strength(1.5F, 6.0F)), true);

    public static final ServingTrayBlock SERVING_TRAY = register("serving_tray",
            new ServingTrayBlock(AbstractBlock.Settings.of(Material.WOOD, MapColor.BROWN)
                    .breakInstantly()), false);

    public static final BeverageBlock CRAFT_BEER = registerBeer("beer_craft",
            new BeverageBlock(AbstractBlock.Settings.of(Material.GLASS, MapColor.BROWN).breakInstantly(),
                    BeverageBlock.Shape.SMALL, BeverageBlock.Color.RED), false);

    public static final BeverageBlock CRAFT_BEER_LARGE = registerBeer("beer_craft_large",
            new BeverageBlock(AbstractBlock.Settings.of(Material.GLASS, MapColor.BROWN).breakInstantly(),
                    BeverageBlock.Shape.LARGE, BeverageBlock.Color.RED), false);

    public static final BeverageBlock LAGER_BEER = registerBeer("beer_lager",
            new BeverageBlock(AbstractBlock.Settings.of(Material.GLASS, MapColor.BROWN).breakInstantly(),
                    BeverageBlock.Shape.SMALL, BeverageBlock.Color.LIGHT), false);

    public static final BeverageBlock LAGER_BEER_LARGE = registerBeer("beer_lager_large",
            new BeverageBlock(AbstractBlock.Settings.of(Material.GLASS, MapColor.BROWN).breakInstantly(),
                    BeverageBlock.Shape.LARGE, BeverageBlock.Color.LIGHT), false);

    public static final BeverageBlock WEIRD_BEER = registerBeer("beer_weird",
            new BeverageBlock(AbstractBlock.Settings.of(Material.GLASS, MapColor.BROWN).breakInstantly(),
                    BeverageBlock.Shape.SMALL, BeverageBlock.Color.DARK), false);

    public static final BeverageBlock WEIRD_BEER_LARGE = registerBeer("beer_weird_large",
            new BeverageBlock(AbstractBlock.Settings.of(Material.GLASS, MapColor.BROWN).breakInstantly(),
                    BeverageBlock.Shape.LARGE, BeverageBlock.Color.DARK), false);

    public static final BeverageBlock RED_WINE = register("wine_red",
            new BeverageBlock(AbstractBlock.Settings.of(Material.GLASS, MapColor.BROWN).breakInstantly(),
                    BeverageBlock.Shape.SMALL, BeverageBlock.Color.DARK_RED), false);

    public static final BeverageBlock ROSE_WINE = register("wine_rose",
            new BeverageBlock(AbstractBlock.Settings.of(Material.GLASS, MapColor.BROWN).breakInstantly(),
                    BeverageBlock.Shape.SMALL, BeverageBlock.Color.ROSE), false);

    public static final BeverageBlock WHITE_WINE = register("wine_white",
            new BeverageBlock(AbstractBlock.Settings.of(Material.GLASS, MapColor.BROWN).breakInstantly(),
                    BeverageBlock.Shape.SMALL, BeverageBlock.Color.WHITE), false);

    public static final BeverageBlock CIDER = register("cider",
            new BeverageBlock(AbstractBlock.Settings.of(Material.GLASS, MapColor.BROWN).breakInstantly(),
                    BeverageBlock.Shape.SMALL, BeverageBlock.Color.HONEY), false);

    public static final BeverageBlock MEAD = register("mead",
            new BeverageBlock(AbstractBlock.Settings.of(Material.GLASS, MapColor.BROWN).breakInstantly(),
                    BeverageBlock.Shape.SMALL, BeverageBlock.Color.HONEY), false);

    public static final HopsBlock HOPS_PLANT = register("hops_plant",
            new HopsBlock(FabricBlockSettings.of(Material.PLANT).nonOpaque().noCollision().ticksRandomly()
                    .breakInstantly().sounds(BlockSoundGroup.CROP)), false);

    private static <T extends Block> T register(String name, T block, boolean hasDefaultItem) {
        Identifier id = new Identifier(BreweryWinery.MOD_ID, name);
        if (hasDefaultItem) {
            Registry.register(Registry.ITEM, id, new BlockItem(block, new FabricItemSettings()
                    .group(BreweryWineryItemGroups.BREWERY_WINERY)));
        }
        return Registry.register(Registry.BLOCK, id, block);
    }

    private static <T extends Block> T registerBeer(String name, T block, boolean hasDefaultItem) {
        if (block instanceof BeverageBlock beverageBlock) {
            BEERS.add(beverageBlock);
        }
        return register(name, block, hasDefaultItem);
    }

    public static void initialize() {
        //initialize class statically
    }
}
