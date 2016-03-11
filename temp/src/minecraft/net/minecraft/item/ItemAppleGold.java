package net.minecraft.item;

import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.AchievementList;
import net.minecraft.world.World;

public class ItemAppleGold extends ItemFood {
   public ItemAppleGold(int p_i45341_1_, float p_i45341_2_, boolean p_i45341_3_) {
      super(p_i45341_1_, p_i45341_2_, p_i45341_3_);
      this.func_77627_a(true);
   }

   public boolean func_77636_d(ItemStack p_77636_1_) {
      return p_77636_1_.func_77960_j() > 0;
   }

   public EnumRarity func_77613_e(ItemStack p_77613_1_) {
      return p_77613_1_.func_77960_j() == 0?EnumRarity.RARE:EnumRarity.EPIC;
   }

   protected void func_77849_c(ItemStack p_77849_1_, World p_77849_2_, EntityPlayer p_77849_3_) {
      if(!p_77849_2_.field_72995_K) {
         if(p_77849_1_.func_77960_j() > 0) {
            p_77849_3_.func_71029_a(AchievementList.field_187980_M);
            p_77849_3_.func_70690_d(new PotionEffect(MobEffects.field_76428_l, 400, 1));
            p_77849_3_.func_70690_d(new PotionEffect(MobEffects.field_76429_m, 6000, 0));
            p_77849_3_.func_70690_d(new PotionEffect(MobEffects.field_76426_n, 6000, 0));
            p_77849_3_.func_70690_d(new PotionEffect(MobEffects.field_76444_x, 2400, 3));
         } else {
            p_77849_3_.func_70690_d(new PotionEffect(MobEffects.field_76428_l, 100, 1));
            p_77849_3_.func_70690_d(new PotionEffect(MobEffects.field_76444_x, 2400, 0));
         }
      }

   }

   public void func_150895_a(Item p_150895_1_, CreativeTabs p_150895_2_, List<ItemStack> p_150895_3_) {
      p_150895_3_.add(new ItemStack(p_150895_1_));
      p_150895_3_.add(new ItemStack(p_150895_1_, 1, 1));
   }
}
