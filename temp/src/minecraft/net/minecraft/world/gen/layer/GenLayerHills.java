package net.minecraft.world.gen.layer;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GenLayerHills extends GenLayer {
   private static final Logger field_151629_c = LogManager.getLogger();
   private GenLayer field_151628_d;

   public GenLayerHills(long p_i45479_1_, GenLayer p_i45479_3_, GenLayer p_i45479_4_) {
      super(p_i45479_1_);
      this.field_75909_a = p_i45479_3_;
      this.field_151628_d = p_i45479_4_;
   }

   public int[] func_75904_a(int p_75904_1_, int p_75904_2_, int p_75904_3_, int p_75904_4_) {
      int[] aint = this.field_75909_a.func_75904_a(p_75904_1_ - 1, p_75904_2_ - 1, p_75904_3_ + 2, p_75904_4_ + 2);
      int[] aint1 = this.field_151628_d.func_75904_a(p_75904_1_ - 1, p_75904_2_ - 1, p_75904_3_ + 2, p_75904_4_ + 2);
      int[] aint2 = IntCache.func_76445_a(p_75904_3_ * p_75904_4_);

      for(int i = 0; i < p_75904_4_; ++i) {
         for(int j = 0; j < p_75904_3_; ++j) {
            this.func_75903_a((long)(j + p_75904_1_), (long)(i + p_75904_2_));
            int k = aint[j + 1 + (i + 1) * (p_75904_3_ + 2)];
            int l = aint1[j + 1 + (i + 1) * (p_75904_3_ + 2)];
            boolean flag = (l - 2) % 29 == 0;
            if(k > 255) {
               field_151629_c.debug("old! " + k);
            }

            BiomeGenBase biomegenbase = BiomeGenBase.func_185357_a(k);
            boolean flag1 = biomegenbase != null && biomegenbase.func_185363_b();
            if(k != 0 && l >= 2 && (l - 2) % 29 == 1 && !flag1) {
               BiomeGenBase biomegenbase3 = BiomeGenBase.func_185356_b(biomegenbase);
               aint2[j + i * p_75904_3_] = biomegenbase3 == null?k:BiomeGenBase.func_185362_a(biomegenbase3);
            } else if(this.func_75902_a(3) != 0 && !flag) {
               aint2[j + i * p_75904_3_] = k;
            } else {
               BiomeGenBase biomegenbase1 = biomegenbase;
               if(biomegenbase == Biomes.field_76769_d) {
                  biomegenbase1 = Biomes.field_76786_s;
               } else if(biomegenbase == Biomes.field_76767_f) {
                  biomegenbase1 = Biomes.field_76785_t;
               } else if(biomegenbase == Biomes.field_150583_P) {
                  biomegenbase1 = Biomes.field_150582_Q;
               } else if(biomegenbase == Biomes.field_150585_R) {
                  biomegenbase1 = Biomes.field_76772_c;
               } else if(biomegenbase == Biomes.field_76768_g) {
                  biomegenbase1 = Biomes.field_76784_u;
               } else if(biomegenbase == Biomes.field_150578_U) {
                  biomegenbase1 = Biomes.field_150581_V;
               } else if(biomegenbase == Biomes.field_150584_S) {
                  biomegenbase1 = Biomes.field_150579_T;
               } else if(biomegenbase == Biomes.field_76772_c) {
                  if(this.func_75902_a(3) == 0) {
                     biomegenbase1 = Biomes.field_76785_t;
                  } else {
                     biomegenbase1 = Biomes.field_76767_f;
                  }
               } else if(biomegenbase == Biomes.field_76774_n) {
                  biomegenbase1 = Biomes.field_76775_o;
               } else if(biomegenbase == Biomes.field_76782_w) {
                  biomegenbase1 = Biomes.field_76792_x;
               } else if(biomegenbase == Biomes.field_76771_b) {
                  biomegenbase1 = Biomes.field_150575_M;
               } else if(biomegenbase == Biomes.field_76770_e) {
                  biomegenbase1 = Biomes.field_150580_W;
               } else if(biomegenbase == Biomes.field_150588_X) {
                  biomegenbase1 = Biomes.field_150587_Y;
               } else if(func_151616_a(k, BiomeGenBase.func_185362_a(Biomes.field_150607_aa))) {
                  biomegenbase1 = Biomes.field_150589_Z;
               } else if(biomegenbase == Biomes.field_150575_M && this.func_75902_a(3) == 0) {
                  int i1 = this.func_75902_a(2);
                  if(i1 == 0) {
                     biomegenbase1 = Biomes.field_76772_c;
                  } else {
                     biomegenbase1 = Biomes.field_76767_f;
                  }
               }

               int j2 = BiomeGenBase.func_185362_a(biomegenbase1);
               if(flag && j2 != k) {
                  BiomeGenBase biomegenbase2 = BiomeGenBase.func_185356_b(biomegenbase1);
                  j2 = biomegenbase2 == null?k:BiomeGenBase.func_185362_a(biomegenbase2);
               }

               if(j2 == k) {
                  aint2[j + i * p_75904_3_] = k;
               } else {
                  int k2 = aint[j + 1 + (i + 0) * (p_75904_3_ + 2)];
                  int j1 = aint[j + 2 + (i + 1) * (p_75904_3_ + 2)];
                  int k1 = aint[j + 0 + (i + 1) * (p_75904_3_ + 2)];
                  int l1 = aint[j + 1 + (i + 2) * (p_75904_3_ + 2)];
                  int i2 = 0;
                  if(func_151616_a(k2, k)) {
                     ++i2;
                  }

                  if(func_151616_a(j1, k)) {
                     ++i2;
                  }

                  if(func_151616_a(k1, k)) {
                     ++i2;
                  }

                  if(func_151616_a(l1, k)) {
                     ++i2;
                  }

                  if(i2 >= 3) {
                     aint2[j + i * p_75904_3_] = j2;
                  } else {
                     aint2[j + i * p_75904_3_] = k;
                  }
               }
            }
         }
      }

      return aint2;
   }
}
