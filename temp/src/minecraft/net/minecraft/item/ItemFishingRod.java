package net.minecraft.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ItemFishingRod extends Item {
   public ItemFishingRod() {
      this.func_77656_e(64);
      this.func_77625_d(1);
      this.func_77637_a(CreativeTabs.field_78040_i);
      this.func_185043_a(new ResourceLocation("cast"), new IItemPropertyGetter() {
         public float func_185085_a(ItemStack p_185085_1_, World p_185085_2_, EntityLivingBase p_185085_3_) {
            return p_185085_3_ == null?0.0F:(p_185085_3_.func_184614_ca() == p_185085_1_ && p_185085_3_ instanceof EntityPlayer && ((EntityPlayer)p_185085_3_).field_71104_cf != null?1.0F:0.0F);
         }
      });
   }

   public boolean func_77662_d() {
      return true;
   }

   public boolean func_77629_n_() {
      return true;
   }

   public ActionResult<ItemStack> func_77659_a(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_, EnumHand p_77659_4_) {
      if(p_77659_3_.field_71104_cf != null) {
         int i = p_77659_3_.field_71104_cf.func_146034_e();
         p_77659_1_.func_77972_a(i, p_77659_3_);
         p_77659_3_.func_184609_a(p_77659_4_);
      } else {
         p_77659_2_.func_184148_a((EntityPlayer)null, p_77659_3_.field_70165_t, p_77659_3_.field_70163_u, p_77659_3_.field_70161_v, SoundEvents.field_187612_G, SoundCategory.NEUTRAL, 0.5F, 0.4F / (field_77697_d.nextFloat() * 0.4F + 0.8F));
         if(!p_77659_2_.field_72995_K) {
            p_77659_2_.func_72838_d(new EntityFishHook(p_77659_2_, p_77659_3_));
         }

         p_77659_3_.func_184609_a(p_77659_4_);
         p_77659_3_.func_71029_a(StatList.func_188057_b(this));
      }

      return new ActionResult(EnumActionResult.SUCCESS, p_77659_1_);
   }

   public boolean func_77616_k(ItemStack p_77616_1_) {
      return super.func_77616_k(p_77616_1_);
   }

   public int func_77619_b() {
      return 1;
   }
}
