package net.minecraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockFenceGate extends BlockHorizontal {
   public static final PropertyBool field_176466_a = PropertyBool.func_177716_a("open");
   public static final PropertyBool field_176465_b = PropertyBool.func_177716_a("powered");
   public static final PropertyBool field_176467_M = PropertyBool.func_177716_a("in_wall");
   protected static final AxisAlignedBB field_185541_d = new AxisAlignedBB(0.0D, 0.0D, 0.375D, 1.0D, 1.0D, 0.625D);
   protected static final AxisAlignedBB field_185542_e = new AxisAlignedBB(0.375D, 0.0D, 0.0D, 0.625D, 1.0D, 1.0D);
   protected static final AxisAlignedBB field_185543_f = new AxisAlignedBB(0.0D, 0.0D, 0.375D, 1.0D, 0.8125D, 0.625D);
   protected static final AxisAlignedBB field_185544_g = new AxisAlignedBB(0.375D, 0.0D, 0.0D, 0.625D, 0.8125D, 1.0D);
   protected static final AxisAlignedBB field_185539_B = new AxisAlignedBB(0.0D, 0.0D, 0.375D, 1.0D, 1.5D, 0.625D);
   protected static final AxisAlignedBB field_185540_C = new AxisAlignedBB(0.375D, 0.0D, 0.0D, 0.625D, 1.5D, 1.0D);

   public BlockFenceGate(BlockPlanks.EnumType p_i46394_1_) {
      super(Material.field_151575_d, p_i46394_1_.func_181070_c());
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(field_176466_a, Boolean.valueOf(false)).func_177226_a(field_176465_b, Boolean.valueOf(false)).func_177226_a(field_176467_M, Boolean.valueOf(false)));
      this.func_149647_a(CreativeTabs.field_78028_d);
   }

   public AxisAlignedBB func_185496_a(IBlockState p_185496_1_, IBlockAccess p_185496_2_, BlockPos p_185496_3_) {
      p_185496_1_ = this.func_176221_a(p_185496_1_, p_185496_2_, p_185496_3_);
      return ((Boolean)p_185496_1_.func_177229_b(field_176467_M)).booleanValue()?(((EnumFacing)p_185496_1_.func_177229_b(field_185512_D)).func_176740_k() == EnumFacing.Axis.X?field_185544_g:field_185543_f):(((EnumFacing)p_185496_1_.func_177229_b(field_185512_D)).func_176740_k() == EnumFacing.Axis.X?field_185542_e:field_185541_d);
   }

   public IBlockState func_176221_a(IBlockState p_176221_1_, IBlockAccess p_176221_2_, BlockPos p_176221_3_) {
      EnumFacing.Axis enumfacing$axis = ((EnumFacing)p_176221_1_.func_177229_b(field_185512_D)).func_176740_k();
      if(enumfacing$axis == EnumFacing.Axis.Z && (p_176221_2_.func_180495_p(p_176221_3_.func_177976_e()).func_177230_c() == Blocks.field_150463_bK || p_176221_2_.func_180495_p(p_176221_3_.func_177974_f()).func_177230_c() == Blocks.field_150463_bK) || enumfacing$axis == EnumFacing.Axis.X && (p_176221_2_.func_180495_p(p_176221_3_.func_177978_c()).func_177230_c() == Blocks.field_150463_bK || p_176221_2_.func_180495_p(p_176221_3_.func_177968_d()).func_177230_c() == Blocks.field_150463_bK)) {
         p_176221_1_ = p_176221_1_.func_177226_a(field_176467_M, Boolean.valueOf(true));
      }

      return p_176221_1_;
   }

   public IBlockState func_185499_a(IBlockState p_185499_1_, Rotation p_185499_2_) {
      return p_185499_1_.func_177226_a(field_185512_D, p_185499_2_.func_185831_a((EnumFacing)p_185499_1_.func_177229_b(field_185512_D)));
   }

   public IBlockState func_185471_a(IBlockState p_185471_1_, Mirror p_185471_2_) {
      return p_185471_1_.func_185907_a(p_185471_2_.func_185800_a((EnumFacing)p_185471_1_.func_177229_b(field_185512_D)));
   }

   public boolean func_176196_c(World p_176196_1_, BlockPos p_176196_2_) {
      return p_176196_1_.func_180495_p(p_176196_2_.func_177977_b()).func_185904_a().func_76220_a()?super.func_176196_c(p_176196_1_, p_176196_2_):false;
   }

   public AxisAlignedBB func_180646_a(IBlockState p_180646_1_, World p_180646_2_, BlockPos p_180646_3_) {
      return ((Boolean)p_180646_1_.func_177229_b(field_176466_a)).booleanValue()?field_185506_k:(((EnumFacing)p_180646_1_.func_177229_b(field_185512_D)).func_176740_k() == EnumFacing.Axis.Z?field_185539_B:field_185540_C);
   }

   public boolean func_149662_c(IBlockState p_149662_1_) {
      return false;
   }

   public boolean func_149686_d(IBlockState p_149686_1_) {
      return false;
   }

   public boolean func_176205_b(IBlockAccess p_176205_1_, BlockPos p_176205_2_) {
      return ((Boolean)p_176205_1_.func_180495_p(p_176205_2_).func_177229_b(field_176466_a)).booleanValue();
   }

   public IBlockState func_180642_a(World p_180642_1_, BlockPos p_180642_2_, EnumFacing p_180642_3_, float p_180642_4_, float p_180642_5_, float p_180642_6_, int p_180642_7_, EntityLivingBase p_180642_8_) {
      return this.func_176223_P().func_177226_a(field_185512_D, p_180642_8_.func_174811_aO()).func_177226_a(field_176466_a, Boolean.valueOf(false)).func_177226_a(field_176465_b, Boolean.valueOf(false)).func_177226_a(field_176467_M, Boolean.valueOf(false));
   }

   public boolean func_180639_a(World p_180639_1_, BlockPos p_180639_2_, IBlockState p_180639_3_, EntityPlayer p_180639_4_, EnumHand p_180639_5_, ItemStack p_180639_6_, EnumFacing p_180639_7_, float p_180639_8_, float p_180639_9_, float p_180639_10_) {
      if(((Boolean)p_180639_3_.func_177229_b(field_176466_a)).booleanValue()) {
         p_180639_3_ = p_180639_3_.func_177226_a(field_176466_a, Boolean.valueOf(false));
         p_180639_1_.func_180501_a(p_180639_2_, p_180639_3_, 10);
      } else {
         EnumFacing enumfacing = EnumFacing.func_176733_a((double)p_180639_4_.field_70177_z);
         if(p_180639_3_.func_177229_b(field_185512_D) == enumfacing.func_176734_d()) {
            p_180639_3_ = p_180639_3_.func_177226_a(field_185512_D, enumfacing);
         }

         p_180639_3_ = p_180639_3_.func_177226_a(field_176466_a, Boolean.valueOf(true));
         p_180639_1_.func_180501_a(p_180639_2_, p_180639_3_, 10);
      }

      p_180639_1_.func_180498_a(p_180639_4_, ((Boolean)p_180639_3_.func_177229_b(field_176466_a)).booleanValue()?1008:1014, p_180639_2_, 0);
      return true;
   }

   public void func_176204_a(World p_176204_1_, BlockPos p_176204_2_, IBlockState p_176204_3_, Block p_176204_4_) {
      if(!p_176204_1_.field_72995_K) {
         boolean flag = p_176204_1_.func_175640_z(p_176204_2_);
         if(flag || p_176204_4_.func_176223_P().func_185897_m()) {
            if(flag && !((Boolean)p_176204_3_.func_177229_b(field_176466_a)).booleanValue() && !((Boolean)p_176204_3_.func_177229_b(field_176465_b)).booleanValue()) {
               p_176204_1_.func_180501_a(p_176204_2_, p_176204_3_.func_177226_a(field_176466_a, Boolean.valueOf(true)).func_177226_a(field_176465_b, Boolean.valueOf(true)), 2);
               p_176204_1_.func_180498_a((EntityPlayer)null, 1008, p_176204_2_, 0);
            } else if(!flag && ((Boolean)p_176204_3_.func_177229_b(field_176466_a)).booleanValue() && ((Boolean)p_176204_3_.func_177229_b(field_176465_b)).booleanValue()) {
               p_176204_1_.func_180501_a(p_176204_2_, p_176204_3_.func_177226_a(field_176466_a, Boolean.valueOf(false)).func_177226_a(field_176465_b, Boolean.valueOf(false)), 2);
               p_176204_1_.func_180498_a((EntityPlayer)null, 1014, p_176204_2_, 0);
            } else if(flag != ((Boolean)p_176204_3_.func_177229_b(field_176465_b)).booleanValue()) {
               p_176204_1_.func_180501_a(p_176204_2_, p_176204_3_.func_177226_a(field_176465_b, Boolean.valueOf(flag)), 2);
            }
         }

      }
   }

   public boolean func_176225_a(IBlockState p_176225_1_, IBlockAccess p_176225_2_, BlockPos p_176225_3_, EnumFacing p_176225_4_) {
      return true;
   }

   public IBlockState func_176203_a(int p_176203_1_) {
      return this.func_176223_P().func_177226_a(field_185512_D, EnumFacing.func_176731_b(p_176203_1_)).func_177226_a(field_176466_a, Boolean.valueOf((p_176203_1_ & 4) != 0)).func_177226_a(field_176465_b, Boolean.valueOf((p_176203_1_ & 8) != 0));
   }

   public int func_176201_c(IBlockState p_176201_1_) {
      int i = 0;
      i = i | ((EnumFacing)p_176201_1_.func_177229_b(field_185512_D)).func_176736_b();
      if(((Boolean)p_176201_1_.func_177229_b(field_176465_b)).booleanValue()) {
         i |= 8;
      }

      if(((Boolean)p_176201_1_.func_177229_b(field_176466_a)).booleanValue()) {
         i |= 4;
      }

      return i;
   }

   protected BlockStateContainer func_180661_e() {
      return new BlockStateContainer(this, new IProperty[]{field_185512_D, field_176466_a, field_176465_b, field_176467_M});
   }
}
