package net.minecraft.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ItemBow extends Item {
   public ItemBow() {
      this.field_77777_bU = 1;
      this.func_77656_e(384);
      this.func_77637_a(CreativeTabs.field_78037_j);
      this.func_185043_a(new ResourceLocation("pull"), new IItemPropertyGetter() {
         public float func_185085_a(ItemStack p_185085_1_, World p_185085_2_, EntityLivingBase p_185085_3_) {
            if(p_185085_3_ == null) {
               return 0.0F;
            } else {
               ItemStack itemstack = p_185085_3_.func_184607_cu();
               return itemstack != null && itemstack.func_77973_b() == Items.field_151031_f?(float)(p_185085_1_.func_77988_m() - p_185085_3_.func_184605_cv()) / 20.0F:0.0F;
            }
         }
      });
      this.func_185043_a(new ResourceLocation("pulling"), new IItemPropertyGetter() {
         public float func_185085_a(ItemStack p_185085_1_, World p_185085_2_, EntityLivingBase p_185085_3_) {
            return p_185085_3_ != null && p_185085_3_.func_184587_cr() && p_185085_3_.func_184607_cu() == p_185085_1_?1.0F:0.0F;
         }
      });
   }

   private ItemStack func_185060_a(EntityPlayer p_185060_1_) {
      if(this.func_185058_h_(p_185060_1_.func_184586_b(EnumHand.OFF_HAND))) {
         return p_185060_1_.func_184586_b(EnumHand.OFF_HAND);
      } else if(this.func_185058_h_(p_185060_1_.func_184586_b(EnumHand.MAIN_HAND))) {
         return p_185060_1_.func_184586_b(EnumHand.MAIN_HAND);
      } else {
         for(int i = 0; i < p_185060_1_.field_71071_by.func_70302_i_(); ++i) {
            ItemStack itemstack = p_185060_1_.field_71071_by.func_70301_a(i);
            if(this.func_185058_h_(itemstack)) {
               return itemstack;
            }
         }

         return null;
      }
   }

   protected boolean func_185058_h_(ItemStack p_185058_1_) {
      return p_185058_1_ != null && p_185058_1_.func_77973_b() instanceof ItemArrow;
   }

   public void func_77615_a(ItemStack p_77615_1_, World p_77615_2_, EntityLivingBase p_77615_3_, int p_77615_4_) {
      if(p_77615_3_ instanceof EntityPlayer) {
         EntityPlayer entityplayer = (EntityPlayer)p_77615_3_;
         boolean flag = entityplayer.field_71075_bZ.field_75098_d || EnchantmentHelper.func_77506_a(Enchantments.field_185312_x, p_77615_1_) > 0;
         ItemStack itemstack = this.func_185060_a(entityplayer);
         if(itemstack != null || flag) {
            if(itemstack == null) {
               itemstack = new ItemStack(Items.field_151032_g);
            }

            int i = this.func_77626_a(p_77615_1_) - p_77615_4_;
            float f = func_185059_b(i);
            if((double)f >= 0.1D) {
               boolean flag1 = flag && itemstack.func_77973_b() == Items.field_151032_g;
               if(!p_77615_2_.field_72995_K) {
                  ItemArrow itemarrow = (ItemArrow)((ItemArrow)(itemstack.func_77973_b() instanceof ItemArrow?itemstack.func_77973_b():Items.field_151032_g));
                  EntityArrow entityarrow = itemarrow.func_185052_a(p_77615_2_, itemstack, entityplayer);
                  entityarrow.func_184547_a(entityplayer, entityplayer.field_70125_A, entityplayer.field_70177_z, 0.0F, f * 3.0F, 1.0F);
                  if(f == 1.0F) {
                     entityarrow.func_70243_d(true);
                  }

                  int j = EnchantmentHelper.func_77506_a(Enchantments.field_185309_u, p_77615_1_);
                  if(j > 0) {
                     entityarrow.func_70239_b(entityarrow.func_70242_d() + (double)j * 0.5D + 0.5D);
                  }

                  int k = EnchantmentHelper.func_77506_a(Enchantments.field_185310_v, p_77615_1_);
                  if(k > 0) {
                     entityarrow.func_70240_a(k);
                  }

                  if(EnchantmentHelper.func_77506_a(Enchantments.field_185311_w, p_77615_1_) > 0) {
                     entityarrow.func_70015_d(100);
                  }

                  p_77615_1_.func_77972_a(1, entityplayer);
                  if(flag1) {
                     entityarrow.field_70251_a = EntityArrow.PickupStatus.CREATIVE_ONLY;
                  }

                  p_77615_2_.func_72838_d(entityarrow);
               }

               p_77615_2_.func_184148_a((EntityPlayer)null, entityplayer.field_70165_t, entityplayer.field_70163_u, entityplayer.field_70161_v, SoundEvents.field_187737_v, SoundCategory.NEUTRAL, 1.0F, 1.0F / (field_77697_d.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
               if(!flag1) {
                  --itemstack.field_77994_a;
                  if(itemstack.field_77994_a == 0) {
                     entityplayer.field_71071_by.func_184437_d(itemstack);
                  }
               }

               entityplayer.func_71029_a(StatList.func_188057_b(this));
            }
         }
      }
   }

   public static float func_185059_b(int p_185059_0_) {
      float f = (float)p_185059_0_ / 20.0F;
      f = (f * f + f * 2.0F) / 3.0F;
      if(f > 1.0F) {
         f = 1.0F;
      }

      return f;
   }

   public int func_77626_a(ItemStack p_77626_1_) {
      return 72000;
   }

   public EnumAction func_77661_b(ItemStack p_77661_1_) {
      return EnumAction.BOW;
   }

   public ActionResult<ItemStack> func_77659_a(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_, EnumHand p_77659_4_) {
      boolean flag = this.func_185060_a(p_77659_3_) != null;
      if(!p_77659_3_.field_71075_bZ.field_75098_d && !flag) {
         return !flag?new ActionResult(EnumActionResult.FAIL, p_77659_1_):new ActionResult(EnumActionResult.PASS, p_77659_1_);
      } else {
         p_77659_3_.func_184598_c(p_77659_4_);
         return new ActionResult(EnumActionResult.SUCCESS, p_77659_1_);
      }
   }

   public int func_77619_b() {
      return 1;
   }
}
