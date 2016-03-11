package net.minecraft.world.biome;

import java.util.Random;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenIcePath;
import net.minecraft.world.gen.feature.WorldGenIceSpike;
import net.minecraft.world.gen.feature.WorldGenTaiga2;

public class BiomeGenSnow extends BiomeGenBase {
   private boolean field_150615_aC;
   private WorldGenIceSpike field_150616_aD = new WorldGenIceSpike();
   private WorldGenIcePath field_150617_aE = new WorldGenIcePath(4);

   public BiomeGenSnow(boolean p_i46706_1_, BiomeGenBase.BiomeProperties p_i46706_2_) {
      super(p_i46706_2_);
      this.field_150615_aC = p_i46706_1_;
      if(p_i46706_1_) {
         this.field_76752_A = Blocks.field_150433_aE.func_176223_P();
      }

      this.field_76762_K.clear();
      this.field_76762_K.add(new BiomeGenBase.SpawnListEntry(EntityRabbit.class, 4, 2, 3));
   }

   public void func_180624_a(World p_180624_1_, Random p_180624_2_, BlockPos p_180624_3_) {
      if(this.field_150615_aC) {
         for(int i = 0; i < 3; ++i) {
            int j = p_180624_2_.nextInt(16) + 8;
            int k = p_180624_2_.nextInt(16) + 8;
            this.field_150616_aD.func_180709_b(p_180624_1_, p_180624_2_, p_180624_1_.func_175645_m(p_180624_3_.func_177982_a(j, 0, k)));
         }

         for(int l = 0; l < 2; ++l) {
            int i1 = p_180624_2_.nextInt(16) + 8;
            int j1 = p_180624_2_.nextInt(16) + 8;
            this.field_150617_aE.func_180709_b(p_180624_1_, p_180624_2_, p_180624_1_.func_175645_m(p_180624_3_.func_177982_a(i1, 0, j1)));
         }
      }

      super.func_180624_a(p_180624_1_, p_180624_2_, p_180624_3_);
   }

   public WorldGenAbstractTree func_150567_a(Random p_150567_1_) {
      return new WorldGenTaiga2(false);
   }
}
