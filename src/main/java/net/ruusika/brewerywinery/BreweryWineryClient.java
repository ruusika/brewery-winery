package net.ruusika.brewerywinery;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import net.ruusika.brewerywinery.init.BreweryWineryBlocks;

public class BreweryWineryClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(BreweryWineryBlocks.LAGER_BEER_LARGE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BreweryWineryBlocks.CRAFT_BEER_LARGE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BreweryWineryBlocks.WEIRD_BEER_LARGE, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(BreweryWineryBlocks.LAGER_BEER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BreweryWineryBlocks.CRAFT_BEER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BreweryWineryBlocks.WEIRD_BEER, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(BreweryWineryBlocks.RED_WINE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BreweryWineryBlocks.WHITE_WINE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BreweryWineryBlocks.ROSE_WINE, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(BreweryWineryBlocks.CIDER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BreweryWineryBlocks.MEAD, RenderLayer.getCutout());
    }
}
