package net.minecraft.item;

import com.google.common.base.Predicate;
import java.util.List;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityAreaEffectCloud;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class ItemGlassBottle extends Item {
   public ItemGlassBottle() {
      this.func_77637_a(CreativeTabs.field_78038_k);
   }

   public ActionResult<ItemStack> func_77659_a(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_, EnumHand p_77659_4_) {
      List<EntityAreaEffectCloud> list = p_77659_2_.<EntityAreaEffectCloud>func_175647_a(EntityAreaEffectCloud.class, p_77659_3_.func_174813_aQ().func_186662_g(2.0D), new Predicate<EntityAreaEffectCloud>() {
         public boolean apply(EntityAreaEffectCloud p_apply_1_) {
            return p_apply_1_ != null && p_apply_1_.func_70089_S() && p_apply_1_.func_184494_w() instanceof EntityDragon;
         }
      });
      if(!list.isEmpty()) {
         EntityAreaEffectCloud entityareaeffectcloud = (EntityAreaEffectCloud)list.get(0);
         entityareaeffectcloud.func_184483_a(entityareaeffectcloud.func_184490_j() - 0.5F);
         p_77659_2_.func_184148_a((EntityPlayer)null, p_77659_3_.field_70165_t, p_77659_3_.field_70163_u, p_77659_3_.field_70161_v, SoundEvents.field_187618_I, SoundCategory.NEUTRAL, 1.0F, 1.0F);
         return new ActionResult(EnumActionResult.SUCCESS, this.func_185061_a(p_77659_1_, p_77659_3_, new ItemStack(Items.field_185157_bK)));
      } else {
         RayTraceResult raytraceresult = this.func_77621_a(p_77659_2_, p_77659_3_, true);
         if(raytraceresult == null) {
            return new ActionResult(EnumActionResult.PASS, p_77659_1_);
         } else {
            if(raytraceresult.field_72313_a == RayTraceResult.Type.BLOCK) {
               BlockPos blockpos = raytraceresult.func_178782_a();
               if(!p_77659_2_.func_175660_a(p_77659_3_, blockpos) || !p_77659_3_.func_175151_a(blockpos.func_177972_a(raytraceresult.field_178784_b), raytraceresult.field_178784_b, p_77659_1_)) {
                  return new ActionResult(EnumActionResult.PASS, p_77659_1_);
               }

               if(p_77659_2_.func_180495_p(blockpos).func_185904_a() == Material.field_151586_h) {
                  p_77659_2_.func_184148_a(p_77659_3_, p_77659_3_.field_70165_t, p_77659_3_.field_70163_u, p_77659_3_.field_70161_v, SoundEvents.field_187615_H, SoundCategory.NEUTRAL, 1.0F, 1.0F);
                  return new ActionResult(EnumActionResult.SUCCESS, this.func_185061_a(p_77659_1_, p_77659_3_, new ItemStack(Items.field_151068_bn)));
               }
            }

            return new ActionResult(EnumActionResult.PASS, p_77659_1_);
         }
      }
   }

   protected ItemStack func_185061_a(ItemStack p_185061_1_, EntityPlayer p_185061_2_, ItemStack p_185061_3_) {
      --p_185061_1_.field_77994_a;
      p_185061_2_.func_71029_a(StatList.func_188057_b(this));
      if(p_185061_1_.field_77994_a <= 0) {
         return p_185061_3_;
      } else {
         if(!p_185061_2_.field_71071_by.func_70441_a(p_185061_3_)) {
            p_185061_2_.func_71019_a(p_185061_3_, false);
         }

         return p_185061_1_;
      }
   }
}
