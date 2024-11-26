package net.ruusika.brewerywinery.init;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.ruusika.brewerywinery.BreweryWinery;

public class BreweryWineryItemGroups {
    public static final ItemGroup BREWERY_WINERY = FabricItemGroupBuilder.build(new Identifier(BreweryWinery.MOD_ID,"brewery-winery"),
            () -> new ItemStack(BreweryWineryItems.LAGER_BEER));
}
