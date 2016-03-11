package net.minecraft.item;

import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

public class ItemPotion extends Item {
   public ItemPotion() {
      this.func_77625_d(1);
      this.func_77637_a(CreativeTabs.field_78038_k);
   }

   public ItemStack func_77654_b(ItemStack p_77654_1_, World p_77654_2_, EntityLivingBase p_77654_3_) {
      EntityPlayer entityplayer = p_77654_3_ instanceof EntityPlayer?(EntityPlayer)p_77654_3_:null;
      if(entityplayer == null || !entityplayer.field_71075_bZ.field_75098_d) {
         --p_77654_1_.field_77994_a;
      }

      if(!p_77654_2_.field_72995_K) {
         for(PotionEffect potioneffect : PotionUtils.func_185189_a(p_77654_1_)) {
            p_77654_3_.func_70690_d(new PotionEffect(potioneffect));
         }
      }

      if(entityplayer != null) {
         entityplayer.func_71029_a(StatList.func_188057_b(this));
      }

      if(entityplayer == null || !entityplayer.field_71075_bZ.field_75098_d) {
         if(p_77654_1_.field_77994_a <= 0) {
            return new ItemStack(Items.field_151069_bo);
         }

         if(entityplayer != null) {
            entityplayer.field_71071_by.func_70441_a(new ItemStack(Items.field_151069_bo));
         }
      }

      return p_77654_1_;
   }

   public int func_77626_a(ItemStack p_77626_1_) {
      return 32;
   }

   public EnumAction func_77661_b(ItemStack p_77661_1_) {
      return EnumAction.DRINK;
   }

   public ActionResult<ItemStack> func_77659_a(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_, EnumHand p_77659_4_) {
      p_77659_3_.func_184598_c(p_77659_4_);
      return new ActionResult(EnumActionResult.SUCCESS, p_77659_1_);
   }

   public String func_77653_i(ItemStack p_77653_1_) {
      return I18n.func_74838_a(PotionUtils.func_185191_c(p_77653_1_).func_185174_b("potion.effect."));
   }

   public void func_77624_a(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List<String> p_77624_3_, boolean p_77624_4_) {
      PotionUtils.func_185182_a(p_77624_1_, p_77624_3_, 1.0F);
   }

   public boolean func_77636_d(ItemStack p_77636_1_) {
      return !PotionUtils.func_185189_a(p_77636_1_).isEmpty();
   }

   public void func_150895_a(Item p_150895_1_, CreativeTabs p_150895_2_, List<ItemStack> p_150895_3_) {
      for(PotionType potiontype : PotionType.field_185176_a) {
         p_150895_3_.add(PotionUtils.func_185188_a(new ItemStack(p_150895_1_), potiontype));
      }

   }
}
