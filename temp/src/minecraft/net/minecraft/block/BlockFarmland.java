package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockStem;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockFarmland extends Block {
   public static final PropertyInteger field_176531_a = PropertyInteger.func_177719_a("moisture", 0, 7);
   protected static final AxisAlignedBB field_185665_b = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.9375D, 1.0D);

   protected BlockFarmland() {
      super(Material.field_151578_c);
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(field_176531_a, Integer.valueOf(0)));
      this.func_149675_a(true);
      this.func_149713_g(255);
   }

   public AxisAlignedBB func_185496_a(IBlockState p_185496_1_, IBlockAccess p_185496_2_, BlockPos p_185496_3_) {
      return field_185665_b;
   }

   public AxisAlignedBB func_180646_a(IBlockState p_180646_1_, World p_180646_2_, BlockPos p_180646_3_) {
      return field_185505_j;
   }

   public boolean func_149662_c(IBlockState p_149662_1_) {
      return false;
   }

   public boolean func_149686_d(IBlockState p_149686_1_) {
      return false;
   }

   public void func_180650_b(World p_180650_1_, BlockPos p_180650_2_, IBlockState p_180650_3_, Random p_180650_4_) {
      int i = ((Integer)p_180650_3_.func_177229_b(field_176531_a)).intValue();
      if(!this.func_176530_e(p_180650_1_, p_180650_2_) && !p_180650_1_.func_175727_C(p_180650_2_.func_177984_a())) {
         if(i > 0) {
            p_180650_1_.func_180501_a(p_180650_2_, p_180650_3_.func_177226_a(field_176531_a, Integer.valueOf(i - 1)), 2);
         } else if(!this.func_176529_d(p_180650_1_, p_180650_2_)) {
            p_180650_1_.func_175656_a(p_180650_2_, Blocks.field_150346_d.func_176223_P());
         }
      } else if(i < 7) {
         p_180650_1_.func_180501_a(p_180650_2_, p_180650_3_.func_177226_a(field_176531_a, Integer.valueOf(7)), 2);
      }

   }

   public void func_180658_a(World p_180658_1_, BlockPos p_180658_2_, Entity p_180658_3_, float p_180658_4_) {
      if(!p_180658_1_.field_72995_K && p_180658_1_.field_73012_v.nextFloat() < p_180658_4_ - 0.5F && p_180658_3_ instanceof EntityLivingBase && (p_180658_3_ instanceof EntityPlayer || p_180658_1_.func_82736_K().func_82766_b("mobGriefing")) && p_180658_3_.field_70130_N * p_180658_3_.field_70130_N * p_180658_3_.field_70131_O > 0.512F) {
         p_180658_1_.func_175656_a(p_180658_2_, Blocks.field_150346_d.func_176223_P());
      }

      super.func_180658_a(p_180658_1_, p_180658_2_, p_180658_3_, p_180658_4_);
   }

   private boolean func_176529_d(World p_176529_1_, BlockPos p_176529_2_) {
      Block block = p_176529_1_.func_180495_p(p_176529_2_.func_177984_a()).func_177230_c();
      return block instanceof BlockCrops || block instanceof BlockStem;
   }

   private boolean func_176530_e(World p_176530_1_, BlockPos p_176530_2_) {
      for(BlockPos.MutableBlockPos blockpos$mutableblockpos : BlockPos.func_177975_b(p_176530_2_.func_177982_a(-4, 0, -4), p_176530_2_.func_177982_a(4, 1, 4))) {
         if(p_176530_1_.func_180495_p(blockpos$mutableblockpos).func_185904_a() == Material.field_151586_h) {
            return true;
         }
      }

      return false;
   }

   public void func_176204_a(World p_176204_1_, BlockPos p_176204_2_, IBlockState p_176204_3_, Block p_176204_4_) {
      super.func_176204_a(p_176204_1_, p_176204_2_, p_176204_3_, p_176204_4_);
      if(p_176204_1_.func_180495_p(p_176204_2_.func_177984_a()).func_185904_a().func_76220_a()) {
         p_176204_1_.func_175656_a(p_176204_2_, Blocks.field_150346_d.func_176223_P());
      }

   }

   public boolean func_176225_a(IBlockState p_176225_1_, IBlockAccess p_176225_2_, BlockPos p_176225_3_, EnumFacing p_176225_4_) {
      switch(p_176225_4_) {
      case UP:
         return true;
      case NORTH:
      case SOUTH:
      case WEST:
      case EAST:
         IBlockState iblockstate = p_176225_2_.func_180495_p(p_176225_3_.func_177972_a(p_176225_4_));
         Block block = iblockstate.func_177230_c();
         return !iblockstate.func_185914_p() && block != Blocks.field_150458_ak && block != Blocks.field_185774_da;
      default:
         return super.func_176225_a(p_176225_1_, p_176225_2_, p_176225_3_, p_176225_4_);
      }
   }

   public Item func_180660_a(IBlockState p_180660_1_, Random p_180660_2_, int p_180660_3_) {
      return Blocks.field_150346_d.func_180660_a(Blocks.field_150346_d.func_176223_P().func_177226_a(BlockDirt.field_176386_a, BlockDirt.DirtType.DIRT), p_180660_2_, p_180660_3_);
   }

   public ItemStack func_185473_a(World p_185473_1_, BlockPos p_185473_2_, IBlockState p_185473_3_) {
      return new ItemStack(Blocks.field_150346_d);
   }

   public IBlockState func_176203_a(int p_176203_1_) {
      return this.func_176223_P().func_177226_a(field_176531_a, Integer.valueOf(p_176203_1_ & 7));
   }

   public int func_176201_c(IBlockState p_176201_1_) {
      return ((Integer)p_176201_1_.func_177229_b(field_176531_a)).intValue();
   }

   protected BlockStateContainer func_180661_e() {
      return new BlockStateContainer(this, new IProperty[]{field_176531_a});
   }
}
