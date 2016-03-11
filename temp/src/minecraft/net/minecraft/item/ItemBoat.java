package net.minecraft.item;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ItemBoat extends Item {
   private final EntityBoat.Type field_185057_a;

   public ItemBoat(EntityBoat.Type p_i46748_1_) {
      this.field_185057_a = p_i46748_1_;
      this.field_77777_bU = 1;
      this.func_77637_a(CreativeTabs.field_78029_e);
      this.func_77655_b("boat." + p_i46748_1_.func_184980_a());
   }

   public ActionResult<ItemStack> func_77659_a(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_, EnumHand p_77659_4_) {
      float f = 1.0F;
      float f1 = p_77659_3_.field_70127_C + (p_77659_3_.field_70125_A - p_77659_3_.field_70127_C) * f;
      float f2 = p_77659_3_.field_70126_B + (p_77659_3_.field_70177_z - p_77659_3_.field_70126_B) * f;
      double d0 = p_77659_3_.field_70169_q + (p_77659_3_.field_70165_t - p_77659_3_.field_70169_q) * (double)f;
      double d1 = p_77659_3_.field_70167_r + (p_77659_3_.field_70163_u - p_77659_3_.field_70167_r) * (double)f + (double)p_77659_3_.func_70047_e();
      double d2 = p_77659_3_.field_70166_s + (p_77659_3_.field_70161_v - p_77659_3_.field_70166_s) * (double)f;
      Vec3d vec3d = new Vec3d(d0, d1, d2);
      float f3 = MathHelper.func_76134_b(-f2 * 0.017453292F - 3.1415927F);
      float f4 = MathHelper.func_76126_a(-f2 * 0.017453292F - 3.1415927F);
      float f5 = -MathHelper.func_76134_b(-f1 * 0.017453292F);
      float f6 = MathHelper.func_76126_a(-f1 * 0.017453292F);
      float f7 = f4 * f5;
      float f8 = f3 * f5;
      double d3 = 5.0D;
      Vec3d vec3d1 = vec3d.func_72441_c((double)f7 * d3, (double)f6 * d3, (double)f8 * d3);
      RayTraceResult raytraceresult = p_77659_2_.func_72901_a(vec3d, vec3d1, true);
      if(raytraceresult == null) {
         return new ActionResult(EnumActionResult.PASS, p_77659_1_);
      } else {
         Vec3d vec3d2 = p_77659_3_.func_70676_i(f);
         boolean flag = false;
         List<Entity> list = p_77659_2_.func_72839_b(p_77659_3_, p_77659_3_.func_174813_aQ().func_72321_a(vec3d2.field_72450_a * d3, vec3d2.field_72448_b * d3, vec3d2.field_72449_c * d3).func_186662_g(1.0D));

         for(int i = 0; i < list.size(); ++i) {
            Entity entity = (Entity)list.get(i);
            if(entity.func_70067_L()) {
               AxisAlignedBB axisalignedbb = entity.func_174813_aQ().func_186662_g((double)entity.func_70111_Y());
               if(axisalignedbb.func_72318_a(vec3d)) {
                  flag = true;
               }
            }
         }

         if(flag) {
            return new ActionResult(EnumActionResult.PASS, p_77659_1_);
         } else if(raytraceresult.field_72313_a != RayTraceResult.Type.BLOCK) {
            return new ActionResult(EnumActionResult.PASS, p_77659_1_);
         } else {
            Block block = p_77659_2_.func_180495_p(raytraceresult.func_178782_a()).func_177230_c();
            boolean flag1 = block == Blocks.field_150355_j || block == Blocks.field_150358_i;
            EntityBoat entityboat = new EntityBoat(p_77659_2_, raytraceresult.field_72307_f.field_72450_a, flag1?raytraceresult.field_72307_f.field_72448_b - 0.12D:raytraceresult.field_72307_f.field_72448_b, raytraceresult.field_72307_f.field_72449_c);
            entityboat.func_184458_a(this.field_185057_a);
            entityboat.field_70177_z = p_77659_3_.field_70177_z;
            if(!p_77659_2_.func_184144_a(entityboat, entityboat.func_174813_aQ().func_186662_g(-0.1D)).isEmpty()) {
               return new ActionResult(EnumActionResult.FAIL, p_77659_1_);
            } else {
               if(!p_77659_2_.field_72995_K) {
                  p_77659_2_.func_72838_d(entityboat);
               }

               if(!p_77659_3_.field_71075_bZ.field_75098_d) {
                  --p_77659_1_.field_77994_a;
               }

               p_77659_3_.func_71029_a(StatList.func_188057_b(this));
               return new ActionResult(EnumActionResult.SUCCESS, p_77659_1_);
            }
         }
      }
   }
}
