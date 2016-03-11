package net.minecraft.client.particle;

import java.util.List;
import java.util.Random;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityFX {
   private static final AxisAlignedBB field_187121_a = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
   protected World field_187122_b;
   protected double field_187123_c;
   protected double field_187124_d;
   protected double field_187125_e;
   protected double field_187126_f;
   protected double field_187127_g;
   protected double field_187128_h;
   protected double field_187129_i;
   protected double field_187130_j;
   protected double field_187131_k;
   private AxisAlignedBB field_187120_G;
   protected boolean field_187132_l;
   protected boolean field_187133_m;
   protected float field_187134_n;
   protected float field_187135_o;
   protected Random field_187136_p;
   protected int field_94054_b;
   protected int field_94055_c;
   protected float field_70548_b;
   protected float field_70549_c;
   protected int field_70546_d;
   protected int field_70547_e;
   protected float field_70544_f;
   protected float field_70545_g;
   protected float field_70552_h;
   protected float field_70553_i;
   protected float field_70551_j;
   protected float field_82339_as;
   protected TextureAtlasSprite field_187119_C;
   public static double field_70556_an;
   public static double field_70554_ao;
   public static double field_70555_ap;

   protected EntityFX(World p_i46352_1_, double p_i46352_2_, double p_i46352_4_, double p_i46352_6_) {
      this.field_187120_G = field_187121_a;
      this.field_187134_n = 0.6F;
      this.field_187135_o = 1.8F;
      this.field_187136_p = new Random();
      this.field_82339_as = 1.0F;
      this.field_187122_b = p_i46352_1_;
      this.func_187115_a(0.2F, 0.2F);
      this.func_187109_b(p_i46352_2_, p_i46352_4_, p_i46352_6_);
      this.field_187123_c = p_i46352_2_;
      this.field_187124_d = p_i46352_4_;
      this.field_187125_e = p_i46352_6_;
      this.field_70552_h = this.field_70553_i = this.field_70551_j = 1.0F;
      this.field_70548_b = this.field_187136_p.nextFloat() * 3.0F;
      this.field_70549_c = this.field_187136_p.nextFloat() * 3.0F;
      this.field_70544_f = (this.field_187136_p.nextFloat() * 0.5F + 0.5F) * 2.0F;
      this.field_70547_e = (int)(4.0F / (this.field_187136_p.nextFloat() * 0.9F + 0.1F));
      this.field_70546_d = 0;
   }

   public EntityFX(World p_i1219_1_, double p_i1219_2_, double p_i1219_4_, double p_i1219_6_, double p_i1219_8_, double p_i1219_10_, double p_i1219_12_) {
      this(p_i1219_1_, p_i1219_2_, p_i1219_4_, p_i1219_6_);
      this.field_187129_i = p_i1219_8_ + (Math.random() * 2.0D - 1.0D) * 0.4000000059604645D;
      this.field_187130_j = p_i1219_10_ + (Math.random() * 2.0D - 1.0D) * 0.4000000059604645D;
      this.field_187131_k = p_i1219_12_ + (Math.random() * 2.0D - 1.0D) * 0.4000000059604645D;
      float f = (float)(Math.random() + Math.random() + 1.0D) * 0.15F;
      float f1 = MathHelper.func_76133_a(this.field_187129_i * this.field_187129_i + this.field_187130_j * this.field_187130_j + this.field_187131_k * this.field_187131_k);
      this.field_187129_i = this.field_187129_i / (double)f1 * (double)f * 0.4000000059604645D;
      this.field_187130_j = this.field_187130_j / (double)f1 * (double)f * 0.4000000059604645D + 0.10000000149011612D;
      this.field_187131_k = this.field_187131_k / (double)f1 * (double)f * 0.4000000059604645D;
   }

   public EntityFX func_70543_e(float p_70543_1_) {
      this.field_187129_i *= (double)p_70543_1_;
      this.field_187130_j = (this.field_187130_j - 0.10000000149011612D) * (double)p_70543_1_ + 0.10000000149011612D;
      this.field_187131_k *= (double)p_70543_1_;
      return this;
   }

   public EntityFX func_70541_f(float p_70541_1_) {
      this.func_187115_a(0.2F * p_70541_1_, 0.2F * p_70541_1_);
      this.field_70544_f *= p_70541_1_;
      return this;
   }

   public void func_70538_b(float p_70538_1_, float p_70538_2_, float p_70538_3_) {
      this.field_70552_h = p_70538_1_;
      this.field_70553_i = p_70538_2_;
      this.field_70551_j = p_70538_3_;
   }

   public void func_82338_g(float p_82338_1_) {
      this.field_82339_as = p_82338_1_;
   }

   public boolean func_187111_c() {
      return false;
   }

   public float func_70534_d() {
      return this.field_70552_h;
   }

   public float func_70542_f() {
      return this.field_70553_i;
   }

   public float func_70535_g() {
      return this.field_70551_j;
   }

   public void func_187114_a(int p_187114_1_) {
      this.field_70547_e = p_187114_1_;
   }

   public void func_189213_a() {
      this.field_187123_c = this.field_187126_f;
      this.field_187124_d = this.field_187127_g;
      this.field_187125_e = this.field_187128_h;
      if(this.field_70546_d++ >= this.field_70547_e) {
         this.func_187112_i();
      }

      this.field_187130_j -= 0.04D * (double)this.field_70545_g;
      this.func_187110_a(this.field_187129_i, this.field_187130_j, this.field_187131_k);
      this.field_187129_i *= 0.9800000190734863D;
      this.field_187130_j *= 0.9800000190734863D;
      this.field_187131_k *= 0.9800000190734863D;
      if(this.field_187132_l) {
         this.field_187129_i *= 0.699999988079071D;
         this.field_187131_k *= 0.699999988079071D;
      }

   }

   public void func_180434_a(VertexBuffer p_180434_1_, Entity p_180434_2_, float p_180434_3_, float p_180434_4_, float p_180434_5_, float p_180434_6_, float p_180434_7_, float p_180434_8_) {
      float f = (float)this.field_94054_b / 16.0F;
      float f1 = f + 0.0624375F;
      float f2 = (float)this.field_94055_c / 16.0F;
      float f3 = f2 + 0.0624375F;
      float f4 = 0.1F * this.field_70544_f;
      if(this.field_187119_C != null) {
         f = this.field_187119_C.func_94209_e();
         f1 = this.field_187119_C.func_94212_f();
         f2 = this.field_187119_C.func_94206_g();
         f3 = this.field_187119_C.func_94210_h();
      }

      float f5 = (float)(this.field_187123_c + (this.field_187126_f - this.field_187123_c) * (double)p_180434_3_ - field_70556_an);
      float f6 = (float)(this.field_187124_d + (this.field_187127_g - this.field_187124_d) * (double)p_180434_3_ - field_70554_ao);
      float f7 = (float)(this.field_187125_e + (this.field_187128_h - this.field_187125_e) * (double)p_180434_3_ - field_70555_ap);
      int i = this.func_189214_a(p_180434_3_);
      int j = i >> 16 & '\uffff';
      int k = i & '\uffff';
      p_180434_1_.func_181662_b((double)(f5 - p_180434_4_ * f4 - p_180434_7_ * f4), (double)(f6 - p_180434_5_ * f4), (double)(f7 - p_180434_6_ * f4 - p_180434_8_ * f4)).func_187315_a((double)f1, (double)f3).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, this.field_82339_as).func_187314_a(j, k).func_181675_d();
      p_180434_1_.func_181662_b((double)(f5 - p_180434_4_ * f4 + p_180434_7_ * f4), (double)(f6 + p_180434_5_ * f4), (double)(f7 - p_180434_6_ * f4 + p_180434_8_ * f4)).func_187315_a((double)f1, (double)f2).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, this.field_82339_as).func_187314_a(j, k).func_181675_d();
      p_180434_1_.func_181662_b((double)(f5 + p_180434_4_ * f4 + p_180434_7_ * f4), (double)(f6 + p_180434_5_ * f4), (double)(f7 + p_180434_6_ * f4 + p_180434_8_ * f4)).func_187315_a((double)f, (double)f2).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, this.field_82339_as).func_187314_a(j, k).func_181675_d();
      p_180434_1_.func_181662_b((double)(f5 + p_180434_4_ * f4 - p_180434_7_ * f4), (double)(f6 - p_180434_5_ * f4), (double)(f7 + p_180434_6_ * f4 - p_180434_8_ * f4)).func_187315_a((double)f, (double)f3).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, this.field_82339_as).func_187314_a(j, k).func_181675_d();
   }

   public int func_70537_b() {
      return 0;
   }

   public void func_187117_a(TextureAtlasSprite p_187117_1_) {
      int i = this.func_70537_b();
      if(i == 1) {
         this.field_187119_C = p_187117_1_;
      } else {
         throw new RuntimeException("Invalid call to Particle.setTex, use coordinate methods");
      }
   }

   public void func_70536_a(int p_70536_1_) {
      if(this.func_70537_b() != 0) {
         throw new RuntimeException("Invalid call to Particle.setMiscTex");
      } else {
         this.field_94054_b = p_70536_1_ % 16;
         this.field_94055_c = p_70536_1_ / 16;
      }
   }

   public void func_94053_h() {
      ++this.field_94054_b;
   }

   public String toString() {
      return this.getClass().getSimpleName() + ", Pos (" + this.field_187126_f + "," + this.field_187127_g + "," + this.field_187128_h + "), RGBA (" + this.field_70552_h + "," + this.field_70553_i + "," + this.field_70551_j + "," + this.field_82339_as + "), Age " + this.field_70546_d;
   }

   public void func_187112_i() {
      this.field_187133_m = true;
   }

   protected void func_187115_a(float p_187115_1_, float p_187115_2_) {
      if(p_187115_1_ != this.field_187134_n || p_187115_2_ != this.field_187135_o) {
         this.field_187134_n = p_187115_1_;
         this.field_187135_o = p_187115_2_;
         AxisAlignedBB axisalignedbb = this.func_187116_l();
         this.func_187108_a(new AxisAlignedBB(axisalignedbb.field_72340_a, axisalignedbb.field_72338_b, axisalignedbb.field_72339_c, axisalignedbb.field_72340_a + (double)this.field_187134_n, axisalignedbb.field_72338_b + (double)this.field_187135_o, axisalignedbb.field_72339_c + (double)this.field_187134_n));
      }

   }

   public void func_187109_b(double p_187109_1_, double p_187109_3_, double p_187109_5_) {
      this.field_187126_f = p_187109_1_;
      this.field_187127_g = p_187109_3_;
      this.field_187128_h = p_187109_5_;
      float f = this.field_187134_n / 2.0F;
      float f1 = this.field_187135_o;
      this.func_187108_a(new AxisAlignedBB(p_187109_1_ - (double)f, p_187109_3_, p_187109_5_ - (double)f, p_187109_1_ + (double)f, p_187109_3_ + (double)f1, p_187109_5_ + (double)f));
   }

   public void func_187110_a(double p_187110_1_, double p_187110_3_, double p_187110_5_) {
      double d0 = p_187110_1_;
      double d1 = p_187110_3_;
      double d2 = p_187110_5_;
      List<AxisAlignedBB> list = this.field_187122_b.func_184144_a((Entity)null, this.func_187116_l().func_72321_a(p_187110_1_, p_187110_3_, p_187110_5_));

      for(AxisAlignedBB axisalignedbb : list) {
         p_187110_3_ = axisalignedbb.func_72323_b(this.func_187116_l(), p_187110_3_);
      }

      this.func_187108_a(this.func_187116_l().func_72317_d(0.0D, p_187110_3_, 0.0D));

      for(AxisAlignedBB axisalignedbb1 : list) {
         p_187110_1_ = axisalignedbb1.func_72316_a(this.func_187116_l(), p_187110_1_);
      }

      this.func_187108_a(this.func_187116_l().func_72317_d(p_187110_1_, 0.0D, 0.0D));

      for(AxisAlignedBB axisalignedbb2 : list) {
         p_187110_5_ = axisalignedbb2.func_72322_c(this.func_187116_l(), p_187110_5_);
      }

      this.func_187108_a(this.func_187116_l().func_72317_d(0.0D, 0.0D, p_187110_5_));
      this.func_187118_j();
      this.field_187132_l = d1 != p_187110_3_ && d1 < 0.0D;
      if(d0 != p_187110_1_) {
         this.field_187129_i = 0.0D;
      }

      if(d2 != p_187110_5_) {
         this.field_187131_k = 0.0D;
      }

   }

   protected void func_187118_j() {
      AxisAlignedBB axisalignedbb = this.func_187116_l();
      this.field_187126_f = (axisalignedbb.field_72340_a + axisalignedbb.field_72336_d) / 2.0D;
      this.field_187127_g = axisalignedbb.field_72338_b;
      this.field_187128_h = (axisalignedbb.field_72339_c + axisalignedbb.field_72334_f) / 2.0D;
   }

   public int func_189214_a(float p_189214_1_) {
      BlockPos blockpos = new BlockPos(this.field_187126_f, this.field_187127_g, this.field_187128_h);
      return this.field_187122_b.func_175667_e(blockpos)?this.field_187122_b.func_175626_b(blockpos, 0):0;
   }

   public boolean func_187113_k() {
      return !this.field_187133_m;
   }

   public AxisAlignedBB func_187116_l() {
      return this.field_187120_G;
   }

   public void func_187108_a(AxisAlignedBB p_187108_1_) {
      this.field_187120_G = p_187108_1_;
   }
}
