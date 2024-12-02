package net.ruusika.brewerywinery;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.ruusika.brewerywinery.datagen.BreweryWineryModelProvider;

public class BreweryWineryDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		fabricDataGenerator.addProvider(BreweryWineryModelProvider::new);
	}
}
