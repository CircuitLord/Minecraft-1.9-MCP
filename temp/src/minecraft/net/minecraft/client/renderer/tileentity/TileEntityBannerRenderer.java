package net.minecraft.client.renderer.tileentity;

import net.minecraft.client.model.ModelBanner;
import net.minecraft.client.renderer.BannerTextures;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityBanner;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class TileEntityBannerRenderer extends TileEntitySpecialRenderer<TileEntityBanner> {
   private ModelBanner field_178465_e = new ModelBanner();

   public void func_180535_a(TileEntityBanner p_180535_1_, double p_180535_2_, double p_180535_4_, double p_180535_6_, float p_180535_8_, int p_180535_9_) {
      boolean flag = p_180535_1_.func_145831_w() != null;
      boolean flag1 = !flag || p_180535_1_.func_145838_q() == Blocks.field_180393_cK;
      int i = flag?p_180535_1_.func_145832_p():0;
      long j = flag?p_180535_1_.func_145831_w().func_82737_E():0L;
      GlStateManager.func_179094_E();
      float f = 0.6666667F;
      if(flag1) {
         GlStateManager.func_179109_b((float)p_180535_2_ + 0.5F, (float)p_180535_4_ + 0.75F * f, (float)p_180535_6_ + 0.5F);
         float f1 = (float)(i * 360) / 16.0F;
         GlStateManager.func_179114_b(-f1, 0.0F, 1.0F, 0.0F);
         this.field_178465_e.field_178688_b.field_78806_j = true;
      } else {
         float f2 = 0.0F;
         if(i == 2) {
            f2 = 180.0F;
         }

         if(i == 4) {
            f2 = 90.0F;
         }

         if(i == 5) {
            f2 = -90.0F;
         }

         GlStateManager.func_179109_b((float)p_180535_2_ + 0.5F, (float)p_180535_4_ - 0.25F * f, (float)p_180535_6_ + 0.5F);
         GlStateManager.func_179114_b(-f2, 0.0F, 1.0F, 0.0F);
         GlStateManager.func_179109_b(0.0F, -0.3125F, -0.4375F);
         this.field_178465_e.field_178688_b.field_78806_j = false;
      }

      BlockPos blockpos = p_180535_1_.func_174877_v();
      float f3 = (float)(blockpos.func_177958_n() * 7 + blockpos.func_177956_o() * 9 + blockpos.func_177952_p() * 13) + (float)j + p_180535_8_;
      this.field_178465_e.field_178690_a.field_78795_f = (-0.0125F + 0.01F * MathHelper.func_76134_b(f3 * 3.1415927F * 0.02F)) * 3.1415927F;
      GlStateManager.func_179091_B();
      ResourceLocation resourcelocation = this.func_178463_a(p_180535_1_);
      if(resourcelocation != null) {
         this.func_147499_a(resourcelocation);
         GlStateManager.func_179094_E();
         GlStateManager.func_179152_a(f, -f, -f);
         this.field_178465_e.func_178687_a();
         GlStateManager.func_179121_F();
      }

      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.func_179121_F();
   }

   private ResourceLocation func_178463_a(TileEntityBanner p_178463_1_) {
      return BannerTextures.field_178466_c.func_187478_a(p_178463_1_.func_175116_e(), p_178463_1_.func_175114_c(), p_178463_1_.func_175110_d());
   }
}
