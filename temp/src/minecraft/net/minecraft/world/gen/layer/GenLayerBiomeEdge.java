package net.minecraft.world.gen.layer;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerBiomeEdge extends GenLayer {
   public GenLayerBiomeEdge(long p_i45475_1_, GenLayer p_i45475_3_) {
      super(p_i45475_1_);
      this.field_75909_a = p_i45475_3_;
   }

   public int[] func_75904_a(int p_75904_1_, int p_75904_2_, int p_75904_3_, int p_75904_4_) {
      int[] aint = this.field_75909_a.func_75904_a(p_75904_1_ - 1, p_75904_2_ - 1, p_75904_3_ + 2, p_75904_4_ + 2);
      int[] aint1 = IntCache.func_76445_a(p_75904_3_ * p_75904_4_);

      for(int i = 0; i < p_75904_4_; ++i) {
         for(int j = 0; j < p_75904_3_; ++j) {
            this.func_75903_a((long)(j + p_75904_1_), (long)(i + p_75904_2_));
            int k = aint[j + 1 + (i + 1) * (p_75904_3_ + 2)];
            if(!this.func_151636_a(aint, aint1, j, i, p_75904_3_, k, BiomeGenBase.func_185362_a(Biomes.field_76770_e), BiomeGenBase.func_185362_a(Biomes.field_76783_v)) && !this.func_151635_b(aint, aint1, j, i, p_75904_3_, k, BiomeGenBase.func_185362_a(Biomes.field_150607_aa), BiomeGenBase.func_185362_a(Biomes.field_150589_Z)) && !this.func_151635_b(aint, aint1, j, i, p_75904_3_, k, BiomeGenBase.func_185362_a(Biomes.field_150608_ab), BiomeGenBase.func_185362_a(Biomes.field_150589_Z)) && !this.func_151635_b(aint, aint1, j, i, p_75904_3_, k, BiomeGenBase.func_185362_a(Biomes.field_150578_U), BiomeGenBase.func_185362_a(Biomes.field_76768_g))) {
               if(k == BiomeGenBase.func_185362_a(Biomes.field_76769_d)) {
                  int l1 = aint[j + 1 + (i + 1 - 1) * (p_75904_3_ + 2)];
                  int i2 = aint[j + 1 + 1 + (i + 1) * (p_75904_3_ + 2)];
                  int j2 = aint[j + 1 - 1 + (i + 1) * (p_75904_3_ + 2)];
                  int k2 = aint[j + 1 + (i + 1 + 1) * (p_75904_3_ + 2)];
                  if(l1 != BiomeGenBase.func_185362_a(Biomes.field_76774_n) && i2 != BiomeGenBase.func_185362_a(Biomes.field_76774_n) && j2 != BiomeGenBase.func_185362_a(Biomes.field_76774_n) && k2 != BiomeGenBase.func_185362_a(Biomes.field_76774_n)) {
                     aint1[j + i * p_75904_3_] = k;
                  } else {
                     aint1[j + i * p_75904_3_] = BiomeGenBase.func_185362_a(Biomes.field_150580_W);
                  }
               } else if(k == BiomeGenBase.func_185362_a(Biomes.field_76780_h)) {
                  int l = aint[j + 1 + (i + 1 - 1) * (p_75904_3_ + 2)];
                  int i1 = aint[j + 1 + 1 + (i + 1) * (p_75904_3_ + 2)];
                  int j1 = aint[j + 1 - 1 + (i + 1) * (p_75904_3_ + 2)];
                  int k1 = aint[j + 1 + (i + 1 + 1) * (p_75904_3_ + 2)];
                  if(l != BiomeGenBase.func_185362_a(Biomes.field_76769_d) && i1 != BiomeGenBase.func_185362_a(Biomes.field_76769_d) && j1 != BiomeGenBase.func_185362_a(Biomes.field_76769_d) && k1 != BiomeGenBase.func_185362_a(Biomes.field_76769_d) && l != BiomeGenBase.func_185362_a(Biomes.field_150584_S) && i1 != BiomeGenBase.func_185362_a(Biomes.field_150584_S) && j1 != BiomeGenBase.func_185362_a(Biomes.field_150584_S) && k1 != BiomeGenBase.func_185362_a(Biomes.field_150584_S) && l != BiomeGenBase.func_185362_a(Biomes.field_76774_n) && i1 != BiomeGenBase.func_185362_a(Biomes.field_76774_n) && j1 != BiomeGenBase.func_185362_a(Biomes.field_76774_n) && k1 != BiomeGenBase.func_185362_a(Biomes.field_76774_n)) {
                     if(l != BiomeGenBase.func_185362_a(Biomes.field_76782_w) && k1 != BiomeGenBase.func_185362_a(Biomes.field_76782_w) && i1 != BiomeGenBase.func_185362_a(Biomes.field_76782_w) && j1 != BiomeGenBase.func_185362_a(Biomes.field_76782_w)) {
                        aint1[j + i * p_75904_3_] = k;
                     } else {
                        aint1[j + i * p_75904_3_] = BiomeGenBase.func_185362_a(Biomes.field_150574_L);
                     }
                  } else {
                     aint1[j + i * p_75904_3_] = BiomeGenBase.func_185362_a(Biomes.field_76772_c);
                  }
               } else {
                  aint1[j + i * p_75904_3_] = k;
               }
            }
         }
      }

      return aint1;
   }

   private boolean func_151636_a(int[] p_151636_1_, int[] p_151636_2_, int p_151636_3_, int p_151636_4_, int p_151636_5_, int p_151636_6_, int p_151636_7_, int p_151636_8_) {
      if(!func_151616_a(p_151636_6_, p_151636_7_)) {
         return false;
      } else {
         int i = p_151636_1_[p_151636_3_ + 1 + (p_151636_4_ + 1 - 1) * (p_151636_5_ + 2)];
         int j = p_151636_1_[p_151636_3_ + 1 + 1 + (p_151636_4_ + 1) * (p_151636_5_ + 2)];
         int k = p_151636_1_[p_151636_3_ + 1 - 1 + (p_151636_4_ + 1) * (p_151636_5_ + 2)];
         int l = p_151636_1_[p_151636_3_ + 1 + (p_151636_4_ + 1 + 1) * (p_151636_5_ + 2)];
         if(this.func_151634_b(i, p_151636_7_) && this.func_151634_b(j, p_151636_7_) && this.func_151634_b(k, p_151636_7_) && this.func_151634_b(l, p_151636_7_)) {
            p_151636_2_[p_151636_3_ + p_151636_4_ * p_151636_5_] = p_151636_6_;
         } else {
            p_151636_2_[p_151636_3_ + p_151636_4_ * p_151636_5_] = p_151636_8_;
         }

         return true;
      }
   }

   private boolean func_151635_b(int[] p_151635_1_, int[] p_151635_2_, int p_151635_3_, int p_151635_4_, int p_151635_5_, int p_151635_6_, int p_151635_7_, int p_151635_8_) {
      if(p_151635_6_ != p_151635_7_) {
         return false;
      } else {
         int i = p_151635_1_[p_151635_3_ + 1 + (p_151635_4_ + 1 - 1) * (p_151635_5_ + 2)];
         int j = p_151635_1_[p_151635_3_ + 1 + 1 + (p_151635_4_ + 1) * (p_151635_5_ + 2)];
         int k = p_151635_1_[p_151635_3_ + 1 - 1 + (p_151635_4_ + 1) * (p_151635_5_ + 2)];
         int l = p_151635_1_[p_151635_3_ + 1 + (p_151635_4_ + 1 + 1) * (p_151635_5_ + 2)];
         if(func_151616_a(i, p_151635_7_) && func_151616_a(j, p_151635_7_) && func_151616_a(k, p_151635_7_) && func_151616_a(l, p_151635_7_)) {
            p_151635_2_[p_151635_3_ + p_151635_4_ * p_151635_5_] = p_151635_6_;
         } else {
            p_151635_2_[p_151635_3_ + p_151635_4_ * p_151635_5_] = p_151635_8_;
         }

         return true;
      }
   }

   private boolean func_151634_b(int p_151634_1_, int p_151634_2_) {
      if(func_151616_a(p_151634_1_, p_151634_2_)) {
         return true;
      } else {
         BiomeGenBase biomegenbase = BiomeGenBase.func_150568_d(p_151634_1_);
         BiomeGenBase biomegenbase1 = BiomeGenBase.func_150568_d(p_151634_2_);
         if(biomegenbase != null && biomegenbase1 != null) {
            BiomeGenBase.TempCategory biomegenbase$tempcategory = biomegenbase.func_150561_m();
            BiomeGenBase.TempCategory biomegenbase$tempcategory1 = biomegenbase1.func_150561_m();
            return biomegenbase$tempcategory == biomegenbase$tempcategory1 || biomegenbase$tempcategory == BiomeGenBase.TempCategory.MEDIUM || biomegenbase$tempcategory1 == BiomeGenBase.TempCategory.MEDIUM;
         } else {
            return false;
         }
      }
   }
}
