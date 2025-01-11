package net.ruusika.brewerywinery.blocks.entities.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3f;
import net.ruusika.brewerywinery.blocks.entities.ServingTrayBlockEntity;

import java.util.Optional;

public class ServingTrayBlockEntityRenderer<T extends ServingTrayBlockEntity> implements BlockEntityRenderer<T> {
    @SuppressWarnings("FieldCanBeLocal")
    private final BlockEntityRendererFactory.Context context;

    public ServingTrayBlockEntityRenderer(BlockEntityRendererFactory.Context context) {
        this.context = context;
    }

    @Override
    public void render(T entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {

        MinecraftClient client = MinecraftClient.getInstance();
        if (entity == null || entity.getWorld() == null) {
            return;
        }

        for (int i = 0; i < entity.getInventory().size(); i++) {


            ItemStack stack = entity.getInventory().get(i);
            int seed = (int) (entity.getPos().asLong() + i);
            double offset = 0.195;
            double center = 0.5;

            double x;
            double z;

            if (i == 0) {
                x = center + offset;
                z = center + offset;
            } else if (i == 1) {
                x = center - offset;
                z = center + offset;
            } else if (i == 2) {
                x = center + offset;
                z = center - offset;
            } else {
                x = center - offset;
                z = center - offset;
            }

            if (!stack.isEmpty()) {
                float scale = 0.7f;

                matrices.push();

                matrices.translate(x, 0.24, z);
                float rotation = Optional.ofNullable(entity.getStackRotation(i)).orElse(0f);
                matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(rotation));
                matrices.scale(scale, scale, scale);
                client.getItemRenderer().renderItem(stack, ModelTransformation.Mode.FIXED, light, overlay, matrices, vertexConsumers, seed);

                matrices.pop();
            }
        }

    }
}
