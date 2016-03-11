package net.minecraft.entity.projectile;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootTableList;

public class EntityFishHook extends Entity {
   private static final DataParameter<Integer> field_184528_c = EntityDataManager.<Integer>func_187226_a(EntityFishHook.class, DataSerializers.field_187192_b);
   private int field_146037_g;
   private int field_146048_h;
   private int field_146050_i;
   private Block field_146046_j;
   private boolean field_146051_au;
   public EntityPlayer field_146042_b;
   private int field_146049_av;
   private int field_146047_aw;
   private int field_146045_ax;
   private int field_146040_ay;
   private int field_146038_az;
   private float field_146054_aA;
   public Entity field_146043_c;
   private int field_146055_aB;
   private double field_146056_aC;
   private double field_146057_aD;
   private double field_146058_aE;
   private double field_146059_aF;
   private double field_146060_aG;
   private double field_146061_aH;
   private double field_146052_aI;
   private double field_146053_aJ;

   public EntityFishHook(World p_i1764_1_) {
      super(p_i1764_1_);
      this.field_146037_g = -1;
      this.field_146048_h = -1;
      this.field_146050_i = -1;
      this.func_70105_a(0.25F, 0.25F);
      this.field_70158_ak = true;
   }

   public EntityFishHook(World p_i1765_1_, double p_i1765_2_, double p_i1765_4_, double p_i1765_6_, EntityPlayer p_i1765_8_) {
      this(p_i1765_1_);
      this.func_70107_b(p_i1765_2_, p_i1765_4_, p_i1765_6_);
      this.field_70158_ak = true;
      this.field_146042_b = p_i1765_8_;
      p_i1765_8_.field_71104_cf = this;
   }

   public EntityFishHook(World p_i1766_1_, EntityPlayer p_i1766_2_) {
      super(p_i1766_1_);
      this.field_146037_g = -1;
      this.field_146048_h = -1;
      this.field_146050_i = -1;
      this.field_70158_ak = true;
      this.field_146042_b = p_i1766_2_;
      this.field_146042_b.field_71104_cf = this;
      this.func_70105_a(0.25F, 0.25F);
      this.func_70012_b(p_i1766_2_.field_70165_t, p_i1766_2_.field_70163_u + (double)p_i1766_2_.func_70047_e(), p_i1766_2_.field_70161_v, p_i1766_2_.field_70177_z, p_i1766_2_.field_70125_A);
      this.field_70165_t -= (double)(MathHelper.func_76134_b(this.field_70177_z * 0.017453292F) * 0.16F);
      this.field_70163_u -= 0.10000000149011612D;
      this.field_70161_v -= (double)(MathHelper.func_76126_a(this.field_70177_z * 0.017453292F) * 0.16F);
      this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
      float f = 0.4F;
      this.field_70159_w = (double)(-MathHelper.func_76126_a(this.field_70177_z * 0.017453292F) * MathHelper.func_76134_b(this.field_70125_A * 0.017453292F) * f);
      this.field_70179_y = (double)(MathHelper.func_76134_b(this.field_70177_z * 0.017453292F) * MathHelper.func_76134_b(this.field_70125_A * 0.017453292F) * f);
      this.field_70181_x = (double)(-MathHelper.func_76126_a(this.field_70125_A * 0.017453292F) * f);
      this.func_146035_c(this.field_70159_w, this.field_70181_x, this.field_70179_y, 1.5F, 1.0F);
   }

   protected void func_70088_a() {
      this.func_184212_Q().func_187214_a(field_184528_c, Integer.valueOf(0));
   }

   public void func_184206_a(DataParameter<?> p_184206_1_) {
      if(field_184528_c.equals(p_184206_1_)) {
         int i = ((Integer)this.func_184212_Q().func_187225_a(field_184528_c)).intValue();
         if(i > 0 && this.field_146043_c != null) {
            this.field_146043_c = null;
         }
      }

      super.func_184206_a(p_184206_1_);
   }

   public boolean func_70112_a(double p_70112_1_) {
      double d0 = this.func_174813_aQ().func_72320_b() * 4.0D;
      if(Double.isNaN(d0)) {
         d0 = 4.0D;
      }

      d0 = d0 * 64.0D;
      return p_70112_1_ < d0 * d0;
   }

   public void func_146035_c(double p_146035_1_, double p_146035_3_, double p_146035_5_, float p_146035_7_, float p_146035_8_) {
      float f = MathHelper.func_76133_a(p_146035_1_ * p_146035_1_ + p_146035_3_ * p_146035_3_ + p_146035_5_ * p_146035_5_);
      p_146035_1_ = p_146035_1_ / (double)f;
      p_146035_3_ = p_146035_3_ / (double)f;
      p_146035_5_ = p_146035_5_ / (double)f;
      p_146035_1_ = p_146035_1_ + this.field_70146_Z.nextGaussian() * 0.007499999832361937D * (double)p_146035_8_;
      p_146035_3_ = p_146035_3_ + this.field_70146_Z.nextGaussian() * 0.007499999832361937D * (double)p_146035_8_;
      p_146035_5_ = p_146035_5_ + this.field_70146_Z.nextGaussian() * 0.007499999832361937D * (double)p_146035_8_;
      p_146035_1_ = p_146035_1_ * (double)p_146035_7_;
      p_146035_3_ = p_146035_3_ * (double)p_146035_7_;
      p_146035_5_ = p_146035_5_ * (double)p_146035_7_;
      this.field_70159_w = p_146035_1_;
      this.field_70181_x = p_146035_3_;
      this.field_70179_y = p_146035_5_;
      float f1 = MathHelper.func_76133_a(p_146035_1_ * p_146035_1_ + p_146035_5_ * p_146035_5_);
      this.field_70126_B = this.field_70177_z = (float)(MathHelper.func_181159_b(p_146035_1_, p_146035_5_) * 57.2957763671875D);
      this.field_70127_C = this.field_70125_A = (float)(MathHelper.func_181159_b(p_146035_3_, (double)f1) * 57.2957763671875D);
      this.field_146049_av = 0;
   }

   public void func_180426_a(double p_180426_1_, double p_180426_3_, double p_180426_5_, float p_180426_7_, float p_180426_8_, int p_180426_9_, boolean p_180426_10_) {
      this.field_146056_aC = p_180426_1_;
      this.field_146057_aD = p_180426_3_;
      this.field_146058_aE = p_180426_5_;
      this.field_146059_aF = (double)p_180426_7_;
      this.field_146060_aG = (double)p_180426_8_;
      this.field_146055_aB = p_180426_9_;
      this.field_70159_w = this.field_146061_aH;
      this.field_70181_x = this.field_146052_aI;
      this.field_70179_y = this.field_146053_aJ;
   }

   public void func_70016_h(double p_70016_1_, double p_70016_3_, double p_70016_5_) {
      this.field_146061_aH = this.field_70159_w = p_70016_1_;
      this.field_146052_aI = this.field_70181_x = p_70016_3_;
      this.field_146053_aJ = this.field_70179_y = p_70016_5_;
   }

   public void func_70071_h_() {
      super.func_70071_h_();
      if(this.field_70170_p.field_72995_K) {
         int i = ((Integer)this.func_184212_Q().func_187225_a(field_184528_c)).intValue();
         if(i > 0 && this.field_146043_c == null) {
            this.field_146043_c = this.field_70170_p.func_73045_a(i - 1);
         }
      } else {
         ItemStack itemstack = this.field_146042_b.func_184614_ca();
         if(this.field_146042_b.field_70128_L || !this.field_146042_b.func_70089_S() || itemstack == null || itemstack.func_77973_b() != Items.field_151112_aM || this.func_70068_e(this.field_146042_b) > 1024.0D) {
            this.func_70106_y();
            this.field_146042_b.field_71104_cf = null;
            return;
         }
      }

      if(this.field_146043_c != null) {
         if(!this.field_146043_c.field_70128_L) {
            this.field_70165_t = this.field_146043_c.field_70165_t;
            double d17 = (double)this.field_146043_c.field_70131_O;
            this.field_70163_u = this.field_146043_c.func_174813_aQ().field_72338_b + d17 * 0.8D;
            this.field_70161_v = this.field_146043_c.field_70161_v;
            return;
         }

         this.field_146043_c = null;
      }

      if(this.field_146055_aB > 0) {
         double d3 = this.field_70165_t + (this.field_146056_aC - this.field_70165_t) / (double)this.field_146055_aB;
         double d4 = this.field_70163_u + (this.field_146057_aD - this.field_70163_u) / (double)this.field_146055_aB;
         double d6 = this.field_70161_v + (this.field_146058_aE - this.field_70161_v) / (double)this.field_146055_aB;
         double d8 = MathHelper.func_76138_g(this.field_146059_aF - (double)this.field_70177_z);
         this.field_70177_z = (float)((double)this.field_70177_z + d8 / (double)this.field_146055_aB);
         this.field_70125_A = (float)((double)this.field_70125_A + (this.field_146060_aG - (double)this.field_70125_A) / (double)this.field_146055_aB);
         --this.field_146055_aB;
         this.func_70107_b(d3, d4, d6);
         this.func_70101_b(this.field_70177_z, this.field_70125_A);
      } else {
         if(this.field_146051_au) {
            if(this.field_70170_p.func_180495_p(new BlockPos(this.field_146037_g, this.field_146048_h, this.field_146050_i)).func_177230_c() == this.field_146046_j) {
               ++this.field_146049_av;
               if(this.field_146049_av == 1200) {
                  this.func_70106_y();
               }

               return;
            }

            this.field_146051_au = false;
            this.field_70159_w *= (double)(this.field_70146_Z.nextFloat() * 0.2F);
            this.field_70181_x *= (double)(this.field_70146_Z.nextFloat() * 0.2F);
            this.field_70179_y *= (double)(this.field_70146_Z.nextFloat() * 0.2F);
            this.field_146049_av = 0;
            this.field_146047_aw = 0;
         } else {
            ++this.field_146047_aw;
         }

         if(!this.field_70170_p.field_72995_K) {
            Vec3d vec3d1 = new Vec3d(this.field_70165_t, this.field_70163_u, this.field_70161_v);
            Vec3d vec3d = new Vec3d(this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);
            RayTraceResult raytraceresult = this.field_70170_p.func_72933_a(vec3d1, vec3d);
            vec3d1 = new Vec3d(this.field_70165_t, this.field_70163_u, this.field_70161_v);
            vec3d = new Vec3d(this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);
            if(raytraceresult != null) {
               vec3d = new Vec3d(raytraceresult.field_72307_f.field_72450_a, raytraceresult.field_72307_f.field_72448_b, raytraceresult.field_72307_f.field_72449_c);
            }

            Entity entity = null;
            List<Entity> list = this.field_70170_p.func_72839_b(this, this.func_174813_aQ().func_72321_a(this.field_70159_w, this.field_70181_x, this.field_70179_y).func_186662_g(1.0D));
            double d0 = 0.0D;

            for(int j = 0; j < list.size(); ++j) {
               Entity entity1 = (Entity)list.get(j);
               if(entity1.func_70067_L() && (entity1 != this.field_146042_b || this.field_146047_aw >= 5)) {
                  AxisAlignedBB axisalignedbb1 = entity1.func_174813_aQ().func_186662_g(0.30000001192092896D);
                  RayTraceResult raytraceresult1 = axisalignedbb1.func_72327_a(vec3d1, vec3d);
                  if(raytraceresult1 != null) {
                     double d1 = vec3d1.func_72436_e(raytraceresult1.field_72307_f);
                     if(d1 < d0 || d0 == 0.0D) {
                        entity = entity1;
                        d0 = d1;
                     }
                  }
               }
            }

            if(entity != null) {
               raytraceresult = new RayTraceResult(entity);
            }

            if(raytraceresult != null) {
               if(raytraceresult.field_72308_g != null) {
                  this.field_146043_c = raytraceresult.field_72308_g;
                  this.func_184212_Q().func_187227_b(field_184528_c, Integer.valueOf(this.field_146043_c.func_145782_y() + 1));
               } else {
                  this.field_146051_au = true;
               }
            }
         }

         if(!this.field_146051_au) {
            this.func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
            float f2 = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
            this.field_70177_z = (float)(MathHelper.func_181159_b(this.field_70159_w, this.field_70179_y) * 57.2957763671875D);

            for(this.field_70125_A = (float)(MathHelper.func_181159_b(this.field_70181_x, (double)f2) * 57.2957763671875D); this.field_70125_A - this.field_70127_C < -180.0F; this.field_70127_C -= 360.0F) {
               ;
            }

            while(this.field_70125_A - this.field_70127_C >= 180.0F) {
               this.field_70127_C += 360.0F;
            }

            while(this.field_70177_z - this.field_70126_B < -180.0F) {
               this.field_70126_B -= 360.0F;
            }

            while(this.field_70177_z - this.field_70126_B >= 180.0F) {
               this.field_70126_B += 360.0F;
            }

            this.field_70125_A = this.field_70127_C + (this.field_70125_A - this.field_70127_C) * 0.2F;
            this.field_70177_z = this.field_70126_B + (this.field_70177_z - this.field_70126_B) * 0.2F;
            float f3 = 0.92F;
            if(this.field_70122_E || this.field_70123_F) {
               f3 = 0.5F;
            }

            int k = 5;
            double d5 = 0.0D;

            for(int l = 0; l < k; ++l) {
               AxisAlignedBB axisalignedbb = this.func_174813_aQ();
               double d9 = axisalignedbb.field_72337_e - axisalignedbb.field_72338_b;
               double d10 = axisalignedbb.field_72338_b + d9 * (double)l / (double)k;
               double d11 = axisalignedbb.field_72338_b + d9 * (double)(l + 1) / (double)k;
               AxisAlignedBB axisalignedbb2 = new AxisAlignedBB(axisalignedbb.field_72340_a, d10, axisalignedbb.field_72339_c, axisalignedbb.field_72336_d, d11, axisalignedbb.field_72334_f);
               if(this.field_70170_p.func_72830_b(axisalignedbb2, Material.field_151586_h)) {
                  d5 += 1.0D / (double)k;
               }
            }

            if(!this.field_70170_p.field_72995_K && d5 > 0.0D) {
               WorldServer worldserver = (WorldServer)this.field_70170_p;
               int i1 = 1;
               BlockPos blockpos = (new BlockPos(this)).func_177984_a();
               if(this.field_70146_Z.nextFloat() < 0.25F && this.field_70170_p.func_175727_C(blockpos)) {
                  i1 = 2;
               }

               if(this.field_70146_Z.nextFloat() < 0.5F && !this.field_70170_p.func_175678_i(blockpos)) {
                  --i1;
               }

               if(this.field_146045_ax > 0) {
                  --this.field_146045_ax;
                  if(this.field_146045_ax <= 0) {
                     this.field_146040_ay = 0;
                     this.field_146038_az = 0;
                  }
               } else if(this.field_146038_az > 0) {
                  this.field_146038_az -= i1;
                  if(this.field_146038_az <= 0) {
                     this.field_70181_x -= 0.20000000298023224D;
                     this.func_184185_a(SoundEvents.field_187609_F, 0.25F, 1.0F + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.4F);
                     float f6 = (float)MathHelper.func_76128_c(this.func_174813_aQ().field_72338_b);
                     worldserver.func_175739_a(EnumParticleTypes.WATER_BUBBLE, this.field_70165_t, (double)(f6 + 1.0F), this.field_70161_v, (int)(1.0F + this.field_70130_N * 20.0F), (double)this.field_70130_N, 0.0D, (double)this.field_70130_N, 0.20000000298023224D, new int[0]);
                     worldserver.func_175739_a(EnumParticleTypes.WATER_WAKE, this.field_70165_t, (double)(f6 + 1.0F), this.field_70161_v, (int)(1.0F + this.field_70130_N * 20.0F), (double)this.field_70130_N, 0.0D, (double)this.field_70130_N, 0.20000000298023224D, new int[0]);
                     this.field_146045_ax = MathHelper.func_76136_a(this.field_70146_Z, 10, 30);
                  } else {
                     this.field_146054_aA = (float)((double)this.field_146054_aA + this.field_70146_Z.nextGaussian() * 4.0D);
                     float f5 = this.field_146054_aA * 0.017453292F;
                     float f8 = MathHelper.func_76126_a(f5);
                     float f10 = MathHelper.func_76134_b(f5);
                     double d13 = this.field_70165_t + (double)(f8 * (float)this.field_146038_az * 0.1F);
                     double d15 = (double)((float)MathHelper.func_76128_c(this.func_174813_aQ().field_72338_b) + 1.0F);
                     double d16 = this.field_70161_v + (double)(f10 * (float)this.field_146038_az * 0.1F);
                     Block block1 = worldserver.func_180495_p(new BlockPos((int)d13, (int)d15 - 1, (int)d16)).func_177230_c();
                     if(block1 == Blocks.field_150355_j || block1 == Blocks.field_150358_i) {
                        if(this.field_70146_Z.nextFloat() < 0.15F) {
                           worldserver.func_175739_a(EnumParticleTypes.WATER_BUBBLE, d13, d15 - 0.10000000149011612D, d16, 1, (double)f8, 0.1D, (double)f10, 0.0D, new int[0]);
                        }

                        float f = f8 * 0.04F;
                        float f1 = f10 * 0.04F;
                        worldserver.func_175739_a(EnumParticleTypes.WATER_WAKE, d13, d15, d16, 0, (double)f1, 0.01D, (double)(-f), 1.0D, new int[0]);
                        worldserver.func_175739_a(EnumParticleTypes.WATER_WAKE, d13, d15, d16, 0, (double)(-f1), 0.01D, (double)f, 1.0D, new int[0]);
                     }
                  }
               } else if(this.field_146040_ay > 0) {
                  this.field_146040_ay -= i1;
                  float f4 = 0.15F;
                  if(this.field_146040_ay < 20) {
                     f4 = (float)((double)f4 + (double)(20 - this.field_146040_ay) * 0.05D);
                  } else if(this.field_146040_ay < 40) {
                     f4 = (float)((double)f4 + (double)(40 - this.field_146040_ay) * 0.02D);
                  } else if(this.field_146040_ay < 60) {
                     f4 = (float)((double)f4 + (double)(60 - this.field_146040_ay) * 0.01D);
                  }

                  if(this.field_70146_Z.nextFloat() < f4) {
                     float f7 = MathHelper.func_151240_a(this.field_70146_Z, 0.0F, 360.0F) * 0.017453292F;
                     float f9 = MathHelper.func_151240_a(this.field_70146_Z, 25.0F, 60.0F);
                     double d12 = this.field_70165_t + (double)(MathHelper.func_76126_a(f7) * f9 * 0.1F);
                     double d14 = (double)((float)MathHelper.func_76128_c(this.func_174813_aQ().field_72338_b) + 1.0F);
                     double d2 = this.field_70161_v + (double)(MathHelper.func_76134_b(f7) * f9 * 0.1F);
                     Block block = worldserver.func_180495_p(new BlockPos((int)d12, (int)d14 - 1, (int)d2)).func_177230_c();
                     if(block == Blocks.field_150355_j || block == Blocks.field_150358_i) {
                        worldserver.func_175739_a(EnumParticleTypes.WATER_SPLASH, d12, d14, d2, 2 + this.field_70146_Z.nextInt(2), 0.10000000149011612D, 0.0D, 0.10000000149011612D, 0.0D, new int[0]);
                     }
                  }

                  if(this.field_146040_ay <= 0) {
                     this.field_146054_aA = MathHelper.func_151240_a(this.field_70146_Z, 0.0F, 360.0F);
                     this.field_146038_az = MathHelper.func_76136_a(this.field_70146_Z, 20, 80);
                  }
               } else {
                  this.field_146040_ay = MathHelper.func_76136_a(this.field_70146_Z, 100, 900);
                  this.field_146040_ay -= EnchantmentHelper.func_151387_h(this.field_146042_b) * 20 * 5;
               }

               if(this.field_146045_ax > 0) {
                  this.field_70181_x -= (double)(this.field_70146_Z.nextFloat() * this.field_70146_Z.nextFloat() * this.field_70146_Z.nextFloat()) * 0.2D;
               }
            }

            double d7 = d5 * 2.0D - 1.0D;
            this.field_70181_x += 0.03999999910593033D * d7;
            if(d5 > 0.0D) {
               f3 = (float)((double)f3 * 0.9D);
               this.field_70181_x *= 0.8D;
            }

            this.field_70159_w *= (double)f3;
            this.field_70181_x *= (double)f3;
            this.field_70179_y *= (double)f3;
            this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
         }
      }
   }

   public void func_70014_b(NBTTagCompound p_70014_1_) {
      p_70014_1_.func_74768_a("xTile", this.field_146037_g);
      p_70014_1_.func_74768_a("yTile", this.field_146048_h);
      p_70014_1_.func_74768_a("zTile", this.field_146050_i);
      ResourceLocation resourcelocation = (ResourceLocation)Block.field_149771_c.func_177774_c(this.field_146046_j);
      p_70014_1_.func_74778_a("inTile", resourcelocation == null?"":resourcelocation.toString());
      p_70014_1_.func_74774_a("inGround", (byte)(this.field_146051_au?1:0));
   }

   public void func_70037_a(NBTTagCompound p_70037_1_) {
      this.field_146037_g = p_70037_1_.func_74762_e("xTile");
      this.field_146048_h = p_70037_1_.func_74762_e("yTile");
      this.field_146050_i = p_70037_1_.func_74762_e("zTile");
      if(p_70037_1_.func_150297_b("inTile", 8)) {
         this.field_146046_j = Block.func_149684_b(p_70037_1_.func_74779_i("inTile"));
      } else {
         this.field_146046_j = Block.func_149729_e(p_70037_1_.func_74771_c("inTile") & 255);
      }

      this.field_146051_au = p_70037_1_.func_74771_c("inGround") == 1;
   }

   public int func_146034_e() {
      if(this.field_70170_p.field_72995_K) {
         return 0;
      } else {
         int i = 0;
         if(this.field_146043_c != null) {
            this.func_184527_k();
            this.field_70170_p.func_72960_a(this, (byte)31);
            i = this.field_146043_c instanceof EntityItem?3:5;
         } else if(this.field_146045_ax > 0) {
            LootContext.Builder lootcontext$builder = new LootContext.Builder((WorldServer)this.field_70170_p);
            lootcontext$builder.func_186469_a((float)EnchantmentHelper.func_151386_g(this.field_146042_b) + this.field_146042_b.func_184817_da());

            for(ItemStack itemstack : this.field_70170_p.func_184146_ak().func_186521_a(LootTableList.field_186387_al).func_186462_a(this.field_70146_Z, lootcontext$builder.func_186471_a())) {
               EntityItem entityitem = new EntityItem(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, itemstack);
               double d0 = this.field_146042_b.field_70165_t - this.field_70165_t;
               double d1 = this.field_146042_b.field_70163_u - this.field_70163_u;
               double d2 = this.field_146042_b.field_70161_v - this.field_70161_v;
               double d3 = (double)MathHelper.func_76133_a(d0 * d0 + d1 * d1 + d2 * d2);
               double d4 = 0.1D;
               entityitem.field_70159_w = d0 * d4;
               entityitem.field_70181_x = d1 * d4 + (double)MathHelper.func_76133_a(d3) * 0.08D;
               entityitem.field_70179_y = d2 * d4;
               this.field_70170_p.func_72838_d(entityitem);
               this.field_146042_b.field_70170_p.func_72838_d(new EntityXPOrb(this.field_146042_b.field_70170_p, this.field_146042_b.field_70165_t, this.field_146042_b.field_70163_u + 0.5D, this.field_146042_b.field_70161_v + 0.5D, this.field_70146_Z.nextInt(6) + 1));
            }

            i = 1;
         }

         if(this.field_146051_au) {
            i = 2;
         }

         this.func_70106_y();
         this.field_146042_b.field_71104_cf = null;
         return i;
      }
   }

   public void func_70103_a(byte p_70103_1_) {
      if(p_70103_1_ == 31 && this.field_70170_p.field_72995_K && this.field_146043_c instanceof EntityPlayer && ((EntityPlayer)this.field_146043_c).func_175144_cb()) {
         this.func_184527_k();
      }

      super.func_70103_a(p_70103_1_);
   }

   protected void func_184527_k() {
      double d0 = this.field_146042_b.field_70165_t - this.field_70165_t;
      double d1 = this.field_146042_b.field_70163_u - this.field_70163_u;
      double d2 = this.field_146042_b.field_70161_v - this.field_70161_v;
      double d3 = (double)MathHelper.func_76133_a(d0 * d0 + d1 * d1 + d2 * d2);
      double d4 = 0.1D;
      this.field_146043_c.field_70159_w += d0 * d4;
      this.field_146043_c.field_70181_x += d1 * d4 + (double)MathHelper.func_76133_a(d3) * 0.08D;
      this.field_146043_c.field_70179_y += d2 * d4;
   }

   public void func_70106_y() {
      super.func_70106_y();
      if(this.field_146042_b != null) {
         this.field_146042_b.field_71104_cf = null;
      }

   }
}
