/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package optifine;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.ITickableTextureObject;
import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.optifine.entity.model.CustomEntityModels;
import optifine.BetterGrass;
import optifine.BetterSnow;
import optifine.Config;
import optifine.CustomColors;
import optifine.CustomGuis;
import optifine.CustomItems;
import optifine.CustomPanorama;
import optifine.CustomSky;
import optifine.Lang;
import optifine.NaturalTextures;
import optifine.RandomMobs;
import optifine.SmartLeaves;
import optifine.TextureAnimations;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;
import shadersmod.client.MultiTexID;
import shadersmod.client.Shaders;

public class TextureUtils {
    public static final String texGrassTop = "grass_top";
    public static final String texStone = "stone";
    public static final String texDirt = "dirt";
    public static final String texCoarseDirt = "coarse_dirt";
    public static final String texGrassSide = "grass_side";
    public static final String texStoneslabSide = "stone_slab_side";
    public static final String texStoneslabTop = "stone_slab_top";
    public static final String texBedrock = "bedrock";
    public static final String texSand = "sand";
    public static final String texGravel = "gravel";
    public static final String texLogOak = "log_oak";
    public static final String texLogBigOak = "log_big_oak";
    public static final String texLogAcacia = "log_acacia";
    public static final String texLogSpruce = "log_spruce";
    public static final String texLogBirch = "log_birch";
    public static final String texLogJungle = "log_jungle";
    public static final String texLogOakTop = "log_oak_top";
    public static final String texLogBigOakTop = "log_big_oak_top";
    public static final String texLogAcaciaTop = "log_acacia_top";
    public static final String texLogSpruceTop = "log_spruce_top";
    public static final String texLogBirchTop = "log_birch_top";
    public static final String texLogJungleTop = "log_jungle_top";
    public static final String texLeavesOak = "leaves_oak";
    public static final String texLeavesBigOak = "leaves_big_oak";
    public static final String texLeavesAcacia = "leaves_acacia";
    public static final String texLeavesBirch = "leaves_birch";
    public static final String texLeavesSpuce = "leaves_spruce";
    public static final String texLeavesJungle = "leaves_jungle";
    public static final String texGoldOre = "gold_ore";
    public static final String texIronOre = "iron_ore";
    public static final String texCoalOre = "coal_ore";
    public static final String texObsidian = "obsidian";
    public static final String texGrassSideOverlay = "grass_side_overlay";
    public static final String texSnow = "snow";
    public static final String texGrassSideSnowed = "grass_side_snowed";
    public static final String texMyceliumSide = "mycelium_side";
    public static final String texMyceliumTop = "mycelium_top";
    public static final String texDiamondOre = "diamond_ore";
    public static final String texRedstoneOre = "redstone_ore";
    public static final String texLapisOre = "lapis_ore";
    public static final String texCactusSide = "cactus_side";
    public static final String texClay = "clay";
    public static final String texFarmlandWet = "farmland_wet";
    public static final String texFarmlandDry = "farmland_dry";
    public static final String texNetherrack = "netherrack";
    public static final String texSoulSand = "soul_sand";
    public static final String texGlowstone = "glowstone";
    public static final String texLeavesSpruce = "leaves_spruce";
    public static final String texLeavesSpruceOpaque = "leaves_spruce_opaque";
    public static final String texEndStone = "end_stone";
    public static final String texSandstoneTop = "sandstone_top";
    public static final String texSandstoneBottom = "sandstone_bottom";
    public static final String texRedstoneLampOff = "redstone_lamp_off";
    public static final String texRedstoneLampOn = "redstone_lamp_on";
    public static final String texWaterStill = "water_still";
    public static final String texWaterFlow = "water_flow";
    public static final String texLavaStill = "lava_still";
    public static final String texLavaFlow = "lava_flow";
    public static final String texFireLayer0 = "fire_layer_0";
    public static final String texFireLayer1 = "fire_layer_1";
    public static final String texPortal = "portal";
    public static final String texGlass = "glass";
    public static final String texGlassPaneTop = "glass_pane_top";
    public static final String texCompass = "compass";
    public static final String texClock = "clock";
    public static TextureAtlasSprite iconGrassTop;
    public static TextureAtlasSprite iconGrassSide;
    public static TextureAtlasSprite iconGrassSideOverlay;
    public static TextureAtlasSprite iconSnow;
    public static TextureAtlasSprite iconGrassSideSnowed;
    public static TextureAtlasSprite iconMyceliumSide;
    public static TextureAtlasSprite iconMyceliumTop;
    public static TextureAtlasSprite iconWaterStill;
    public static TextureAtlasSprite iconWaterFlow;
    public static TextureAtlasSprite iconLavaStill;
    public static TextureAtlasSprite iconLavaFlow;
    public static TextureAtlasSprite iconPortal;
    public static TextureAtlasSprite iconFireLayer0;
    public static TextureAtlasSprite iconFireLayer1;
    public static TextureAtlasSprite iconGlass;
    public static TextureAtlasSprite iconGlassPaneTop;
    public static TextureAtlasSprite iconCompass;
    public static TextureAtlasSprite iconClock;
    public static final String SPRITE_PREFIX_BLOCKS = "minecraft:blocks/";
    public static final String SPRITE_PREFIX_ITEMS = "minecraft:items/";
    private static IntBuffer staticBuffer;

    public static void update() {
        TextureMap texturemap = TextureUtils.getTextureMapBlocks();
        if (texturemap != null) {
            String s = SPRITE_PREFIX_BLOCKS;
            iconGrassTop = texturemap.getSpriteSafe(s + texGrassTop);
            iconGrassSide = texturemap.getSpriteSafe(s + texGrassSide);
            iconGrassSideOverlay = texturemap.getSpriteSafe(s + texGrassSideOverlay);
            iconSnow = texturemap.getSpriteSafe(s + texSnow);
            iconGrassSideSnowed = texturemap.getSpriteSafe(s + texGrassSideSnowed);
            iconMyceliumSide = texturemap.getSpriteSafe(s + texMyceliumSide);
            iconMyceliumTop = texturemap.getSpriteSafe(s + texMyceliumTop);
            iconWaterStill = texturemap.getSpriteSafe(s + texWaterStill);
            iconWaterFlow = texturemap.getSpriteSafe(s + texWaterFlow);
            iconLavaStill = texturemap.getSpriteSafe(s + texLavaStill);
            iconLavaFlow = texturemap.getSpriteSafe(s + texLavaFlow);
            iconFireLayer0 = texturemap.getSpriteSafe(s + texFireLayer0);
            iconFireLayer1 = texturemap.getSpriteSafe(s + texFireLayer1);
            iconPortal = texturemap.getSpriteSafe(s + texPortal);
            iconGlass = texturemap.getSpriteSafe(s + texGlass);
            iconGlassPaneTop = texturemap.getSpriteSafe(s + texGlassPaneTop);
            String s1 = SPRITE_PREFIX_ITEMS;
            iconCompass = texturemap.getSpriteSafe(s1 + texCompass);
            iconClock = texturemap.getSpriteSafe(s1 + texClock);
        }
    }

    public static BufferedImage fixTextureDimensions(String p_fixTextureDimensions_0_, BufferedImage p_fixTextureDimensions_1_) {
        int j;
        int i;
        if ((p_fixTextureDimensions_0_.startsWith("/mob/zombie") || p_fixTextureDimensions_0_.startsWith("/mob/pigzombie")) && (i = p_fixTextureDimensions_1_.getWidth()) == (j = p_fixTextureDimensions_1_.getHeight()) * 2) {
            BufferedImage bufferedimage = new BufferedImage(i, j * 2, 2);
            Graphics2D graphics2d = bufferedimage.createGraphics();
            graphics2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            graphics2d.drawImage(p_fixTextureDimensions_1_, 0, 0, i, j, null);
            return bufferedimage;
        }
        return p_fixTextureDimensions_1_;
    }

    public static int ceilPowerOfTwo(int p_ceilPowerOfTwo_0_) {
        int i;
        for (i = 1; i < p_ceilPowerOfTwo_0_; i *= 2) {
        }
        return i;
    }

    public static int getPowerOfTwo(int p_getPowerOfTwo_0_) {
        int i = 1;
        int j = 0;
        while (i < p_getPowerOfTwo_0_) {
            i *= 2;
            ++j;
        }
        return j;
    }

    public static int twoToPower(int p_twoToPower_0_) {
        int i = 1;
        for (int j = 0; j < p_twoToPower_0_; ++j) {
            i *= 2;
        }
        return i;
    }

    public static ITextureObject getTexture(ResourceLocation p_getTexture_0_) {
        ITextureObject itextureobject = Config.getTextureManager().getTexture(p_getTexture_0_);
        if (itextureobject != null) {
            return itextureobject;
        }
        if (!Config.hasResource(p_getTexture_0_)) {
            return null;
        }
        SimpleTexture simpletexture = new SimpleTexture(p_getTexture_0_);
        Config.getTextureManager().loadTexture(p_getTexture_0_, simpletexture);
        return simpletexture;
    }

    public static void resourcesReloaded(IResourceManager p_resourcesReloaded_0_) {
        if (TextureUtils.getTextureMapBlocks() != null) {
            Config.dbg("*** Reloading custom textures ***");
            CustomSky.reset();
            TextureAnimations.reset();
            TextureUtils.update();
            NaturalTextures.update();
            BetterGrass.update();
            BetterSnow.update();
            TextureAnimations.update();
            CustomColors.update();
            CustomSky.update();
            RandomMobs.resetTextures();
            CustomItems.updateModels();
            CustomEntityModels.update();
            Shaders.resourcesReloaded();
            Lang.resourcesReloaded();
            Config.updateTexturePackClouds();
            SmartLeaves.updateLeavesModels();
            CustomPanorama.update();
            CustomGuis.update();
            Config.getTextureManager().tick();
        }
    }

    public static TextureMap getTextureMapBlocks() {
        return Minecraft.getMinecraft().getTextureMapBlocks();
    }

    public static void registerResourceListener() {
        IResourceManager iresourcemanager = Config.getResourceManager();
        if (iresourcemanager instanceof IReloadableResourceManager) {
            IReloadableResourceManager ireloadableresourcemanager = (IReloadableResourceManager)iresourcemanager;
            IResourceManagerReloadListener iresourcemanagerreloadlistener = new IResourceManagerReloadListener(){

                @Override
                public void onResourceManagerReload(IResourceManager resourceManager) {
                    TextureUtils.resourcesReloaded(resourceManager);
                }
            };
            ireloadableresourcemanager.registerReloadListener(iresourcemanagerreloadlistener);
        }
        ITickableTextureObject itickabletextureobject = new ITickableTextureObject(){

            @Override
            public void tick() {
                TextureAnimations.updateCustomAnimations();
            }

            @Override
            public void loadTexture(IResourceManager resourceManager) throws IOException {
            }

            @Override
            public int getGlTextureId() {
                return 0;
            }

            @Override
            public void setBlurMipmap(boolean blurIn, boolean mipmapIn) {
            }

            @Override
            public void restoreLastBlurMipmap() {
            }

            @Override
            public MultiTexID getMultiTexID() {
                return null;
            }
        };
        ResourceLocation resourcelocation = new ResourceLocation("optifine/TickableTextures");
        Config.getTextureManager().loadTickableTexture(resourcelocation, itickabletextureobject);
    }

    public static ResourceLocation fixResourceLocation(ResourceLocation p_fixResourceLocation_0_, String p_fixResourceLocation_1_) {
        if (!p_fixResourceLocation_0_.getResourceDomain().equals("minecraft")) {
            return p_fixResourceLocation_0_;
        }
        String s = p_fixResourceLocation_0_.getResourcePath();
        String s1 = TextureUtils.fixResourcePath(s, p_fixResourceLocation_1_);
        if (s1 != s) {
            p_fixResourceLocation_0_ = new ResourceLocation(p_fixResourceLocation_0_.getResourceDomain(), s1);
        }
        return p_fixResourceLocation_0_;
    }

    public static String fixResourcePath(String p_fixResourcePath_0_, String p_fixResourcePath_1_) {
        String s = "assets/minecraft/";
        if (((String)p_fixResourcePath_0_).startsWith(s)) {
            p_fixResourcePath_0_ = ((String)p_fixResourcePath_0_).substring(s.length());
            return p_fixResourcePath_0_;
        }
        if (((String)p_fixResourcePath_0_).startsWith("./")) {
            p_fixResourcePath_0_ = ((String)p_fixResourcePath_0_).substring(2);
            if (!((String)p_fixResourcePath_1_).endsWith("/")) {
                p_fixResourcePath_1_ = (String)p_fixResourcePath_1_ + "/";
            }
            p_fixResourcePath_0_ = (String)p_fixResourcePath_1_ + (String)p_fixResourcePath_0_;
            return p_fixResourcePath_0_;
        }
        if (((String)p_fixResourcePath_0_).startsWith("/~")) {
            p_fixResourcePath_0_ = ((String)p_fixResourcePath_0_).substring(1);
        }
        String s1 = "mcpatcher/";
        if (((String)p_fixResourcePath_0_).startsWith("~/")) {
            p_fixResourcePath_0_ = ((String)p_fixResourcePath_0_).substring(2);
            p_fixResourcePath_0_ = s1 + (String)p_fixResourcePath_0_;
            return p_fixResourcePath_0_;
        }
        if (((String)p_fixResourcePath_0_).startsWith("/")) {
            p_fixResourcePath_0_ = s1 + ((String)p_fixResourcePath_0_).substring(1);
            return p_fixResourcePath_0_;
        }
        return p_fixResourcePath_0_;
    }

    public static String getBasePath(String p_getBasePath_0_) {
        int i = p_getBasePath_0_.lastIndexOf(47);
        return i < 0 ? "" : p_getBasePath_0_.substring(0, i);
    }

    public static void applyAnisotropicLevel() {
        if (GLContext.getCapabilities().GL_EXT_texture_filter_anisotropic) {
            float f = GL11.glGetFloat(34047);
            float f1 = Config.getAnisotropicFilterLevel();
            f1 = Math.min(f1, f);
            GL11.glTexParameterf(3553, 34046, f1);
        }
    }

    public static void bindTexture(int p_bindTexture_0_) {
        GlStateManager.bindTexture(p_bindTexture_0_);
    }

    public static boolean isPowerOfTwo(int p_isPowerOfTwo_0_) {
        int i = MathHelper.smallestEncompassingPowerOfTwo(p_isPowerOfTwo_0_);
        return i == p_isPowerOfTwo_0_;
    }

    public static BufferedImage scaleImage(BufferedImage p_scaleImage_0_, int p_scaleImage_1_) {
        int i = p_scaleImage_0_.getWidth();
        int j = p_scaleImage_0_.getHeight();
        int k = j * p_scaleImage_1_ / i;
        BufferedImage bufferedimage = new BufferedImage(p_scaleImage_1_, k, 2);
        Graphics2D graphics2d = bufferedimage.createGraphics();
        Object object = RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR;
        if (p_scaleImage_1_ < i || p_scaleImage_1_ % i != 0) {
            object = RenderingHints.VALUE_INTERPOLATION_BILINEAR;
        }
        graphics2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, object);
        graphics2d.drawImage(p_scaleImage_0_, 0, 0, p_scaleImage_1_, k, null);
        return bufferedimage;
    }

    public static BufferedImage scaleToPowerOfTwo(BufferedImage p_scaleToPowerOfTwo_0_, int p_scaleToPowerOfTwo_1_) {
        if (p_scaleToPowerOfTwo_0_ == null) {
            return p_scaleToPowerOfTwo_0_;
        }
        int i = p_scaleToPowerOfTwo_0_.getWidth();
        int j = TextureUtils.scaleToPowerOfTwo(i, p_scaleToPowerOfTwo_1_);
        if (j == i) {
            return p_scaleToPowerOfTwo_0_;
        }
        BufferedImage bufferedimage = TextureUtils.scaleImage(p_scaleToPowerOfTwo_0_, j);
        return bufferedimage;
    }

    public static int scaleToPowerOfTwo(int p_scaleToPowerOfTwo_0_, int p_scaleToPowerOfTwo_1_) {
        int i = Math.max(p_scaleToPowerOfTwo_0_, p_scaleToPowerOfTwo_1_);
        i = MathHelper.smallestEncompassingPowerOfTwo(i);
        return i;
    }

    public static int scaleMinTo(int p_scaleMinTo_0_, int p_scaleMinTo_1_) {
        int i;
        if (p_scaleMinTo_0_ >= p_scaleMinTo_1_) {
            return p_scaleMinTo_0_;
        }
        for (i = p_scaleMinTo_0_; i < p_scaleMinTo_1_; i *= 2) {
        }
        return i;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static Dimension getImageSize(InputStream p_getImageSize_0_, String p_getImageSize_1_) {
        Iterator<ImageReader> iterator = ImageIO.getImageReadersBySuffix(p_getImageSize_1_);
        while (iterator.hasNext()) {
            Dimension dimension;
            ImageReader imagereader = iterator.next();
            try {
                ImageInputStream imageinputstream = ImageIO.createImageInputStream(p_getImageSize_0_);
                imagereader.setInput(imageinputstream);
                int i = imagereader.getWidth(imagereader.getMinIndex());
                int j = imagereader.getHeight(imagereader.getMinIndex());
                dimension = new Dimension(i, j);
            } catch (IOException var11) {}
            continue;
            finally {
                imagereader.dispose();
                continue;
            }
            return dimension;
        }
        return null;
    }

    public static void dbgMipmaps(TextureAtlasSprite p_dbgMipmaps_0_) {
        int[][] aint = p_dbgMipmaps_0_.getFrameTextureData(0);
        for (int i = 0; i < aint.length; ++i) {
            int[] aint1 = aint[i];
            if (aint1 == null) {
                Config.dbg(i + ": " + aint1);
                continue;
            }
            Config.dbg(i + ": " + aint1.length);
        }
    }

    public static void saveGlTexture(String p_saveGlTexture_0_, int p_saveGlTexture_1_, int p_saveGlTexture_2_, int p_saveGlTexture_3_, int p_saveGlTexture_4_) {
        TextureUtils.bindTexture(p_saveGlTexture_1_);
        GL11.glPixelStorei(3333, 1);
        GL11.glPixelStorei(3317, 1);
        File file1 = new File(p_saveGlTexture_0_);
        File file2 = file1.getParentFile();
        if (file2 != null) {
            file2.mkdirs();
        }
        for (int i = 0; i < 16; ++i) {
            File file3 = new File(p_saveGlTexture_0_ + "_" + i + ".png");
            file3.delete();
        }
        for (int i1 = 0; i1 <= p_saveGlTexture_2_; ++i1) {
            File file4 = new File(p_saveGlTexture_0_ + "_" + i1 + ".png");
            int j = p_saveGlTexture_3_ >> i1;
            int k = p_saveGlTexture_4_ >> i1;
            int l = j * k;
            IntBuffer intbuffer = BufferUtils.createIntBuffer(l);
            int[] aint = new int[l];
            GL11.glGetTexImage(3553, i1, 32993, 33639, intbuffer);
            intbuffer.get(aint);
            BufferedImage bufferedimage = new BufferedImage(j, k, 2);
            bufferedimage.setRGB(0, 0, j, k, aint, 0, j);
            try {
                ImageIO.write((RenderedImage)bufferedimage, "png", file4);
                Config.dbg("Exported: " + file4);
                continue;
            } catch (Exception exception) {
                Config.warn("Error writing: " + file4);
                Config.warn(exception.getClass().getName() + ": " + exception.getMessage());
            }
        }
    }

    public static void generateCustomMipmaps(TextureAtlasSprite p_generateCustomMipmaps_0_, int p_generateCustomMipmaps_1_) {
        int i = p_generateCustomMipmaps_0_.getIconWidth();
        int j = p_generateCustomMipmaps_0_.getIconHeight();
        if (p_generateCustomMipmaps_0_.getFrameCount() < 1) {
            ArrayList<int[][]> list = new ArrayList<int[][]>();
            int[][] aint = new int[p_generateCustomMipmaps_1_ + 1][];
            int[] aint1 = new int[i * j];
            aint[0] = aint1;
            list.add(aint);
            p_generateCustomMipmaps_0_.setFramesTextureData(list);
        }
        ArrayList<int[][]> list1 = new ArrayList<int[][]>();
        int l = p_generateCustomMipmaps_0_.getFrameCount();
        for (int i1 = 0; i1 < l; ++i1) {
            int[] aint2 = TextureUtils.getFrameData(p_generateCustomMipmaps_0_, i1, 0);
            if (aint2 == null || aint2.length < 1) {
                aint2 = new int[i * j];
            }
            if (aint2.length != i * j) {
                int k = (int)Math.round(Math.sqrt(aint2.length));
                if (k * k != aint2.length) {
                    aint2 = new int[1];
                    k = 1;
                }
                BufferedImage bufferedimage = new BufferedImage(k, k, 2);
                bufferedimage.setRGB(0, 0, k, k, aint2, 0, k);
                BufferedImage bufferedimage1 = TextureUtils.scaleImage(bufferedimage, i);
                int[] aint3 = new int[i * j];
                bufferedimage1.getRGB(0, 0, i, j, aint3, 0, i);
                aint2 = aint3;
            }
            int[][] aint4 = new int[p_generateCustomMipmaps_1_ + 1][];
            aint4[0] = aint2;
            list1.add(aint4);
        }
        p_generateCustomMipmaps_0_.setFramesTextureData(list1);
        p_generateCustomMipmaps_0_.generateMipmaps(p_generateCustomMipmaps_1_);
    }

    public static int[] getFrameData(TextureAtlasSprite p_getFrameData_0_, int p_getFrameData_1_, int p_getFrameData_2_) {
        List<int[][]> list = p_getFrameData_0_.getFramesTextureData();
        if (list.size() <= p_getFrameData_1_) {
            return null;
        }
        int[][] aint = list.get(p_getFrameData_1_);
        if (aint != null && aint.length > p_getFrameData_2_) {
            int[] aint1 = aint[p_getFrameData_2_];
            return aint1;
        }
        return null;
    }

    public static int getGLMaximumTextureSize() {
        for (int i = 65536; i > 0; i >>= 1) {
            GlStateManager.glTexImage2D(32868, 0, 6408, i, i, 0, 6408, 5121, null);
            int j = GL11.glGetError();
            int k = GlStateManager.glGetTexLevelParameteri(32868, 0, 4096);
            if (k == 0) continue;
            return i;
        }
        return -1;
    }

    static {
        staticBuffer = GLAllocation.createDirectIntBuffer(256);
    }
}

