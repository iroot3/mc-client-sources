/*
 * Decompiled with CFR 0.145.
 */
package net.minecraft.client.renderer.entity.layers;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class LayerCape
implements LayerRenderer {
    private final RenderPlayer playerRenderer;
    private static final String __OBFID = "CL_00002425";

    public LayerCape(RenderPlayer p_i46123_1_) {
        this.playerRenderer = p_i46123_1_;
    }

    public void doRenderLayer(AbstractClientPlayer p_177166_1_, float p_177166_2_, float p_177166_3_, float p_177166_4_, float p_177166_5_, float p_177166_6_, float p_177166_7_, float p_177166_8_) {
        if (p_177166_1_.hasCape() && !p_177166_1_.isInvisible() && p_177166_1_.func_175148_a(EnumPlayerModelParts.CAPE) && p_177166_1_.getLocationCape() != null) {
            GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
            this.playerRenderer.bindTexture(p_177166_1_.getLocationCape());
            GlStateManager.pushMatrix();
            GlStateManager.translate(0.0f, 0.0f, 0.125f);
            double var9 = p_177166_1_.field_71091_bM + (p_177166_1_.field_71094_bP - p_177166_1_.field_71091_bM) * (double)p_177166_4_ - (p_177166_1_.prevPosX + (p_177166_1_.posX - p_177166_1_.prevPosX) * (double)p_177166_4_);
            double var11 = p_177166_1_.field_71096_bN + (p_177166_1_.field_71095_bQ - p_177166_1_.field_71096_bN) * (double)p_177166_4_ - (p_177166_1_.prevPosY + (p_177166_1_.posY - p_177166_1_.prevPosY) * (double)p_177166_4_);
            double var13 = p_177166_1_.field_71097_bO + (p_177166_1_.field_71085_bR - p_177166_1_.field_71097_bO) * (double)p_177166_4_ - (p_177166_1_.prevPosZ + (p_177166_1_.posZ - p_177166_1_.prevPosZ) * (double)p_177166_4_);
            float var15 = p_177166_1_.prevRenderYawOffset + (p_177166_1_.renderYawOffset - p_177166_1_.prevRenderYawOffset) * p_177166_4_;
            double var16 = MathHelper.sin(var15 * 3.1415927f / 180.0f);
            double var18 = -MathHelper.cos(var15 * 3.1415927f / 180.0f);
            float var20 = (float)var11 * 10.0f;
            var20 = MathHelper.clamp_float(var20, -6.0f, 32.0f);
            float var21 = (float)(var9 * var16 + var13 * var18) * 100.0f;
            float var22 = (float)(var9 * var18 - var13 * var16) * 100.0f;
            if (var21 < 0.0f) {
                var21 = 0.0f;
            }
            if (var21 > 165.0f) {
                var21 = 165.0f;
            }
            float var23 = p_177166_1_.prevCameraYaw + (p_177166_1_.cameraYaw - p_177166_1_.prevCameraYaw) * p_177166_4_;
            var20 += MathHelper.sin((p_177166_1_.prevDistanceWalkedModified + (p_177166_1_.distanceWalkedModified - p_177166_1_.prevDistanceWalkedModified) * p_177166_4_) * 6.0f) * 32.0f * var23;
            if (p_177166_1_.isSneaking()) {
                var20 += 25.0f;
                GlStateManager.translate(0.0f, 0.142f, -0.0178f);
            }
            GlStateManager.rotate(6.0f + var21 / 2.0f + var20, 1.0f, 0.0f, 0.0f);
            GlStateManager.rotate(var22 / 2.0f, 0.0f, 0.0f, 1.0f);
            GlStateManager.rotate(-var22 / 2.0f, 0.0f, 1.0f, 0.0f);
            GlStateManager.rotate(180.0f, 0.0f, 1.0f, 0.0f);
            this.playerRenderer.func_177136_g().func_178728_c(0.0625f);
            GlStateManager.popMatrix();
        }
    }

    @Override
    public boolean shouldCombineTextures() {
        return false;
    }

    @Override
    public void doRenderLayer(EntityLivingBase p_177141_1_, float p_177141_2_, float p_177141_3_, float p_177141_4_, float p_177141_5_, float p_177141_6_, float p_177141_7_, float p_177141_8_) {
        this.doRenderLayer((AbstractClientPlayer)p_177141_1_, p_177141_2_, p_177141_3_, p_177141_4_, p_177141_5_, p_177141_6_, p_177141_7_, p_177141_8_);
    }
}

