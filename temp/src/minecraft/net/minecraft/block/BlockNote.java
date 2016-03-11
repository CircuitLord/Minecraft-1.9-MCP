package net.minecraft.block;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityNote;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockNote extends BlockContainer {
   private static final List<SoundEvent> field_176434_a = Lists.newArrayList(new SoundEvent[]{SoundEvents.field_187682_dG, SoundEvents.field_187676_dE, SoundEvents.field_187688_dI, SoundEvents.field_187685_dH, SoundEvents.field_187679_dF});

   public BlockNote() {
      super(Material.field_151575_d);
      this.func_149647_a(CreativeTabs.field_78028_d);
   }

   public void func_176204_a(World p_176204_1_, BlockPos p_176204_2_, IBlockState p_176204_3_, Block p_176204_4_) {
      boolean flag = p_176204_1_.func_175640_z(p_176204_2_);
      TileEntity tileentity = p_176204_1_.func_175625_s(p_176204_2_);
      if(tileentity instanceof TileEntityNote) {
         TileEntityNote tileentitynote = (TileEntityNote)tileentity;
         if(tileentitynote.field_145880_i != flag) {
            if(flag) {
               tileentitynote.func_175108_a(p_176204_1_, p_176204_2_);
            }

            tileentitynote.field_145880_i = flag;
         }
      }

   }

   public boolean func_180639_a(World p_180639_1_, BlockPos p_180639_2_, IBlockState p_180639_3_, EntityPlayer p_180639_4_, EnumHand p_180639_5_, ItemStack p_180639_6_, EnumFacing p_180639_7_, float p_180639_8_, float p_180639_9_, float p_180639_10_) {
      if(p_180639_1_.field_72995_K) {
         return true;
      } else {
         TileEntity tileentity = p_180639_1_.func_175625_s(p_180639_2_);
         if(tileentity instanceof TileEntityNote) {
            TileEntityNote tileentitynote = (TileEntityNote)tileentity;
            tileentitynote.func_145877_a();
            tileentitynote.func_175108_a(p_180639_1_, p_180639_2_);
            p_180639_4_.func_71029_a(StatList.field_188087_U);
         }

         return true;
      }
   }

   public void func_180649_a(World p_180649_1_, BlockPos p_180649_2_, EntityPlayer p_180649_3_) {
      if(!p_180649_1_.field_72995_K) {
         TileEntity tileentity = p_180649_1_.func_175625_s(p_180649_2_);
         if(tileentity instanceof TileEntityNote) {
            ((TileEntityNote)tileentity).func_175108_a(p_180649_1_, p_180649_2_);
            p_180649_3_.func_71029_a(StatList.field_188086_T);
         }

      }
   }

   public TileEntity func_149915_a(World p_149915_1_, int p_149915_2_) {
      return new TileEntityNote();
   }

   private SoundEvent func_185576_e(int p_185576_1_) {
      if(p_185576_1_ < 0 || p_185576_1_ >= field_176434_a.size()) {
         p_185576_1_ = 0;
      }

      return (SoundEvent)field_176434_a.get(p_185576_1_);
   }

   public boolean func_180648_a(World p_180648_1_, BlockPos p_180648_2_, IBlockState p_180648_3_, int p_180648_4_, int p_180648_5_) {
      float f = (float)Math.pow(2.0D, (double)(p_180648_5_ - 12) / 12.0D);
      p_180648_1_.func_184133_a((EntityPlayer)null, p_180648_2_, this.func_185576_e(p_180648_4_), SoundCategory.BLOCKS, 3.0F, f);
      p_180648_1_.func_175688_a(EnumParticleTypes.NOTE, (double)p_180648_2_.func_177958_n() + 0.5D, (double)p_180648_2_.func_177956_o() + 1.2D, (double)p_180648_2_.func_177952_p() + 0.5D, (double)p_180648_5_ / 24.0D, 0.0D, 0.0D, new int[0]);
      return true;
   }

   public EnumBlockRenderType func_149645_b(IBlockState p_149645_1_) {
      return EnumBlockRenderType.MODEL;
   }
}
