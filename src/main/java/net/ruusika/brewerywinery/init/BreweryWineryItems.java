package net.ruusika.brewerywinery.init;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.ruusika.brewerywinery.BreweryWinery;
import net.ruusika.brewerywinery.items.BeverageItem;
import net.ruusika.brewerywinery.items.ServingTrayItem;

@SuppressWarnings("unused")
public class BreweryWineryItems {
    public static final Item LAGER_BEER = register("beer_lager", new BeverageItem(
            BreweryWineryBlocks.LAGER_BEER, new FabricItemSettings().group(BreweryWineryItemGroups.BREWERY_WINERY),
            40));

    public static final Item CRAFT_BEER = register("beer_craft", new BeverageItem(
            BreweryWineryBlocks.CRAFT_BEER, new FabricItemSettings().group(BreweryWineryItemGroups.BREWERY_WINERY),
            40));

    public static final Item WEIRD_BEER = register("beer_weird", new BeverageItem(
            BreweryWineryBlocks.WEIRD_BEER, new FabricItemSettings().group(BreweryWineryItemGroups.BREWERY_WINERY),
            40));

    public static final Item LAGER_BEER_LARGE = register("beer_lager_large", new BeverageItem(
            BreweryWineryBlocks.LAGER_BEER_LARGE, new FabricItemSettings().group(BreweryWineryItemGroups.BREWERY_WINERY),
            40));

    public static final Item CRAFT_BEER_LARGE = register("beer_craft_large", new BeverageItem(
            BreweryWineryBlocks.CRAFT_BEER_LARGE, new FabricItemSettings().group(BreweryWineryItemGroups.BREWERY_WINERY),
            40));

    public static final Item WEIRD_BEER_LARGE = register("beer_weird_large", new BeverageItem(
            BreweryWineryBlocks.WEIRD_BEER_LARGE, new FabricItemSettings().group(BreweryWineryItemGroups.BREWERY_WINERY),
            40));
/*
    public static final Item RED_WINE = register("wine_red", new BeverageItem(
            BreweryWineryBlocks.RED_WINE, new FabricItemSettings().group(BreweryWineryItemGroups.BREWERY_WINERY),
            40));

    public static final Item WHITE_WINE = register("wine_white", new BeverageItem(
            BreweryWineryBlocks.WHITE_WINE, new FabricItemSettings().group(BreweryWineryItemGroups.BREWERY_WINERY),
            40));

    public static final Item ROSE_WINE = register("wine_rose", new BeverageItem(
            BreweryWineryBlocks.ROSE_WINE, new FabricItemSettings().group(BreweryWineryItemGroups.BREWERY_WINERY),
            40));

    public static final Item MEAD = register("mead", new BeverageItem(
            BreweryWineryBlocks.MEAD, new FabricItemSettings().group(BreweryWineryItemGroups.BREWERY_WINERY),
            40));

    public static final Item CIDER = register("cider", new BeverageItem(
            BreweryWineryBlocks.CIDER, new FabricItemSettings().group(BreweryWineryItemGroups.BREWERY_WINERY),
            40));
    */

    public static final Item HOPS = register("hops", new Item(
            new FabricItemSettings().group(BreweryWineryItemGroups.BREWERY_WINERY)));
    public static final Item YEAST = register("yeast", new Item(
            new FabricItemSettings().group(BreweryWineryItemGroups.BREWERY_WINERY)));

    public static final Item SERVING_TRAY = register("serving_tray",
            new ServingTrayItem(BreweryWineryBlocks.SERVING_TRAY,
                    new FabricItemSettings().group(BreweryWineryItemGroups.BREWERY_WINERY)));

    private static Item register(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(BreweryWinery.MOD_ID, name), item);
    }

    public static void initialize() {

    }
}
