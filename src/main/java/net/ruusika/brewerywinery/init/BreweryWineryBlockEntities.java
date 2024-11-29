package net.ruusika.brewerywinery.init;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.ruusika.brewerywinery.BreweryWinery;
import net.ruusika.brewerywinery.blocks.entities.KegBlockEntity;
import net.ruusika.brewerywinery.blocks.entities.ServingTrayBlockEntity;

public class BreweryWineryBlockEntities {

    public static final BlockEntityType<KegBlockEntity> KEG = Registry.register(Registry.BLOCK_ENTITY_TYPE,
            new Identifier(BreweryWinery.MOD_ID, "keg"),
            FabricBlockEntityTypeBuilder.create(KegBlockEntity::new, BreweryWineryBlocks.KEG_BLOCK).build());

    public static final BlockEntityType<ServingTrayBlockEntity> SERVING_TRAY = Registry
            .register(Registry.BLOCK_ENTITY_TYPE,
            new Identifier(BreweryWinery.MOD_ID, "serving_tray"),
                    FabricBlockEntityTypeBuilder.create(ServingTrayBlockEntity::new,
                            BreweryWineryBlocks.SERVING_TRAY).build());

    public static void initialize() {
        //initialize class statically
    }
}
