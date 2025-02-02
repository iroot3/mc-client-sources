/*    */ package net.minecraft.scoreboard;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.util.EnumChatFormatting;
/*    */ 
/*    */ public class GoalColor
/*    */   implements IScoreObjectiveCriteria
/*    */ {
/*    */   private final String goalName;
/*    */   
/*    */   public GoalColor(String p_i45549_1_, EnumChatFormatting p_i45549_2_) {
/* 13 */     this.goalName = String.valueOf(p_i45549_1_) + p_i45549_2_.getFriendlyName();
/* 14 */     IScoreObjectiveCriteria.INSTANCES.put(this.goalName, this);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 19 */     return this.goalName;
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_96635_a(List<EntityPlayer> p_96635_1_) {
/* 24 */     return 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isReadOnly() {
/* 29 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public IScoreObjectiveCriteria.EnumRenderType getRenderType() {
/* 34 */     return IScoreObjectiveCriteria.EnumRenderType.INTEGER;
/*    */   }
/*    */ }


/* Location:              C:\Users\mymon\AppData\Roaming\.minecraft\versions\Hera\Hera.jar!\net\minecraft\scoreboard\GoalColor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */