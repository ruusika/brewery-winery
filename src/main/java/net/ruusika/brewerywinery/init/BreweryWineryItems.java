package net.ruusika.brewerywinery.init;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.ruusika.brewerywinery.BreweryWinery;
import net.ruusika.brewerywinery.items.BeverageItem;
import net.ruusika.brewerywinery.items.BreweryWineryToolMaterials;
import net.ruusika.brewerywinery.items.BrokenBottleItem;
import net.ruusika.brewerywinery.items.ServingTrayItem;

@SuppressWarnings("unused")
public class BreweryWineryItems {
    public static final BeverageItem LAGER_BEER = register("beer_lager", new BeverageItem(
            BreweryWineryBlocks.LAGER_BEER, new FabricItemSettings().group(BreweryWineryItemGroups.BREWERY_WINERY),
            40));

    public static final BeverageItem CRAFT_BEER = register("beer_craft", new BeverageItem(
            BreweryWineryBlocks.CRAFT_BEER, new FabricItemSettings().group(BreweryWineryItemGroups.BREWERY_WINERY),
            40));

    public static final BeverageItem WEIRD_BEER = register("beer_weird", new BeverageItem(
            BreweryWineryBlocks.WEIRD_BEER, new FabricItemSettings().group(BreweryWineryItemGroups.BREWERY_WINERY),
            40));

    public static final BeverageItem LAGER_BEER_LARGE = register("beer_lager_large", new BeverageItem(
            BreweryWineryBlocks.LAGER_BEER_LARGE, new FabricItemSettings().group(BreweryWineryItemGroups.BREWERY_WINERY),
            40));

    public static final BeverageItem CRAFT_BEER_LARGE = register("beer_craft_large", new BeverageItem(
            BreweryWineryBlocks.CRAFT_BEER_LARGE, new FabricItemSettings().group(BreweryWineryItemGroups.BREWERY_WINERY),
            40));

    public static final BeverageItem WEIRD_BEER_LARGE = register("beer_weird_large", new BeverageItem(
            BreweryWineryBlocks.WEIRD_BEER_LARGE, new FabricItemSettings().group(BreweryWineryItemGroups.BREWERY_WINERY),
            40));
/*
    public static final BeverageItem RED_WINE = register("wine_red", new BeverageItem(
            BreweryWineryBlocks.RED_WINE, new FabricItemSettings().group(BreweryWineryItemGroups.BREWERY_WINERY),
            40));

    public static final BeverageItem WHITE_WINE = register("wine_white", new BeverageItem(
            BreweryWineryBlocks.WHITE_WINE, new FabricItemSettings().group(BreweryWineryItemGroups.BREWERY_WINERY),
            40));

    public static final BeverageItem ROSE_WINE = register("wine_rose", new BeverageItem(
            BreweryWineryBlocks.ROSE_WINE, new FabricItemSettings().group(BreweryWineryItemGroups.BREWERY_WINERY),
            40));

    public static final BeverageItem MEAD = register("mead", new BeverageItem(
            BreweryWineryBlocks.MEAD, new FabricItemSettings().group(BreweryWineryItemGroups.BREWERY_WINERY),
            40));

    public static final BeverageItem CIDER = register("cider", new BeverageItem(
            BreweryWineryBlocks.CIDER, new FabricItemSettings().group(BreweryWineryItemGroups.BREWERY_WINERY),
            40));
    */

    public static final Item HOPS = register("hops", new AliasedBlockItem(BreweryWineryBlocks.HOPS_PLANT,
            new FabricItemSettings().group(BreweryWineryItemGroups.BREWERY_WINERY)));
    public static final Item YEAST = register("yeast", new Item(
            new FabricItemSettings().group(BreweryWineryItemGroups.BREWERY_WINERY)));

    public static final ServingTrayItem SERVING_TRAY = register("serving_tray",
            new ServingTrayItem(BreweryWineryBlocks.SERVING_TRAY,
                    new FabricItemSettings().group(BreweryWineryItemGroups.BREWERY_WINERY)));

    public static final BrokenBottleItem BROKEN_BOTTLE = register("broken_bottle",
            new BrokenBottleItem(BreweryWineryToolMaterials.BROKEN_GLASS, 3, -2.0F,
                    new Item.Settings().maxCount(1).group(BreweryWineryItemGroups.BREWERY_WINERY)));

    private static <T extends Item> T register(String name, T item) {
        return Registry.register(Registry.ITEM, new Identifier(BreweryWinery.MOD_ID, name), item);
    }

    public static void initialize() {

    }
}
