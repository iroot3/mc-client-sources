package net.minecraft.client.gui;

import java.io.IOException;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.client.audio.SoundEventAccessorComposite;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.stream.GuiStreamOptions;
import net.minecraft.client.gui.stream.GuiStreamUnavailable;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.GameSettings.Options;
import net.minecraft.client.stream.IStream;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.storage.WorldInfo;

public class GuiOptions
  extends GuiScreen
  implements GuiYesNoCallback
{
  private static final GameSettings.Options[] field_146440_f = { GameSettings.Options.FOV };
  private final GuiScreen field_146441_g;
  private final GameSettings game_settings_1;
  private GuiButton field_175357_i;
  private GuiLockIconButton field_175356_r;
  protected String field_146442_a = "Options";
  private static final String __OBFID = "CL_00000700";
  
  public GuiOptions(GuiScreen p_i1046_1_, GameSettings p_i1046_2_)
  {
    this.field_146441_g = p_i1046_1_;
    this.game_settings_1 = p_i1046_2_;
  }
  
  public void initGui()
  {
    int var1 = 0;
    this.field_146442_a = I18n.format("options.title", new Object[0]);
    GameSettings.Options[] var2 = field_146440_f;
    int var3 = var2.length;
    for (int var4 = 0; var4 < var3; var4++)
    {
      GameSettings.Options var5 = var2[var4];
      if (var5.getEnumFloat())
      {
        this.buttonList.add(new GuiOptionSlider(var5.returnEnumOrdinal(), width / 2 - 155 + var1 % 2 * 160, height / 6 - 12 + 24 * (var1 >> 1), var5));
      }
      else
      {
        GuiOptionButton var6 = new GuiOptionButton(var5.returnEnumOrdinal(), width / 2 - 155 + var1 % 2 * 160, height / 6 - 12 + 24 * (var1 >> 1), var5, this.game_settings_1.getKeyBinding(var5));
        this.buttonList.add(var6);
      }
      var1++;
    }
    if (Minecraft.theWorld != null)
    {
      EnumDifficulty var7 = Minecraft.theWorld.getDifficulty();
      this.field_175357_i = new GuiButton(108, width / 2 - 155 + var1 % 2 * 160, height / 6 - 12 + 24 * (var1 >> 1), 150, 20, func_175355_a(var7));
      this.buttonList.add(this.field_175357_i);
      if ((this.mc.isSingleplayer()) && (!Minecraft.theWorld.getWorldInfo().isHardcoreModeEnabled()))
      {
        this.field_175357_i.func_175211_a(this.field_175357_i.getButtonWidth() - 20);
        this.field_175356_r = new GuiLockIconButton(109, this.field_175357_i.xPosition + this.field_175357_i.getButtonWidth(), this.field_175357_i.yPosition);
        this.buttonList.add(this.field_175356_r);
        this.field_175356_r.func_175229_b(Minecraft.theWorld.getWorldInfo().isDifficultyLocked());
        this.field_175356_r.enabled = (!this.field_175356_r.func_175230_c());
        this.field_175357_i.enabled = (!this.field_175356_r.func_175230_c());
      }
      else
      {
        this.field_175357_i.enabled = false;
      }
    }
    this.buttonList.add(new GuiButton(110, width / 2 - 155, height / 6 + 48 - 6, 150, 20, I18n.format("options.skinCustomisation", new Object[0])));
    this.buttonList.add(new GuiButton(8675309, width / 2 + 5, height / 6 + 48 - 6, 150, 20, "Super Secret Settings...")
    {
      private static final String __OBFID = "CL_00000701";
      
      public void playPressSound(SoundHandler soundHandlerIn)
      {
        SoundEventAccessorComposite var2 = soundHandlerIn.getRandomSoundFromCategories(new SoundCategory[] { SoundCategory.ANIMALS, SoundCategory.BLOCKS, SoundCategory.MOBS, SoundCategory.PLAYERS, SoundCategory.WEATHER });
        if (var2 != null) {
          soundHandlerIn.playSound(PositionedSoundRecord.createPositionedSoundRecord(var2.getSoundEventLocation(), 0.5F));
        }
      }
    });
    this.buttonList.add(new GuiButton(106, width / 2 - 155, height / 6 + 72 - 6, 150, 20, I18n.format("options.sounds", new Object[0])));
    this.buttonList.add(new GuiButton(107, width / 2 + 5, height / 6 + 72 - 6, 150, 20, I18n.format("options.stream", new Object[0])));
    this.buttonList.add(new GuiButton(101, width / 2 - 155, height / 6 + 96 - 6, 150, 20, I18n.format("options.video", new Object[0])));
    this.buttonList.add(new GuiButton(100, width / 2 + 5, height / 6 + 96 - 6, 150, 20, I18n.format("options.controls", new Object[0])));
    this.buttonList.add(new GuiButton(102, width / 2 - 155, height / 6 + 120 - 6, 150, 20, I18n.format("options.language", new Object[0])));
    this.buttonList.add(new GuiButton(103, width / 2 + 5, height / 6 + 120 - 6, 150, 20, I18n.format("options.multiplayer.title", new Object[0])));
    this.buttonList.add(new GuiButton(105, width / 2 - 155, height / 6 + 144 - 6, 150, 20, I18n.format("options.resourcepack", new Object[0])));
    this.buttonList.add(new GuiButton(104, width / 2 + 5, height / 6 + 144 - 6, 150, 20, I18n.format("options.snooper.view", new Object[0])));
    this.buttonList.add(new GuiButton(200, width / 2 - 100, height / 6 + 168, I18n.format("gui.done", new Object[0])));
  }
  
  public String func_175355_a(EnumDifficulty p_175355_1_)
  {
    ChatComponentText var2 = new ChatComponentText("");
    var2.appendSibling(new ChatComponentTranslation("options.difficulty", new Object[0]));
    var2.appendText(": ");
    var2.appendSibling(new ChatComponentTranslation(p_175355_1_.getDifficultyResourceKey(), new Object[0]));
    return var2.getFormattedText();
  }
  
  public void confirmClicked(boolean result, int id)
  {
    this.mc.displayGuiScreen(this);
    if ((id == 109) && (result) && (Minecraft.theWorld != null))
    {
      Minecraft.theWorld.getWorldInfo().setDifficultyLocked(true);
      this.field_175356_r.func_175229_b(true);
      this.field_175356_r.enabled = false;
      this.field_175357_i.enabled = false;
    }
  }
  
  protected void actionPerformed(GuiButton button)
    throws IOException
  {
    if (button.enabled)
    {
      if ((button.id < 100) && ((button instanceof GuiOptionButton)))
      {
        GameSettings.Options var2 = ((GuiOptionButton)button).returnEnumOptions();
        this.game_settings_1.setOptionValue(var2, 1);
        button.displayString = this.game_settings_1.getKeyBinding(GameSettings.Options.getEnumOptions(button.id));
      }
      if (button.id == 108)
      {
        Minecraft.theWorld.getWorldInfo().setDifficulty(EnumDifficulty.getDifficultyEnum(Minecraft.theWorld.getDifficulty().getDifficultyId() + 1));
        this.field_175357_i.displayString = func_175355_a(Minecraft.theWorld.getDifficulty());
      }
      if (button.id == 109) {
        this.mc.displayGuiScreen(new GuiYesNo(this, new ChatComponentTranslation("difficulty.lock.title", new Object[0]).getFormattedText(), new ChatComponentTranslation("difficulty.lock.question", new Object[] { new ChatComponentTranslation(Minecraft.theWorld.getWorldInfo().getDifficulty().getDifficultyResourceKey(), new Object[0]) }).getFormattedText(), 109));
      }
      if (button.id == 110)
      {
        this.mc.gameSettings.saveOptions();
        this.mc.displayGuiScreen(new GuiCustomizeSkin(this));
      }
      if (button.id == 8675309) {
        this.mc.entityRenderer.activateNextShader();
      }
      if (button.id == 101)
      {
        this.mc.gameSettings.saveOptions();
        this.mc.displayGuiScreen(new GuiVideoSettings(this, this.game_settings_1));
      }
      if (button.id == 100)
      {
        this.mc.gameSettings.saveOptions();
        this.mc.displayGuiScreen(new GuiControls(this, this.game_settings_1));
      }
      if (button.id == 102)
      {
        this.mc.gameSettings.saveOptions();
        this.mc.displayGuiScreen(new GuiLanguage(this, this.game_settings_1, this.mc.getLanguageManager()));
      }
      if (button.id == 103)
      {
        this.mc.gameSettings.saveOptions();
        this.mc.displayGuiScreen(new ScreenChatOptions(this, this.game_settings_1));
      }
      if (button.id == 104)
      {
        this.mc.gameSettings.saveOptions();
        this.mc.displayGuiScreen(new GuiSnooper(this, this.game_settings_1));
      }
      if (button.id == 200)
      {
        this.mc.gameSettings.saveOptions();
        this.mc.displayGuiScreen(this.field_146441_g);
      }
      if (button.id == 105)
      {
        this.mc.gameSettings.saveOptions();
        this.mc.displayGuiScreen(new GuiScreenResourcePacks(this));
      }
      if (button.id == 106)
      {
        this.mc.gameSettings.saveOptions();
        this.mc.displayGuiScreen(new GuiScreenOptionsSounds(this, this.game_settings_1));
      }
      if (button.id == 107)
      {
        this.mc.gameSettings.saveOptions();
        IStream var3 = this.mc.getTwitchStream();
        if ((var3.func_152936_l()) && (var3.func_152928_D())) {
          this.mc.displayGuiScreen(new GuiStreamOptions(this, this.game_settings_1));
        } else {
          GuiStreamUnavailable.func_152321_a(this);
        }
      }
    }
  }
  
  public void drawScreen(int mouseX, int mouseY, float partialTicks)
  {
    drawDefaultBackground();
    drawCenteredString(this.fontRendererObj, this.field_146442_a, width / 2, 15, 16777215);
    super.drawScreen(mouseX, mouseY, partialTicks);
  }
}
