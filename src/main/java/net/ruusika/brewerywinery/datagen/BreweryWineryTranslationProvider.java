package net.ruusika.brewerywinery.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.ruusika.brewerywinery.BreweryWinery;
import net.ruusika.brewerywinery.blocks.BeverageBlock;
import net.ruusika.brewerywinery.init.BreweryWineryBlocks;
import net.ruusika.brewerywinery.init.BreweryWineryItems;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BreweryWineryTranslationProvider extends FabricLanguageProvider {

    public BreweryWineryTranslationProvider(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {

       for (BeverageBlock entry : BreweryWineryBlocks.BEERS) {
            translationBuilder.add(entry, cleanString(Registry.BLOCK.getId(entry), true));
       }

       translationBuilder.add(BreweryWineryBlocks.SERVING_TRAY, cleanString(Registry.BLOCK.getId(BreweryWineryBlocks.SERVING_TRAY), false));
       translationBuilder.add(BreweryWineryBlocks.KEG_BLOCK, cleanString(Registry.BLOCK.getId(BreweryWineryBlocks.KEG_BLOCK), false));
       translationBuilder.add(BreweryWineryItems.HOPS, cleanString(Registry.ITEM.getId(BreweryWineryItems.HOPS), false));
       translationBuilder.add(BreweryWineryItems.YEAST, cleanString(Registry.ITEM.getId(BreweryWineryItems.YEAST), false));


       try {
           Path existingFilePath = dataGenerator.getModContainer().findPath("assets/%s/lang/en_us_existing.json"
                   .formatted(BreweryWinery.MOD_ID)).get();
           translationBuilder.add(existingFilePath);
       } catch (Exception e) {
           throw new RuntimeException("Failed to add existing language file!", e);
       }
    }

    private static String cleanString(Identifier name, boolean reverse) {
        List<String> input = List.of(name.getPath().split("/"));
        List<String> lastWords = Arrays.asList(input.get(input.size()-1).split("_"));
        if (reverse) {
            Collections.reverse(lastWords);
        }

        StringBuilder output = new StringBuilder();

        for (int i = 0; i < lastWords.size(); i++) {
            String word = lastWords.get(i);
            char firstLetter = Character.toUpperCase(word.charAt(0));
            output.append(firstLetter).append(word.substring(1));
            if (i < lastWords.size()-1) {
                output.append(" ");
            }
        }

        return output.toString();
    }
}
