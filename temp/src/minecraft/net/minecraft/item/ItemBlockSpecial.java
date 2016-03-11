package net.minecraft.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemBlockSpecial extends Item {
   private Block field_150935_a;

   public ItemBlockSpecial(Block p_i45329_1_) {
      this.field_150935_a = p_i45329_1_;
   }

   public EnumActionResult func_180614_a(ItemStack p_180614_1_, EntityPlayer p_180614_2_, World p_180614_3_, BlockPos p_180614_4_, EnumHand p_180614_5_, EnumFacing p_180614_6_, float p_180614_7_, float p_180614_8_, float p_180614_9_) {
      IBlockState iblockstate = p_180614_3_.func_180495_p(p_180614_4_);
      Block block = iblockstate.func_177230_c();
      if(block == Blocks.field_150431_aC && ((Integer)iblockstate.func_177229_b(BlockSnow.field_176315_a)).intValue() < 1) {
         p_180614_6_ = EnumFacing.UP;
      } else if(!block.func_176200_f(p_180614_3_, p_180614_4_)) {
         p_180614_4_ = p_180614_4_.func_177972_a(p_180614_6_);
      }

      if(p_180614_2_.func_175151_a(p_180614_4_, p_180614_6_, p_180614_1_) && p_180614_1_.field_77994_a != 0 && p_180614_3_.func_175716_a(this.field_150935_a, p_180614_4_, false, p_180614_6_, (Entity)null, p_180614_1_)) {
         IBlockState iblockstate1 = this.field_150935_a.func_180642_a(p_180614_3_, p_180614_4_, p_180614_6_, p_180614_7_, p_180614_8_, p_180614_9_, 0, p_180614_2_);
         if(!p_180614_3_.func_180501_a(p_180614_4_, iblockstate1, 11)) {
            return EnumActionResult.FAIL;
         } else {
            iblockstate1 = p_180614_3_.func_180495_p(p_180614_4_);
            if(iblockstate1.func_177230_c() == this.field_150935_a) {
               ItemBlock.func_179224_a(p_180614_3_, p_180614_2_, p_180614_4_, p_180614_1_);
               iblockstate1.func_177230_c().func_180633_a(p_180614_3_, p_180614_4_, iblockstate1, p_180614_2_, p_180614_1_);
            }

            SoundType soundtype = this.field_150935_a.func_185467_w();
            p_180614_3_.func_184133_a(p_180614_2_, p_180614_4_, soundtype.func_185841_e(), SoundCategory.BLOCKS, (soundtype.func_185843_a() + 1.0F) / 2.0F, soundtype.func_185847_b() * 0.8F);
            --p_180614_1_.field_77994_a;
            return EnumActionResult.SUCCESS;
         }
      } else {
         return EnumActionResult.FAIL;
      }
   }
}
