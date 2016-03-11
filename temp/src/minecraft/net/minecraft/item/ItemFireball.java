package net.minecraft.item;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemFireball extends Item {
   public ItemFireball() {
      this.func_77637_a(CreativeTabs.field_78026_f);
   }

   public EnumActionResult func_180614_a(ItemStack p_180614_1_, EntityPlayer p_180614_2_, World p_180614_3_, BlockPos p_180614_4_, EnumHand p_180614_5_, EnumFacing p_180614_6_, float p_180614_7_, float p_180614_8_, float p_180614_9_) {
      if(p_180614_3_.field_72995_K) {
         return EnumActionResult.SUCCESS;
      } else {
         p_180614_4_ = p_180614_4_.func_177972_a(p_180614_6_);
         if(!p_180614_2_.func_175151_a(p_180614_4_, p_180614_6_, p_180614_1_)) {
            return EnumActionResult.FAIL;
         } else {
            if(p_180614_3_.func_180495_p(p_180614_4_).func_185904_a() == Material.field_151579_a) {
               p_180614_3_.func_184133_a((EntityPlayer)null, p_180614_4_, SoundEvents.field_187616_bj, SoundCategory.BLOCKS, 1.0F, (field_77697_d.nextFloat() - field_77697_d.nextFloat()) * 0.2F + 1.0F);
               p_180614_3_.func_175656_a(p_180614_4_, Blocks.field_150480_ab.func_176223_P());
            }

            if(!p_180614_2_.field_71075_bZ.field_75098_d) {
               --p_180614_1_.field_77994_a;
            }

            return EnumActionResult.SUCCESS;
         }
      }
   }
}
