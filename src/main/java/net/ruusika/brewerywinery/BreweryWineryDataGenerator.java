package net.ruusika.brewerywinery;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.ruusika.brewerywinery.datagen.BreweryWineryLootTableProvider;
import net.ruusika.brewerywinery.datagen.BreweryWineryModelProvider;
import net.ruusika.brewerywinery.datagen.BreweryWineryTagsProvider;
import net.ruusika.brewerywinery.datagen.BreweryWineryTranslationProvider;

public class BreweryWineryDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		fabricDataGenerator.addProvider(BreweryWineryModelProvider::new);
		fabricDataGenerator.addProvider(BreweryWineryTranslationProvider::new);
		fabricDataGenerator.addProvider(BreweryWineryLootTableProvider::new);
		//für Unterklassen bei Tags
		BreweryWineryTagsProvider.registerAll(fabricDataGenerator);
	}
}
