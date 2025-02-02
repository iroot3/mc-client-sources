package net.minecraft.block;

import java.util.List;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemLead;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockFence
  extends Block
{
  public static final PropertyBool NORTH = PropertyBool.create("north");
  public static final PropertyBool EAST = PropertyBool.create("east");
  public static final PropertyBool SOUTH = PropertyBool.create("south");
  public static final PropertyBool WEST = PropertyBool.create("west");
  private static final String __OBFID = "CL_00000242";
  
  public BlockFence(Material p_i45721_1_)
  {
    super(p_i45721_1_);
    setDefaultState(this.blockState.getBaseState().withProperty(NORTH, Boolean.valueOf(false)).withProperty(EAST, Boolean.valueOf(false)).withProperty(SOUTH, Boolean.valueOf(false)).withProperty(WEST, Boolean.valueOf(false)));
    setCreativeTab(CreativeTabs.tabDecorations);
  }
  
  public void addCollisionBoxesToList(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List list, Entity collidingEntity)
  {
    boolean var7 = func_176524_e(worldIn, pos.offsetNorth());
    boolean var8 = func_176524_e(worldIn, pos.offsetSouth());
    boolean var9 = func_176524_e(worldIn, pos.offsetWest());
    boolean var10 = func_176524_e(worldIn, pos.offsetEast());
    float var11 = 0.375F;
    float var12 = 0.625F;
    float var13 = 0.375F;
    float var14 = 0.625F;
    if (var7) {
      var13 = 0.0F;
    }
    if (var8) {
      var14 = 1.0F;
    }
    if ((var7) || (var8))
    {
      setBlockBounds(var11, 0.0F, var13, var12, 1.5F, var14);
      super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
    }
    var13 = 0.375F;
    var14 = 0.625F;
    if (var9) {
      var11 = 0.0F;
    }
    if (var10) {
      var12 = 1.0F;
    }
    if ((var9) || (var10) || ((!var7) && (!var8)))
    {
      setBlockBounds(var11, 0.0F, var13, var12, 1.5F, var14);
      super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
    }
    if (var7) {
      var13 = 0.0F;
    }
    if (var8) {
      var14 = 1.0F;
    }
    setBlockBounds(var11, 0.0F, var13, var12, 1.0F, var14);
  }
  
  public void setBlockBoundsBasedOnState(IBlockAccess access, BlockPos pos)
  {
    boolean var3 = func_176524_e(access, pos.offsetNorth());
    boolean var4 = func_176524_e(access, pos.offsetSouth());
    boolean var5 = func_176524_e(access, pos.offsetWest());
    boolean var6 = func_176524_e(access, pos.offsetEast());
    float var7 = 0.375F;
    float var8 = 0.625F;
    float var9 = 0.375F;
    float var10 = 0.625F;
    if (var3) {
      var9 = 0.0F;
    }
    if (var4) {
      var10 = 1.0F;
    }
    if (var5) {
      var7 = 0.0F;
    }
    if (var6) {
      var8 = 1.0F;
    }
    setBlockBounds(var7, 0.0F, var9, var8, 1.0F, var10);
  }
  
  public boolean isOpaqueCube()
  {
    return false;
  }
  
  public boolean isFullCube()
  {
    return false;
  }
  
  public boolean isPassable(IBlockAccess blockAccess, BlockPos pos)
  {
    return false;
  }
  
  public boolean func_176524_e(IBlockAccess p_176524_1_, BlockPos p_176524_2_)
  {
    Block var3 = p_176524_1_.getBlockState(p_176524_2_).getBlock();
    return var3 != Blocks.barrier;
  }
  
  public boolean shouldSideBeRendered(IBlockAccess worldIn, BlockPos pos, EnumFacing side)
  {
    return true;
  }
  
  public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ)
  {
    return worldIn.isRemote ? true : ItemLead.func_180618_a(playerIn, worldIn, pos);
  }
  
  public int getMetaFromState(IBlockState state)
  {
    return 0;
  }
  
  public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
  {
    return state.withProperty(NORTH, Boolean.valueOf(func_176524_e(worldIn, pos.offsetNorth()))).withProperty(EAST, Boolean.valueOf(func_176524_e(worldIn, pos.offsetEast()))).withProperty(SOUTH, Boolean.valueOf(func_176524_e(worldIn, pos.offsetSouth()))).withProperty(WEST, Boolean.valueOf(func_176524_e(worldIn, pos.offsetWest())));
  }
  
  protected BlockState createBlockState()
  {
    return new BlockState(this, new IProperty[] { NORTH, EAST, WEST, SOUTH });
  }
}
