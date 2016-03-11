package net.minecraft.world.biome;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeProvider;

public class BiomeProviderSingle extends BiomeProvider {
   private final BiomeGenBase field_76947_d;

   public BiomeProviderSingle(BiomeGenBase p_i46709_1_) {
      this.field_76947_d = p_i46709_1_;
   }

   public BiomeGenBase func_180631_a(BlockPos p_180631_1_) {
      return this.field_76947_d;
   }

   public BiomeGenBase[] func_76937_a(BiomeGenBase[] p_76937_1_, int p_76937_2_, int p_76937_3_, int p_76937_4_, int p_76937_5_) {
      if(p_76937_1_ == null || p_76937_1_.length < p_76937_4_ * p_76937_5_) {
         p_76937_1_ = new BiomeGenBase[p_76937_4_ * p_76937_5_];
      }

      Arrays.fill(p_76937_1_, 0, p_76937_4_ * p_76937_5_, this.field_76947_d);
      return p_76937_1_;
   }

   public BiomeGenBase[] func_76933_b(BiomeGenBase[] p_76933_1_, int p_76933_2_, int p_76933_3_, int p_76933_4_, int p_76933_5_) {
      if(p_76933_1_ == null || p_76933_1_.length < p_76933_4_ * p_76933_5_) {
         p_76933_1_ = new BiomeGenBase[p_76933_4_ * p_76933_5_];
      }

      Arrays.fill(p_76933_1_, 0, p_76933_4_ * p_76933_5_, this.field_76947_d);
      return p_76933_1_;
   }

   public BiomeGenBase[] func_76931_a(BiomeGenBase[] p_76931_1_, int p_76931_2_, int p_76931_3_, int p_76931_4_, int p_76931_5_, boolean p_76931_6_) {
      return this.func_76933_b(p_76931_1_, p_76931_2_, p_76931_3_, p_76931_4_, p_76931_5_);
   }

   public BlockPos func_180630_a(int p_180630_1_, int p_180630_2_, int p_180630_3_, List<BiomeGenBase> p_180630_4_, Random p_180630_5_) {
      return p_180630_4_.contains(this.field_76947_d)?new BlockPos(p_180630_1_ - p_180630_3_ + p_180630_5_.nextInt(p_180630_3_ * 2 + 1), 0, p_180630_2_ - p_180630_3_ + p_180630_5_.nextInt(p_180630_3_ * 2 + 1)):null;
   }

   public boolean func_76940_a(int p_76940_1_, int p_76940_2_, int p_76940_3_, List<BiomeGenBase> p_76940_4_) {
      return p_76940_4_.contains(this.field_76947_d);
   }
}
