package net.ruusika.brewerywinery.blocks.entities.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3f;
import net.ruusika.brewerywinery.blocks.BeverageBlock;
import net.ruusika.brewerywinery.blocks.entities.ServingTrayBlockEntity;

import java.util.Optional;

public class ServingTrayBlockEntityRenderer<T extends ServingTrayBlockEntity> implements BlockEntityRenderer<T> {

    public ServingTrayBlockEntityRenderer(BlockEntityRendererFactory.Context context) {
    }

    @Override
    public void render(T blockEntity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {

        MinecraftClient client = MinecraftClient.getInstance();
        if (blockEntity == null || blockEntity.getWorld() == null) {
            return;
        }

        for (int i = 0; i < blockEntity.getInventory().size(); i++) {


            ItemStack stack = blockEntity.getInventory().get(i);
            int seed = (int) (blockEntity.getPos().asLong() + i);

            double x;
            double z;

            Vec3d normalizeRandomPosition = Optional.ofNullable(blockEntity.getStackPosition(i)).orElse(Vec3d.ZERO);
            double minRandomPosition = -0.04;
            double maxRandomPosition = -0.015;
            Vec3d randomPosition = new Vec3d(
                    MathHelper.lerp(normalizeRandomPosition.x, minRandomPosition, maxRandomPosition),
                    normalizeRandomPosition.y,
                    MathHelper.lerp(normalizeRandomPosition.x, minRandomPosition, maxRandomPosition)
            );

            double offset = 0.195;
            double center = 0.5;

            if (i == 0) {
                x = center + offset + randomPosition.x;
                z = center + offset + randomPosition.z;
            } else if (i == 1) {
                x = center - (offset + randomPosition.x);
                z = center + offset + randomPosition.z;
            } else if (i == 2) {
                x = center + offset + randomPosition.x;
                z = center - (offset + randomPosition.z);
            } else {
                x = center - (offset + randomPosition.x);
                z = center - (offset + randomPosition.z);
            }

            if (!stack.isEmpty()) {

                ModelTransformation.Mode displayMode;

                matrices.push();
                matrices.translate(x, 0.31, z);

                if (stack.getItem() instanceof BlockItem blockItem && blockItem.getBlock() instanceof BeverageBlock) {
                    float scale = 0.5f;
                    displayMode = ModelTransformation.Mode.HEAD;
                    float rotation = Optional.ofNullable(blockEntity.getStackRotation(i)).orElse(0f);
                    matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(rotation));
                    matrices.scale(scale, scale, scale);
                } else {
                    float scale = 0.25f;
                    displayMode = ModelTransformation.Mode.FIXED;
                    matrices.scale(scale, scale, scale);
                    matrices.translate(0, -0.95, 0);
                    matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(90));

                }
                client.getItemRenderer().renderItem(stack, displayMode, light, overlay, matrices, vertexConsumers, seed);

                matrices.pop();
            }
        }

    }
}
