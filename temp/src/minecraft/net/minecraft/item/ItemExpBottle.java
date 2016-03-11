package net.minecraft.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityExpBottle;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ItemExpBottle extends Item {
   public ItemExpBottle() {
      this.func_77637_a(CreativeTabs.field_78026_f);
   }

   public boolean func_77636_d(ItemStack p_77636_1_) {
      return true;
   }

   public ActionResult<ItemStack> func_77659_a(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_, EnumHand p_77659_4_) {
      if(!p_77659_3_.field_71075_bZ.field_75098_d) {
         --p_77659_1_.field_77994_a;
      }

      p_77659_2_.func_184148_a((EntityPlayer)null, p_77659_3_.field_70165_t, p_77659_3_.field_70163_u, p_77659_3_.field_70161_v, SoundEvents.field_187601_be, SoundCategory.NEUTRAL, 0.5F, 0.4F / (field_77697_d.nextFloat() * 0.4F + 0.8F));
      if(!p_77659_2_.field_72995_K) {
         EntityExpBottle entityexpbottle = new EntityExpBottle(p_77659_2_, p_77659_3_);
         entityexpbottle.func_184538_a(p_77659_3_, p_77659_3_.field_70125_A, p_77659_3_.field_70177_z, -20.0F, 0.7F, 1.0F);
         p_77659_2_.func_72838_d(entityexpbottle);
      }

      p_77659_3_.func_71029_a(StatList.func_188057_b(this));
      return new ActionResult(EnumActionResult.SUCCESS, p_77659_1_);
   }
}
