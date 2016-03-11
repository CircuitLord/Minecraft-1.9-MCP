package net.minecraft.item;

import net.minecraft.block.BlockStandingSign;
import net.minecraft.block.BlockWallSign;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ItemSign extends Item {
   public ItemSign() {
      this.field_77777_bU = 16;
      this.func_77637_a(CreativeTabs.field_78031_c);
   }

   public EnumActionResult func_180614_a(ItemStack p_180614_1_, EntityPlayer p_180614_2_, World p_180614_3_, BlockPos p_180614_4_, EnumHand p_180614_5_, EnumFacing p_180614_6_, float p_180614_7_, float p_180614_8_, float p_180614_9_) {
      IBlockState iblockstate = p_180614_3_.func_180495_p(p_180614_4_);
      boolean flag = iblockstate.func_177230_c().func_176200_f(p_180614_3_, p_180614_4_);
      if(p_180614_6_ != EnumFacing.DOWN && (iblockstate.func_185904_a().func_76220_a() || flag) && (!flag || p_180614_6_ == EnumFacing.UP)) {
         p_180614_4_ = p_180614_4_.func_177972_a(p_180614_6_);
         if(p_180614_2_.func_175151_a(p_180614_4_, p_180614_6_, p_180614_1_) && Blocks.field_150472_an.func_176196_c(p_180614_3_, p_180614_4_)) {
            if(p_180614_3_.field_72995_K) {
               return EnumActionResult.SUCCESS;
            } else {
               p_180614_4_ = flag?p_180614_4_.func_177977_b():p_180614_4_;
               if(p_180614_6_ == EnumFacing.UP) {
                  int i = MathHelper.func_76128_c((double)((p_180614_2_.field_70177_z + 180.0F) * 16.0F / 360.0F) + 0.5D) & 15;
                  p_180614_3_.func_180501_a(p_180614_4_, Blocks.field_150472_an.func_176223_P().func_177226_a(BlockStandingSign.field_176413_a, Integer.valueOf(i)), 11);
               } else {
                  p_180614_3_.func_180501_a(p_180614_4_, Blocks.field_150444_as.func_176223_P().func_177226_a(BlockWallSign.field_176412_a, p_180614_6_), 11);
               }

               --p_180614_1_.field_77994_a;
               TileEntity tileentity = p_180614_3_.func_175625_s(p_180614_4_);
               if(tileentity instanceof TileEntitySign && !ItemBlock.func_179224_a(p_180614_3_, p_180614_2_, p_180614_4_, p_180614_1_)) {
                  p_180614_2_.func_175141_a((TileEntitySign)tileentity);
               }

               return EnumActionResult.SUCCESS;
            }
         } else {
            return EnumActionResult.FAIL;
         }
      } else {
         return EnumActionResult.FAIL;
      }
   }
}
