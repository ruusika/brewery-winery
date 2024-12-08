package net.ruusika.brewerywinery.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.item.ItemStack;
import net.ruusika.brewerywinery.items.ServingTrayItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PlayerEntityRenderer.class)
public class PlayerEntityRendererMixin {
    @ModifyExpressionValue(method = "getArmPose", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isEmpty()Z"))
    private static boolean resetArmPoseForServingTray(boolean original, @Local ItemStack stack) {

        return original || stack.getItem() instanceof ServingTrayItem;
    }
}
