package net.minecraft.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ItemBed extends Item {
   public ItemBed() {
      this.func_77637_a(CreativeTabs.field_78031_c);
   }

   public EnumActionResult func_180614_a(ItemStack p_180614_1_, EntityPlayer p_180614_2_, World p_180614_3_, BlockPos p_180614_4_, EnumHand p_180614_5_, EnumFacing p_180614_6_, float p_180614_7_, float p_180614_8_, float p_180614_9_) {
      if(p_180614_3_.field_72995_K) {
         return EnumActionResult.SUCCESS;
      } else if(p_180614_6_ != EnumFacing.UP) {
         return EnumActionResult.FAIL;
      } else {
         IBlockState iblockstate = p_180614_3_.func_180495_p(p_180614_4_);
         Block block = iblockstate.func_177230_c();
         boolean flag = block.func_176200_f(p_180614_3_, p_180614_4_);
         if(!flag) {
            p_180614_4_ = p_180614_4_.func_177984_a();
         }

         int i = MathHelper.func_76128_c((double)(p_180614_2_.field_70177_z * 4.0F / 360.0F) + 0.5D) & 3;
         EnumFacing enumfacing = EnumFacing.func_176731_b(i);
         BlockPos blockpos = p_180614_4_.func_177972_a(enumfacing);
         if(p_180614_2_.func_175151_a(p_180614_4_, p_180614_6_, p_180614_1_) && p_180614_2_.func_175151_a(blockpos, p_180614_6_, p_180614_1_)) {
            boolean flag1 = p_180614_3_.func_180495_p(blockpos).func_177230_c().func_176200_f(p_180614_3_, blockpos);
            boolean flag2 = flag || p_180614_3_.func_175623_d(p_180614_4_);
            boolean flag3 = flag1 || p_180614_3_.func_175623_d(blockpos);
            if(flag2 && flag3 && p_180614_3_.func_180495_p(p_180614_4_.func_177977_b()).func_185896_q() && p_180614_3_.func_180495_p(blockpos.func_177977_b()).func_185896_q()) {
               IBlockState iblockstate1 = Blocks.field_150324_C.func_176223_P().func_177226_a(BlockBed.field_176471_b, Boolean.valueOf(false)).func_177226_a(BlockBed.field_185512_D, enumfacing).func_177226_a(BlockBed.field_176472_a, BlockBed.EnumPartType.FOOT);
               if(p_180614_3_.func_180501_a(p_180614_4_, iblockstate1, 11)) {
                  IBlockState iblockstate2 = iblockstate1.func_177226_a(BlockBed.field_176472_a, BlockBed.EnumPartType.HEAD);
                  p_180614_3_.func_180501_a(blockpos, iblockstate2, 11);
               }

               SoundType soundtype = iblockstate1.func_177230_c().func_185467_w();
               p_180614_3_.func_184133_a((EntityPlayer)null, p_180614_4_, soundtype.func_185841_e(), SoundCategory.BLOCKS, (soundtype.func_185843_a() + 1.0F) / 2.0F, soundtype.func_185847_b() * 0.8F);
               --p_180614_1_.field_77994_a;
               return EnumActionResult.SUCCESS;
            } else {
               return EnumActionResult.FAIL;
            }
         } else {
            return EnumActionResult.FAIL;
         }
      }
   }
}
