package net.ruusika.brewerywinery.init;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.ruusika.brewerywinery.BreweryWinery;
import net.ruusika.brewerywinery.items.BeverageItem;

public class BreweryWineryItems {
    public static final Item LAGER_BEER = register("beer_lager", new BeverageItem());
    public static final Item HONEY_BEER = register("beer_honey", new BeverageItem());
    public static final Item WEIRD_BEER = register("beer_weird", new BeverageItem());

    public static final Item RED_WINE = register("wine_red", new BeverageItem());
    public static final Item WHITE_WINE = register("wine_white", new BeverageItem());
    public static final Item ROSE_WINE = register("wine_rose", new BeverageItem());

    public static final Item MEAD = register("mead", new BeverageItem(new FabricItemSettings().maxCount(16),20));
    public static final Item CIDER = register("cider", new BeverageItem());

    private static Item register(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(BreweryWinery.MOD_ID, name), item);
    }

    public static void initialize() {

    }
}
