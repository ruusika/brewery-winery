package net.ruusika.brewerywinery.datagen.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.data.client.SimpleModelSupplier;
import net.minecraft.util.Identifier;

public class BeerModelSupplier extends SimpleModelSupplier {
    private final Identifier parent;
    private final Identifier modelTexture;
    private final Identifier particleTexture;

    public BeerModelSupplier(Identifier parent, Identifier modelTexture, Identifier particleTexture) {
        super(parent);
        this.parent = parent;
        this.modelTexture = modelTexture;
        this.particleTexture = particleTexture;
    }

    public JsonElement get() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("parent", this.parent.toString());
        JsonObject jsonTexturesObject = new JsonObject();
        jsonTexturesObject.addProperty("0", this.modelTexture.toString());
        jsonTexturesObject.addProperty("particle", this.particleTexture.toString());
        jsonObject.add("textures", jsonTexturesObject);
        return jsonObject;
    }
}
