package net.minecraft.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemWritableBook extends Item {
   public ItemWritableBook() {
      this.func_77625_d(1);
   }

   public ActionResult<ItemStack> func_77659_a(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_, EnumHand p_77659_4_) {
      p_77659_3_.func_184814_a(p_77659_1_, p_77659_4_);
      p_77659_3_.func_71029_a(StatList.func_188057_b(this));
      return new ActionResult(EnumActionResult.SUCCESS, p_77659_1_);
   }

   public static boolean func_150930_a(NBTTagCompound p_150930_0_) {
      if(p_150930_0_ == null) {
         return false;
      } else if(!p_150930_0_.func_150297_b("pages", 9)) {
         return false;
      } else {
         NBTTagList nbttaglist = p_150930_0_.func_150295_c("pages", 8);

         for(int i = 0; i < nbttaglist.func_74745_c(); ++i) {
            String s = nbttaglist.func_150307_f(i);
            if(s == null) {
               return false;
            }

            if(s.length() > 32767) {
               return false;
            }
         }

         return true;
      }
   }
}
