package net.minecraft.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemRedstone extends Item {
   public ItemRedstone() {
      this.func_77637_a(CreativeTabs.field_78028_d);
   }

   public EnumActionResult func_180614_a(ItemStack p_180614_1_, EntityPlayer p_180614_2_, World p_180614_3_, BlockPos p_180614_4_, EnumHand p_180614_5_, EnumFacing p_180614_6_, float p_180614_7_, float p_180614_8_, float p_180614_9_) {
      boolean flag = p_180614_3_.func_180495_p(p_180614_4_).func_177230_c().func_176200_f(p_180614_3_, p_180614_4_);
      BlockPos blockpos = flag?p_180614_4_:p_180614_4_.func_177972_a(p_180614_6_);
      if(p_180614_2_.func_175151_a(blockpos, p_180614_6_, p_180614_1_) && p_180614_3_.func_175716_a(p_180614_3_.func_180495_p(blockpos).func_177230_c(), blockpos, false, p_180614_6_, (Entity)null, p_180614_1_) && Blocks.field_150488_af.func_176196_c(p_180614_3_, blockpos)) {
         --p_180614_1_.field_77994_a;
         p_180614_3_.func_175656_a(blockpos, Blocks.field_150488_af.func_176223_P());
         return EnumActionResult.SUCCESS;
      } else {
         return EnumActionResult.FAIL;
      }
   }
}
