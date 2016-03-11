package net.minecraft.client.model;

import net.minecraft.client.model.IMultipassModel;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.util.math.MathHelper;

public class ModelBoat extends ModelBase implements IMultipassModel {
   public ModelRenderer[] field_78103_a = new ModelRenderer[5];
   public ModelRenderer[] field_187057_b = new ModelRenderer[2];
   public ModelRenderer field_187058_c;
   private int field_187059_d = GLAllocation.func_74526_a(1);

   public ModelBoat() {
      this.field_78103_a[0] = (new ModelRenderer(this, 0, 0)).func_78787_b(128, 64);
      this.field_78103_a[1] = (new ModelRenderer(this, 0, 19)).func_78787_b(128, 64);
      this.field_78103_a[2] = (new ModelRenderer(this, 0, 27)).func_78787_b(128, 64);
      this.field_78103_a[3] = (new ModelRenderer(this, 0, 35)).func_78787_b(128, 64);
      this.field_78103_a[4] = (new ModelRenderer(this, 0, 43)).func_78787_b(128, 64);
      int i = 32;
      int j = 6;
      int k = 20;
      int l = 4;
      int i1 = 28;
      this.field_78103_a[0].func_78790_a((float)(-i1 / 2), (float)(-k / 2 + 1), -3.0F, i1, k - 4, 3, 0.0F);
      this.field_78103_a[0].func_78793_a(0.0F, (float)(l - 1), 1.0F);
      this.field_78103_a[1].func_78790_a((float)(-i / 2 + 3), (float)(-j - 1), -1.0F, k - 2, j, 2, 0.0F);
      this.field_78103_a[1].func_78793_a((float)(-i / 2 + 1), (float)l, 4.0F);
      this.field_78103_a[2].func_78790_a((float)(-i / 2 + 8), (float)(-j - 1), -1.0F, k - 4, j, 2, 0.0F);
      this.field_78103_a[2].func_78793_a((float)(i / 2 - 1), (float)l, 0.0F);
      this.field_78103_a[3].func_78790_a((float)(-i / 2 + 2), (float)(-j - 1), -1.0F, i - 4, j, 2, 0.0F);
      this.field_78103_a[3].func_78793_a(0.0F, (float)l, (float)(-k / 2 + 1));
      this.field_78103_a[4].func_78790_a((float)(-i / 2 + 2), (float)(-j - 1), -1.0F, i - 4, j, 2, 0.0F);
      this.field_78103_a[4].func_78793_a(0.0F, (float)l, (float)(k / 2 - 1));
      this.field_78103_a[0].field_78795_f = 1.5707964F;
      this.field_78103_a[1].field_78796_g = 4.712389F;
      this.field_78103_a[2].field_78796_g = 1.5707964F;
      this.field_78103_a[3].field_78796_g = 3.1415927F;
      this.field_187057_b[0] = this.func_187056_a(true);
      this.field_187057_b[0].func_78793_a(3.0F, -5.0F, 9.0F);
      this.field_187057_b[1] = this.func_187056_a(false);
      this.field_187057_b[1].func_78793_a(3.0F, -5.0F, -9.0F);
      this.field_187057_b[1].field_78796_g = 3.1415927F;
      this.field_187057_b[0].field_78808_h = this.field_187057_b[1].field_78808_h = 0.19634955F;
      this.field_187058_c = (new ModelRenderer(this, 0, 0)).func_78787_b(128, 64);
      this.field_187058_c.func_78790_a((float)(-i1 / 2), (float)(-k / 2 + 1), -3.0F, i1, k - 4, 3, 0.0F);
      this.field_187058_c.func_78793_a(0.0F, (float)(l - 7), 1.0F);
      this.field_187058_c.field_78795_f = 1.5707964F;
   }

   public void func_78088_a(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
      GlStateManager.func_179114_b(90.0F, 0.0F, 1.0F, 0.0F);
      EntityBoat entityboat = (EntityBoat)p_78088_1_;
      this.func_78087_a(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);

      for(int i = 0; i < 5; ++i) {
         this.field_78103_a[i].func_78785_a(p_78088_7_);
      }

      this.func_187055_a(entityboat, 0, p_78088_7_, p_78088_2_);
      this.func_187055_a(entityboat, 1, p_78088_7_, p_78088_2_);
   }

   public void func_187054_b(Entity p_187054_1_, float p_187054_2_, float p_187054_3_, float p_187054_4_, float p_187054_5_, float p_187054_6_, float p_187054_7_) {
      GlStateManager.func_179114_b(90.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.func_179135_a(false, false, false, false);
      this.field_187058_c.func_78785_a(p_187054_7_);
      GlStateManager.func_179135_a(true, true, true, true);
   }

   public void func_78087_a(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_) {
   }

   ModelRenderer func_187056_a(boolean p_187056_1_) {
      ModelRenderer modelrenderer = (new ModelRenderer(this, 62, p_187056_1_?0:20)).func_78787_b(128, 64);
      int i = 20;
      int j = 7;
      int k = 6;
      float f = -5.0F;
      modelrenderer.func_78789_a(-1.0F, 0.0F, f, 2, 2, i - 2);
      modelrenderer.func_78789_a(p_187056_1_?-1.001F:0.001F, (float)(-k / 2), (float)(i - j) + f, 1, k, j);
      return modelrenderer;
   }

   void func_187055_a(EntityBoat p_187055_1_, int p_187055_2_, float p_187055_3_, float p_187055_4_) {
      float f = 40.0F;
      float f1 = p_187055_1_.func_184448_a(p_187055_2_, p_187055_4_) * f;
      ModelRenderer modelrenderer = this.field_187057_b[p_187055_2_];
      modelrenderer.field_78795_f = (float)MathHelper.func_151238_b(-1.0471975803375244D, -0.2617993950843811D, (double)((MathHelper.func_76126_a(-f1) + 1.0F) / 2.0F));
      modelrenderer.field_78796_g = (float)MathHelper.func_151238_b(-0.7853981852531433D, 0.7853981852531433D, (double)((MathHelper.func_76126_a(-f1 + 1.0F) + 1.0F) / 2.0F));
      if(p_187055_2_ == 1) {
         modelrenderer.field_78796_g = 3.1415927F - modelrenderer.field_78796_g;
      }

      modelrenderer.func_78785_a(p_187055_3_);
   }
}
