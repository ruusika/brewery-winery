package net.ruusika.brewerywinery.util;

import net.minecraft.block.Block;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.ruusika.brewerywinery.BreweryWinery;

public class BreweryWineryTags {
    public static class Blocks {
       public static final TagKey<Block> HEAT_BLOCKS = createTag("heat_blocks");
       public static final TagKey<Block> HOPS_PILLAR = createTag("hops_pillar");

       private static TagKey<Block> createTag(String name) {
           return TagKey.of(Registry.BLOCK_KEY, new Identifier(BreweryWinery.MOD_ID, name));
        }

    }
}
