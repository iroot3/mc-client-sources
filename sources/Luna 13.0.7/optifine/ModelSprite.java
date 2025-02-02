package optifine;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.util.MathHelper;

public class ModelSprite
{
  private ModelRenderer modelRenderer = null;
  private int textureOffsetX = 0;
  private int textureOffsetY = 0;
  private float posX = 0.0F;
  private float posY = 0.0F;
  private float posZ = 0.0F;
  private int sizeX = 0;
  private int sizeY = 0;
  private int sizeZ = 0;
  private float sizeAdd = 0.0F;
  private float minU = 0.0F;
  private float minV = 0.0F;
  private float maxU = 0.0F;
  private float maxV = 0.0F;
  
  public ModelSprite(ModelRenderer modelRenderer, int textureOffsetX, int textureOffsetY, float posX, float posY, float posZ, int sizeX, int sizeY, int sizeZ, float sizeAdd)
  {
    this.modelRenderer = modelRenderer;
    this.textureOffsetX = textureOffsetX;
    this.textureOffsetY = textureOffsetY;
    this.posX = posX;
    this.posY = posY;
    this.posZ = posZ;
    this.sizeX = sizeX;
    this.sizeY = sizeY;
    this.sizeZ = sizeZ;
    this.sizeAdd = sizeAdd;
    this.minU = (textureOffsetX / modelRenderer.textureWidth);
    this.minV = (textureOffsetY / modelRenderer.textureHeight);
    this.maxU = ((textureOffsetX + sizeX) / modelRenderer.textureWidth);
    this.maxV = ((textureOffsetY + sizeY) / modelRenderer.textureHeight);
  }
  
  public void render(Tessellator tessellator, float scale)
  {
    GlStateManager.translate(this.posX * scale, this.posY * scale, this.posZ * scale);
    float rMinU = this.minU;
    float rMaxU = this.maxU;
    float rMinV = this.minV;
    float rMaxV = this.maxV;
    if (this.modelRenderer.mirror)
    {
      rMinU = this.maxU;
      rMaxU = this.minU;
    }
    if (this.modelRenderer.mirrorV)
    {
      rMinV = this.maxV;
      rMaxV = this.minV;
    }
    renderItemIn2D(tessellator, rMinU, rMinV, rMaxU, rMaxV, this.sizeX, this.sizeY, scale * this.sizeZ, this.modelRenderer.textureWidth, this.modelRenderer.textureHeight);
    GlStateManager.translate(-this.posX * scale, -this.posY * scale, -this.posZ * scale);
  }
  
  public static void renderItemIn2D(Tessellator tess, float minU, float minV, float maxU, float maxV, int sizeX, int sizeY, float width, float texWidth, float texHeight)
  {
    if (width < 6.25E-4F) {
      width = 6.25E-4F;
    }
    float dU = maxU - minU;
    float dV = maxV - minV;
    double dimX = MathHelper.abs(dU) * (texWidth / 16.0F);
    double dimY = MathHelper.abs(dV) * (texHeight / 16.0F);
    WorldRenderer tessellator = tess.getWorldRenderer();
    tessellator.startDrawingQuads();
    tessellator.func_178980_d(0.0F, 0.0F, -1.0F);
    tessellator.addVertexWithUV(0.0D, 0.0D, 0.0D, minU, minV);
    tessellator.addVertexWithUV(dimX, 0.0D, 0.0D, maxU, minV);
    tessellator.addVertexWithUV(dimX, dimY, 0.0D, maxU, maxV);
    tessellator.addVertexWithUV(0.0D, dimY, 0.0D, minU, maxV);
    tess.draw();
    tessellator.startDrawingQuads();
    tessellator.func_178980_d(0.0F, 0.0F, 1.0F);
    tessellator.addVertexWithUV(0.0D, dimY, width, minU, maxV);
    tessellator.addVertexWithUV(dimX, dimY, width, maxU, maxV);
    tessellator.addVertexWithUV(dimX, 0.0D, width, maxU, minV);
    tessellator.addVertexWithUV(0.0D, 0.0D, width, minU, minV);
    tess.draw();
    float var8 = 0.5F * dU / sizeX;
    float var9 = 0.5F * dV / sizeY;
    tessellator.startDrawingQuads();
    tessellator.func_178980_d(-1.0F, 0.0F, 0.0F);
    for (int var10 = 0; var10 < sizeX; var10++)
    {
      float var11 = var10 / sizeX;
      float var12 = minU + dU * var11 + var8;
      tessellator.addVertexWithUV(var11 * dimX, 0.0D, width, var12, minV);
      tessellator.addVertexWithUV(var11 * dimX, 0.0D, 0.0D, var12, minV);
      tessellator.addVertexWithUV(var11 * dimX, dimY, 0.0D, var12, maxV);
      tessellator.addVertexWithUV(var11 * dimX, dimY, width, var12, maxV);
    }
    tess.draw();
    tessellator.startDrawingQuads();
    tessellator.func_178980_d(1.0F, 0.0F, 0.0F);
    for (var10 = 0; var10 < sizeX; var10++)
    {
      float var11 = var10 / sizeX;
      float var12 = minU + dU * var11 + var8;
      float var13 = var11 + 1.0F / sizeX;
      tessellator.addVertexWithUV(var13 * dimX, dimY, width, var12, maxV);
      tessellator.addVertexWithUV(var13 * dimX, dimY, 0.0D, var12, maxV);
      tessellator.addVertexWithUV(var13 * dimX, 0.0D, 0.0D, var12, minV);
      tessellator.addVertexWithUV(var13 * dimX, 0.0D, width, var12, minV);
    }
    tess.draw();
    tessellator.startDrawingQuads();
    tessellator.func_178980_d(0.0F, 1.0F, 0.0F);
    for (var10 = 0; var10 < sizeY; var10++)
    {
      float var11 = var10 / sizeY;
      float var12 = minV + dV * var11 + var9;
      float var13 = var11 + 1.0F / sizeY;
      tessellator.addVertexWithUV(0.0D, var13 * dimY, 0.0D, minU, var12);
      tessellator.addVertexWithUV(dimX, var13 * dimY, 0.0D, maxU, var12);
      tessellator.addVertexWithUV(dimX, var13 * dimY, width, maxU, var12);
      tessellator.addVertexWithUV(0.0D, var13 * dimY, width, minU, var12);
    }
    tess.draw();
    tessellator.startDrawingQuads();
    tessellator.func_178980_d(0.0F, -1.0F, 0.0F);
    for (var10 = 0; var10 < sizeY; var10++)
    {
      float var11 = var10 / sizeY;
      float var12 = minV + dV * var11 + var9;
      tessellator.addVertexWithUV(dimX, var11 * dimY, 0.0D, maxU, var12);
      tessellator.addVertexWithUV(0.0D, var11 * dimY, 0.0D, minU, var12);
      tessellator.addVertexWithUV(0.0D, var11 * dimY, width, minU, var12);
      tessellator.addVertexWithUV(dimX, var11 * dimY, width, maxU, var12);
    }
    tess.draw();
  }
}
