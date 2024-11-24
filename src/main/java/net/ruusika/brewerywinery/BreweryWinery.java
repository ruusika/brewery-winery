package net.ruusika.brewerywinery;

import net.fabricmc.api.ModInitializer;

import net.ruusika.brewerywinery.init.BreweryWineryBlockEntities;
import net.ruusika.brewerywinery.init.BreweryWineryBlocks;
import net.ruusika.brewerywinery.init.BreweryWineryItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BreweryWinery implements ModInitializer {
	public static final String MOD_ID = "brewery-winery";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		BreweryWineryItems.initialize();
		BreweryWineryBlocks.initialize();
		BreweryWineryBlockEntities.initialize();
		LOGGER.info("DINONUGGETS!");
	}
}