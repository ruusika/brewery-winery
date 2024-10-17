package net.ruusika.brewerywinery.init;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.ruusika.brewerywinery.BreweryWinery;

public class BreweryWineryBlocks {
    public static final Block TEST_BLOCK = register("test", new Block(AbstractBlock.Settings.of(Material.STONE, MapColor.STONE_GRAY).requiresTool().strength(1.5F, 6.0F)));

    private static Block register(String name, Block block) {
        return Registry.register(Registry.BLOCK, new Identifier(BreweryWinery.MOD_ID, name), block);
    }

    public static void initialize() {
        //initialize class statically
    }
}
